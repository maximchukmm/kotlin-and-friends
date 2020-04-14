package edu.hibernate

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.junit.After
import org.junit.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception

abstract class HibernateBaseTest {
    companion object {
        val log: Logger = LoggerFactory.getLogger(HibernateBaseTest::class.java)
    }

    protected var sessionFactory: SessionFactory? = null

    abstract protected fun entities(): Array<Class<*>>

    protected fun additionalSettings(): Map<String, Any> = emptyMap()

    protected fun doInTransaction(function: (Session?) -> Unit) {
        var session: Session? = null
        var transaction: Transaction? = null

        try {
            session = sessionFactory?.openSession()
            transaction = session?.beginTransaction()
            function.invoke(session)
            transaction?.commit()
        } catch (e: Throwable) {
            if (transaction != null && transaction.isActive) {
                transaction.rollback()
            }
            log.error(e.toString())
            throw e
        } finally {
            session?.close()
        }
    }

    protected fun <T> doInTransaction(function: (Session?) -> T): T {
        var result: T
        var session: Session? = null
        var transaction: Transaction? = null

        try {
            session = sessionFactory?.openSession()
            transaction = session?.beginTransaction()
            result = function.invoke(session)
            transaction?.commit()
        } catch (e: Throwable) {
            if (transaction != null && transaction.isActive) {
                transaction.rollback()
            }
            log.error(e.toString())
            throw e
        } finally {
            session?.close()
        }

        return result
    }

    @Before
    fun setUp() {
        val standardServiceRegistry = StandardServiceRegistryBuilder()
                .configure()
                .applySettings(additionalSettings())
                .build()

        try {
            val metadataSources = MetadataSources(standardServiceRegistry)

            for (entity in entities()) {
                metadataSources.addAnnotatedClass(entity)
            }

            val metadata = metadataSources
                    .metadataBuilder
                    .enableNewIdentifierGeneratorSupport(true)
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                    .build()

            sessionFactory = metadata.sessionFactoryBuilder.build()
        } catch (e: Exception) {
            log.error(e.toString())
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry)
        }
    }

    @After
    fun tearDown() {
        sessionFactory?.close()
    }
}
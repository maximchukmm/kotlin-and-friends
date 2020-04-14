plugins {
    kotlin("jvm")
    kotlin("plugin.jpa") version "1.3.71"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.hibernate:hibernate-core:5.4.14.Final")
    implementation("org.hibernate:hibernate-entitymanager:5.4.14.Final")
    implementation("org.hibernate:hibernate-envers:5.4.14.Final")

    implementation("com.zaxxer:HikariCP:3.4.2")

    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("p6spy:p6spy:3.9.0")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    testImplementation("com.h2database:h2:1.4.200")
}
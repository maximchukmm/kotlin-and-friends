plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("io.ktor:ktor-client-core:1.6.0")
    implementation("io.ktor:ktor-client-cio:1.6.0")

    implementation("com.google.code.gson:gson:2.8.7")

    implementation("ch.qos.logback:logback-classic:1.0.13")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug:1.5.0")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
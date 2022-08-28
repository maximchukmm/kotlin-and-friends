plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("io.ktor:ktor-client-cio:1.6.7")

    implementation("com.google.code.gson:gson:2.9.0")

    implementation("ch.qos.logback:logback-classic:1.2.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug:1.6.0")

    implementation("org.apache.pdfbox:pdfbox:3.0.0-RC1")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}
plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)

    // Json 클래스화를 쉽게 하기 위함
    alias(libs.plugins.kotlin.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    // @Inject 와 같은 어노테이션만 사용하기 위함. 실제 주입은 Hilt가 해줌 (다른 모듈에서 끌어다 쓰면서)
    implementation(libs.javax.inject)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.core.jvm)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.bundles.kotest)
    testImplementation(libs.mockk)
}

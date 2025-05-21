pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://devrepo.kakao.com/nexus/content/groups/public/") }
        maven { setUrl("https://jitpack.io") }
    }
}

// 다른 gradle.kts 에서 projects 접근자를 사용하기 위함
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Withpark"

include(":app")
include(":presentation")

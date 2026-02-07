plugins {
    kotlin("jvm")
    `maven-publish`
    signing
}

group = "io.github.xerooup"
version = "0.1.0"

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    api(platform("org.lwjgl:lwjgl-bom:3.3.6"))
    api("org.lwjgl:lwjgl")
    api("org.lwjgl:lwjgl-glfw")
    api("org.lwjgl:lwjgl-opengl")
    api("org.lwjgl:lwjgl-stb")
    api("org.lwjgl:lwjgl-freetype")
    api("org.lwjgl:lwjgl-openal")

    listOf(
        "natives-windows",
        "natives-linux",
        "natives-macos"
    ).forEach { native ->
        runtimeOnly("org.lwjgl", "lwjgl", classifier = native)
        runtimeOnly("org.lwjgl", "lwjgl-glfw", classifier = native)
        runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = native)
        runtimeOnly("org.lwjgl", "lwjgl-stb", classifier = native)
        runtimeOnly("org.lwjgl", "lwjgl-freetype", classifier = native)
        runtimeOnly("org.lwjgl", "lwjgl-openal", classifier = native)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            groupId = group.toString()
            artifactId = "frame-2d"
            version = version.toString()

            pom {
                name.set("frame-2d")
                description.set("""
                        frame — a kotlin game engine focused on simple game creation and code readability.
                        engine module: 2D
                        """.trimIndent())
                url.set("https://github.com/xerooup/frame")

                licenses {
                    license {
                        name.set("LGPL-3.0 License")
                        url.set("https://www.gnu.org/licenses/lgpl-3.0.html")
                    }
                }

                developers {
                    developer {
                        id.set("xeroup")
                        name.set("xeroup")
                        url.set("https://github.com/xerooup")
                    }
                }

                scm {
                    url.set("https://github.com/xerooup/frame")
                    connection.set("scm:git:https://github.com/xerooup/frame.git")
                    developerConnection.set("scm:git:ssh://github.com/xerooup/frame.git")
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}

kotlin {
    jvmToolchain(21)
}
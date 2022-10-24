plugins {
    java
    `maven-publish`
}

group = "org.ddsa"
version = "1.0.0-SNAPSHOT"


dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

allprojects {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }

        repositories {
            maven {
                name = "nexus"
                val url = "http://localhost:8081/repository/maven-snapshots/"
                isAllowInsecureProtocol = true
                setUrl {
                    url
                }
                credentials {
                    username = "admin"
                    password = "admin"
                }
            }
        }

        tasks.withType<GenerateModuleMetadata> {
            enabled = false;
        }
    }
}
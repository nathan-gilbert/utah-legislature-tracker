import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  val kotlinVersion = "1.3.72"
  id("org.springframework.boot") version "2.3.0.RELEASE"
  id("io.spring.dependency-management") version "1.0.9.RELEASE"
  @Suppress("StringLiteralDuplication")
  kotlin("jvm") version kotlinVersion
  kotlin("plugin.spring") version kotlinVersion
  kotlin("plugin.allopen") version kotlinVersion
  kotlin("plugin.jpa") version kotlinVersion
  kotlin("kapt") version kotlinVersion
  id("io.gitlab.arturbosch.detekt") version "1.9.1"
  id("jacoco")
  id("org.jetbrains.dokka") version "0.10.1"
  id("org.flywaydb.flyway") version "6.3.2"
}

group = "org.mediumcool"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
  jcenter()
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-mustache")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.postgresql:postgresql")
  implementation("io.springfox:springfox-swagger2:2.9.2")
  implementation("io.springfox:springfox-swagger-ui:2.9.2")
  runtimeOnly("com.h2database:h2")
  runtimeOnly("org.springframework.boot:spring-boot-devtools")
  kapt("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.9.1")
}

allOpen {
  annotation("javax.persistence.Entity")
  annotation("javax.persistence.Embeddable")
  annotation("javax.persistence.MappedSuperclass")
}

detekt {
  failFast = true // fail build on any finding
  buildUponDefaultConfig = true // preconfigure defaults
  // point to your custom config defining rules to run, overwriting default behavior
  config = files("$projectDir/detekt.yml")
  autoCorrect = true

  reports {
    html.enabled = true // observe findings in your browser with structure and code snippets
    xml.enabled = false // checkstyle like format mainly for integrations like Jenkins
    txt.enabled = false // similar to the console output, contains issue signature to manually edit baseline files
  }
}

jacoco {
  toolVersion = "0.8.5"
}

flyway {
  url = "jdbc:postgresql://localhost:5432/demo"
  user = "nathan"
  password = ""
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    this.jvmTarget = "11"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks {
  withType<io.gitlab.arturbosch.detekt.Detekt> {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = "11"
  }
}

tasks.jacocoTestReport {
  reports {
    xml.isEnabled = false
    csv.isEnabled = false
    html.isEnabled = true
    html.destination = file("$buildDir/reports/jacocoHtml")
  }
}

tasks.jacocoTestCoverageVerification {
  violationRules {
    rule {
      limit {
        minimum = "1.0".toBigDecimal()
      }
    }
  }
}

tasks {
  getting(DokkaTask::class) {
    outputFormat = "html"
    outputDirectory = "$buildDir/dokka"
  }
}

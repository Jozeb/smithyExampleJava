import org.openapitools.codegen.languages.OpenAPIGenerator
import software.amazon.smithy.gradle.SmithyExtension

plugins {
    java
    id("software.amazon.smithy").version("0.7.0")
    id("org.openapi.generator").version("6.6.0")
}

buildscript {

    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("software.amazon.smithy:smithy-openapi:1.33.0")
        // https://mvnrepository.com/artifact/software.amazon.smithy/smithy-openapi

        // The openapi plugin configured in the smithy-build.json example below
        // uses the restJson1 protocol defined in the aws-traits package. This
        // additional dependency must added to use that protocol.
        classpath("software.amazon.smithy:smithy-aws-traits:1.33.0")
        classpath("org.openapitools:openapi-generator-gradle-plugin:6.6.0")
    }
}


dependencies {
    implementation("software.amazon.smithy:smithy-openapi:1.33.0")
    implementation("software.amazon.smithy:smithy-aws-traits:1.33.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("javax.ws.rs:jsr311-api:1.1.1")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0-M1")

}


repositories {
    mavenLocal()
    mavenCentral()
}

configure<SmithyExtension> {
    tags = setOf("SoftwareCrafters")
    outputDirectory = File("${buildDir}/smithymodel/")
}


openApiGenerate {
    generatorName.set("java")
    inputSpec.set("${buildDir}/smithymodel/source/openapi/Weather.openapi.json")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.software.crafters")
    invokerPackage.set("com.software.crafters.invoker")
    modelPackage.set("com.software.crafters.model")
}

sourceSets["main"].java.srcDir(file("${buildDir}/generated"))

java.sourceSets["main"].java {
    srcDirs("model", "src/main/smithy")
}

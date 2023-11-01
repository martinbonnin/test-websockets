plugins {
  id("org.jetbrains.kotlin.jvm").version("1.9.20")
}

val ktor_version = "2.3.5"
dependencies {
  implementation("io.ktor:ktor-server-netty:$ktor_version")
  implementation("io.ktor:ktor-server-websockets:$ktor_version")
}

tasks.register<JavaExec>("runServer", JavaExec::class.java).configure {
  this.classpath(configurations.getByName("runtimeClasspath"))
  this.classpath(tasks.named("jar"))
  this.mainClass.set("MainKt")
}
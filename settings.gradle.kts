plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "springcloud"
include("core")
include("discoveryservice")
include("userservice")
include("catalogservice")
include("orderservice")
include("myservice1")
include("myservice2")
include("gateway")
include("configservice")

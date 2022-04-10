package io.javalin.plugin.metrics

import io.javalin.Javalin
import io.javalin.core.util.JavalinLogger
import io.javalin.testing.TestUtil
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test

class MicrometerPluginTest {

    @Test
    fun `test that JettyConnectionMetrics is registered`() {
        val registry = SimpleMeterRegistry()
        val micrometerApp = Javalin.create { it.registerPlugin(MicrometerPlugin(registry)) }

        TestUtil.test(micrometerApp) { app, http ->
            app.get("/test") { it.json("Hello world") }
            repeat(10) {
                http.get("/test")
            }
        }

        val maxConnectionGauge = registry.find("jetty.connections.max").gauge() ?: fail("\"jetty.connections.max\" not found")
        assertThat(maxConnectionGauge.value()).isGreaterThan(0.0)

        val messagesOutCounter = registry.find("jetty.connections.messages.out").counter() ?: fail("\"jetty.connections.messages.out\" not found")
        assertThat(messagesOutCounter.count()).isGreaterThan(0.0)
    }
}

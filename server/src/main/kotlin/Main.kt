import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    embeddedServer(Netty, port = 9000) {
        install(WebSockets)

        routing {
            webSocketRaw("/") {
                incoming.receive().apply {
                    check(this is Frame.Text)
                    println(this.readText())
                }

                try {
                    incoming.receive().apply {
                        check(this is Frame.Close)
                    }
                } catch (e: Exception) {
                    println("No close received")
                    e.printStackTrace()
                    exitProcess(1)
                }
            }
        }
    }.start(wait = true)
}
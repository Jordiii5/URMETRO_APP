import org.apache.xmlrpc.XmlRpcException
import org.apache.xmlrpc.client.XmlRpcClient
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl
import java.net.URL

class UR3XmlRpcClient {
    private val serverUrl = "http://172.23.6.131:8080" // Reemplaza con la dirección real del servidor

    fun moveRobotToPosition(position: List<Double>) {
        try {
            val config = XmlRpcClientConfigImpl()
            config.serverURL = URL("$serverUrl/")
            val client = XmlRpcClient()
            client.config == config

            // Llamada al método remoto del servidor para mover el robot a una posición
            client.execute("UR3JointServer.getRandomJointPosition", listOf(position))
        } catch (e: XmlRpcException) {
            e.printStackTrace()
            // Maneja cualquier error de XML-RPC aquí
        }
    }
}

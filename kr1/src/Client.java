import java.net.*;

public class Client {
    private static InetAddress address;
    private static byte[] buffer;
    private static DatagramPacket packet;
    private static String str;
    private static MulticastSocket socket;

    public static void main(String arg[]) throws Exception {
        System.out.println("Ожидание сообщения от сервера");
        try {
            // Создание объекта MulticastSocket, чтобы получать
            // данные от группы, используя номер порта 1502
            socket = new MulticastSocket(1502);
            address = InetAddress.getByName("233.0.0.1");
            // Регистрация клиента в группе
            socket.joinGroup(address);
            while (true) {
                buffer = new byte[256];
                packet = new DatagramPacket(
                        buffer, buffer.length);
                // Получение данных от сервера
                socket.receive(packet);
                str = new String(packet.getData());
                System.out.println(
                        "Получено сообщение: " + str.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Удаление клиента из группы
                socket.leaveGroup(address);
                // Закрытие сокета
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

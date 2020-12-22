import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientTCP {
    public static void main(String args[]) {
        try {
            // Создается объект Socket для соединения с сервером
            Socket clientSocket = new Socket("localhost", 1500);
            // Получаем ссылку на поток, связанный с сокетом
            ObjectInputStream in =
                    new ObjectInputStream(clientSocket.getInputStream());
            // Извлекаем объект из входного потока
            DateMessage dateMessage =
                    (DateMessage) in.readObject();
            // Выводим полученные данные в консоль
            System.out.println(dateMessage.getMessage());
            System.out.println(dateMessage.getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 * Класс сервера (выполняется в отдельном процессе)
 */
public class ServerTCP extends Thread {
    // Объявляется ссылка на объект - сокет сервера
    ServerSocket serverSocket = null;

    /**
     * Конструктор по умолчанию
     */
    public ServerTCP() {
        try {
            // Создается объект ServerSocket, который получает
            // запросы клиента на порт 1500
            serverSocket = new ServerSocket(1500);
            System.out.println("Starting the server ");
            // Запускаем процесс
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Запуск процесса
     */
    public void run() {
        try {
            while (true) {
                // Ожидание запросов соединения от клиентов
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted from " +
                        clientSocket.getInetAddress().getHostAddress());
                // Получение выходного потока, связанного с объектом Socket
                ObjectOutputStream out =
                        new ObjectOutputStream(clientSocket.getOutputStream());
                // Создание объекта для передачи клиентам
                DateMessage dateMessage = new DateMessage(
                        Calendar.getInstance().getTime(),
                        "Текущая дата/время на сервере");
                // Запись объекта в выходной поток
                out.writeObject(dateMessage);
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        // Запуск сервера
        new ServerTCP();
    }
}

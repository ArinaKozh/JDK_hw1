import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Задача: Создать простейшее окно управления сервером (по сути, любым),
 * содержащее две кнопки (JButton) – запустить сервер и остановить сервер.
 * Кнопки должны просто логировать нажатие (имитировать запуск и остановку
 * сервера, соответственно) и выставлять внутри интерфейса соответствующее
 * булево isServerWorking.
 */


/**
 * ServerWindow - класс, наследующий JFrame, предназначенный для создания окна управления сервером.
 */

public class Server extends JFrame {
    private static final int WINDOW_HEIGHT = 340;
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_POS_X = 300;
    private static final int WINDOW_POS_Y = 100;

    private static final String LOG_FILE = "chat_log.txt";

    JTextArea history = new JTextArea();

    Server(){
        setTitle("ПОДКЛЮЧЕНИЕ К СЕРВЕРУ");
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel grid = new JPanel(new GridLayout(3, 2));
        JButton buttonConnect = new JButton("CONNECT");
        grid.add(buttonConnect);
        add(grid);
        buttonConnect.addActionListener(e -> {
            loadChatHistory();
            new Chat(history);
        });

        setVisible(true);
    }
    private void loadChatHistory() {
        File file = new File(LOG_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    history.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
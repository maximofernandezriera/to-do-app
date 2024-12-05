import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private TaskManager taskManager;
    private TaskPanel taskPanel;

    public MainFrame() {
        super("To-Do List Application");
        taskManager = new TaskManager();
        taskPanel = new TaskPanel(taskManager);

        setLayout(new BorderLayout());
        add(taskPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }
}

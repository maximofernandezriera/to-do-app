import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TaskPanel es un componente de interfaz de usuario que permite gestionar una lista de tareas.
 * Proporciona funcionalidades para agregar, eliminar y completar tareas.
 * 
 * Componentes:
 * - taskListModel: Modelo de lista que contiene las tareas.
 * - taskList: Componente JList que muestra las tareas.
 * - taskField: Campo de texto para ingresar la descripción de una nueva tarea.
 * - addButton: Botón para agregar una nueva tarea.
 * - removeButton: Botón para eliminar la tarea seleccionada.
 * - completeButton: Botón para marcar la tarea seleccionada como completada.
 * 
 * Funcionalidades:
 * - Agregar tarea: Permite agregar una nueva tarea a la lista ingresando una descripción en el campo de texto y presionando el botón "Add Task".
 * - Eliminar tarea: Permite eliminar la tarea seleccionada de la lista presionando el botón "Remove Task".
 * - Completar tarea: Permite marcar la tarea seleccionada como completada presionando el botón "Complete Task".
 * 
 * El TaskRenderer es una clase interna que extiende DefaultListCellRenderer y se utiliza para personalizar la apariencia de las tareas en la lista.
 * Las tareas completadas se muestran en color gris y en cursiva, mientras que las tareas pendientes se muestran en color negro y en fuente normal.
 * 
 * @param taskManager El gestor de tareas que maneja la lógica de negocio de las tareas.
 */

public class TaskPanel extends JPanel {
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JTextField taskField;
    private JButton addButton;
    private JButton removeButton;
    private JButton completeButton;

    public TaskPanel(TaskManager taskManager) {
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskRenderer());

        taskField = new JTextField();
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        completeButton = new JButton("Complete Task");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = taskField.getText();
                if (!description.isEmpty()) {
                    Task task = new Task(description);
                    taskManager.addTask(task);
                    taskListModel.addElement(task);
                    taskField.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task selectedTask = taskList.getSelectedValue();
                if (selectedTask != null) {
                    taskManager.removeTask(selectedTask);
                    taskListModel.removeElement(selectedTask);
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task selectedTask = taskList.getSelectedValue();
                if (selectedTask != null) {
                    taskManager.completeTask(selectedTask);
                    taskList.repaint();
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(removeButton);
        buttonPanel.add(completeButton);

        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class TaskRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Task) {
                Task task = (Task) value;
                setText(task.getDescription());
                if (task.isCompleted()) {
                    setForeground(Color.GRAY);
                    setFont(getFont().deriveFont(Font.ITALIC));
                } else {
                    setForeground(Color.BLACK);
                    setFont(getFont().deriveFont(Font.PLAIN));
                }
            }
            return c;
        }
    }
}

### TaskPanel.java

La clase `TaskPanel` extiende `JPanel` y sirve como una interfaz gráfica para gestionar tareas. Esta clase utiliza varios componentes de Swing para permitir al usuario agregar, eliminar y completar tareas en una lista.

En el constructor `TaskPanel`, se establece un diseño de `BorderLayout` para el panel. Se inicializan varios componentes: `taskListModel` (un modelo de lista por defecto para almacenar tareas), `taskList` (una lista visual que utiliza `taskListModel`), `taskField` (un campo de texto para ingresar nuevas tareas), y tres botones (`addButton`, `removeButton`, `completeButton`) para gestionar las tareas. La lista de tareas (`taskList`) utiliza un renderizador personalizado (`TaskRenderer`) para mostrar las tareas con diferentes estilos según su estado de completitud.

Se añaden `ActionListeners` a los botones para definir su comportamiento. El botón "Add Task" agrega una nueva tarea a la lista si el campo de texto no está vacío. El botón "Remove Task" elimina la tarea seleccionada de la lista. El botón "Complete Task" marca la tarea seleccionada como completada y actualiza la lista para reflejar este cambio visualmente.

El panel se divide en tres secciones: un panel de entrada (`inputPanel`) que contiene el campo de texto y el botón de agregar, un panel de botones (`buttonPanel`) que contiene los botones de eliminar y completar, y un panel central que muestra la lista de tareas dentro de un `JScrollPane` para permitir el desplazamiento.

Finalmente, la clase interna `TaskRenderer` extiende `DefaultListCellRenderer` y personaliza la representación de cada tarea en la lista. Si una tarea está completada, se muestra en color gris y en cursiva; de lo contrario, se muestra en color negro y en fuente normal.

En resumen, `TaskPanel` proporciona una interfaz gráfica completa para gestionar una lista de tareas, permitiendo al usuario agregar, eliminar y marcar tareas como completadas, con una representación visual clara de su estado.

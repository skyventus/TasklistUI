package oliviercheah.tojava.taskmanager.util;

import oliviercheah.tojava.taskmanager.model.Deadline;
import oliviercheah.tojava.taskmanager.model.Todo;

public class Parser {

    public static String getCommandWord(String fullCommand){
        String[] task;

        fullCommand=fullCommand.toLowerCase().trim();

        task = fullCommand.split(" ");
        try {
             if(task[0].equals("todo"))
                 return "todo";
        }catch(ArrayIndexOutOfBoundsException e){
            Ui.printError(e.getMessage());
        }
        return task[0];
    }

    public static Todo createTodo(String fullCommand){
        Todo todoTask = new Todo(fullCommand.trim());
        return todoTask;
    }

    public static Deadline createDeadline(String fullCommand, String by){
        Deadline deadline = new Deadline(fullCommand,by);
        return deadline;
    }
}

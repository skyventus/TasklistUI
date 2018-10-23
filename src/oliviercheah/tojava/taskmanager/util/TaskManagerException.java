package oliviercheah.tojava.taskmanager.util;

public class TaskManagerException extends Exception {
    private final String message;

    public TaskManagerException(String message) {
        super(message);
        this.message = message;
    }
}

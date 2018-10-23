package oliviercheah.tojava.taskmanager.model;

public class Deadline extends Todo {

    private String deadline;

    public Deadline(String task, String deadline){
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getBy(){
        return deadline;
    }

    public void setBy(String s){
        this.deadline=s;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "do by: " + deadline;
    }

    @Override
    public String save(){
        return "D | " + (super.isDone() ? "1" : "0") + " | " + super.getDescription() + " | " + deadline + "\n";
    }
}

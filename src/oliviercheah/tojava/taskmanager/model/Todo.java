package oliviercheah.tojava.taskmanager.model;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.CheckBox;

public class Todo extends Task {

    private boolean isDone;
    public ObjectProperty<CheckBox> selected;

    public Todo(String Desc){
        super(Desc);
        isDone=false;
    }

    @Override
    public boolean isDone(){
        return this.isDone;
    }

    @Override
    public void setDone(boolean d){
        this.isDone=d;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() +  "Is Done? " + (isDone ? "Yes" : "No");
    }

    @Override
    public String save(){
        return "T | " + (isDone ? "1" : "0")+ " | " + super.save() + "\n";
    }

    @Override
    public String getBy(){
       return "";
    }

    @Override
    public void setBy(String s){

    }
    public ObjectProperty<CheckBox> checkBoxObjectProperty(){ return selected;}

    public CheckBox getSelect(){
        return selected.get();
    }

    public void setSelect(boolean selected){
        this.isDone=selected;
    }

//    @Override
//    public void printTask(Tasklist task){
//        //[1] submit report
//        // super.printTask(task) + System.lineSeparator() +  "Is Done? " + (isDone ? "Yes" : "No");
//        // System.out.println(Arrays.toString(task.taskDesc.toArray()));
//    }
}

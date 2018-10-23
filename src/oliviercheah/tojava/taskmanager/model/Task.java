package oliviercheah.tojava.taskmanager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    protected StringProperty description;

    public Task(){
        this(null);
    }
    public Task(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String task){
        this.description.setValue(task);
    }

    @Override
    public String toString() {
        return "description: " + description;
    }

    public String getBy(){
        return "";
    }

    public boolean isDone(){
        return true;
    }

    public void setDone(boolean d){}

    public String save(){
        return description.get();
    }

    public StringProperty descriptionProperty(){
        return description;
    }

    public void setBy(String s){

    }

}
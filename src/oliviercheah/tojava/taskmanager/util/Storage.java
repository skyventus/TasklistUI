package oliviercheah.tojava.taskmanager.util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oliviercheah.tojava.taskmanager.model.Task;
import oliviercheah.tojava.taskmanager.util.TaskManagerException;
import oliviercheah.tojava.taskmanager.model.Tasklist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {

    private String filePath;
    private Tasklist tasks;


    public Storage(String filePath){
        this.filePath = filePath;
    };

//    public Tasklist load() throws IOException, TaskManagerException {
//        String[] task;
//        File f = new File(filePath); // create a File for the given file path
//        Scanner s = new Scanner(f); // create a Scanner using the File as the source
//        int idx = 1;
//        tasks = new Tasklist();
//        if(! s.hasNext()){
//            throw new TaskManagerException("Error reading the file");
//        }
//        while(s.hasNext()){
//            task = s.nextLine().split(Pattern.quote("|"));
//            try{
//                if (task[0].trim().equals("T")) {
//                    tasks.addTask(Parser.createTodo(task[2]));
//                } else if (task[0].trim().equals("D")) {
//                    tasks.addTask(Parser.createDeadline(task[2].trim(), task[3].trim()));
//                }
//                if (task[1].contains("1")) {
//                  //  tasks.completedTask(idx);
//                }
//                idx++;
//            }catch (StringIndexOutOfBoundsException e){
//                e.getMessage();
//            }
//        }
//        return this.tasks;
//    }

    public ObservableList<Task> load() throws IOException, TaskManagerException {
        ObservableList<Task> allTasks = FXCollections.observableArrayList();
        String[] task;
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int idx = 1;
        if(! s.hasNext()){
            throw new TaskManagerException("Error reading the file");
        }
        while(s.hasNext()){
            task = s.nextLine().split(Pattern.quote("|"));
            try{
                if (task[0].trim().equals("T")) {
                    allTasks.add(Parser.createTodo(task[2]));
                } else if (task[0].trim().equals("D")) {
                    allTasks.add(Parser.createDeadline(task[2].trim(), task[3].trim()));
                }
                if (task[1].contains("1")) {
                     allTasks.get(idx-1).setDone(true);
                }
                idx++;
            }catch (StringIndexOutOfBoundsException e){
                e.getMessage();
            }
        }
        return allTasks;
    }

    public ObservableList<Task> loadIncompletedTask() throws IOException, TaskManagerException {
        ObservableList<Task> CompletedTask = FXCollections.observableArrayList();
        String[] task;
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int idx = 1;
        if(! s.hasNext()){
            throw new TaskManagerException("Error reading the file");
        }
        while(s.hasNext()){
            task = s.nextLine().split(Pattern.quote("|"));
            try{
                if (task[0].trim().equals("T") && task[1].contains("0")) {
                    CompletedTask.add(Parser.createTodo(task[2]));
                } else if (task[0].trim().equals("D") && task[1].contains("1") ) {
                    CompletedTask.add(Parser.createDeadline(task[2].trim(), task[3].trim()));
                }
                idx++;
            }catch (StringIndexOutOfBoundsException e){
                e.getMessage();
            }
        }
        return CompletedTask;
    }

    public ObservableList<Task> loadCompletedTask() throws IOException, TaskManagerException {
        ObservableList<Task> CompletedTask = FXCollections.observableArrayList();
        String[] task;
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int idx = 1;
        if(! s.hasNext()){
            throw new TaskManagerException("Error reading the file");
        }
        while(s.hasNext()){
            task = s.nextLine().split(Pattern.quote("|"));
            try{
                if (task[0].trim().equals("T") && task[1].contains("1")) {
                    CompletedTask.add(Parser.createTodo(task[2]));
                    CompletedTask.get(idx-1).setDone(true);
                    idx++;
                } else if (task[0].trim().equals("D") && task[1].contains("1") ) {
                    CompletedTask.add(Parser.createDeadline(task[2].trim(), task[3].trim()));
                    CompletedTask.get(idx-1).setDone(true);
                    idx++;
                }
            }catch (StringIndexOutOfBoundsException e){
                e.getMessage();
            }
        }
        return CompletedTask;
    }
    public void save(ObservableList<Task>  tasks){
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            for(int i=0; i<tasks.size(); i++){
                fw.append(tasks.get(i).save());
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e){
            e.getMessage();
        }
    }
}

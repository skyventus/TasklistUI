package oliviercheah.tojava.taskmanager.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import oliviercheah.tojava.taskmanager.MainApp;
import oliviercheah.tojava.taskmanager.model.*;

public class TaskOverviewController {

    @FXML
    private TableView<Task> taskCompletedTable;

    @FXML
    private TableView<Task> IncompleteTaskTable;

    @FXML
    private TableColumn<Task, String> incompleteDescription;

    @FXML
    private TableColumn<Task, String> completeDescription;
    private MainApp mainApp;

    /**
     * The constructor. It is called before the initialize() method.
     */
    public TaskOverviewController(){
    }

    /**
     * Is called by the main application to give a reference back to itself.
     */
    @FXML
    private void initialize(){
        incompleteDescription.setCellValueFactory(
               cellData-> cellData.getValue().descriptionProperty()
        );
        completeDescription.setCellValueFactory(
                cellData -> cellData.getValue().descriptionProperty()
        );


        IncompleteTaskTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTask(newValue));
        taskCompletedTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> showTask(newValue));
       //taskCompletedTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void showTask(Task task) {

        boolean okClicked = mainApp.showTaskDialog(task);

        if(task != null)
            if (okClicked) {
                deleteTask();
                //task=null;
            }else{

        }


    }

    @FXML
    public void deleteTask(){
        Task task = taskCompletedTable.getSelectionModel().getSelectedItem();
        mainApp.getAllCompletedTask().remove(task);
    }

    @FXML
    public void addNewTodo(){
        Task tempTodo = new Todo("");
        boolean okClicked = mainApp.showTaskDialog(tempTodo);
        if (okClicked) {
            if(tempTodo.isDone())
                mainApp.getAllCompletedTask().add(tempTodo);
            else
                mainApp.getAllIncompletedTask().add(tempTodo);
        }
    }

    @FXML
    public void addNewDeadline(){
        Task tempTodo = new Deadline("", "");
        boolean okClicked = mainApp.showTaskDialog(tempTodo);
        if (okClicked) {
            if(tempTodo.isDone())
                mainApp.getAllCompletedTask().add(tempTodo);
            else
                mainApp.getAllIncompletedTask().add(tempTodo);
        }
    }

    @FXML
    public void saveAllTask(){
        mainApp.saveAllTasks();
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        System.out.println("print main");
        IncompleteTaskTable.setItems(mainApp.getAllIncompletedTask());
        taskCompletedTable.setItems(mainApp.getAllCompletedTask());
    }
}

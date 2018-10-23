package oliviercheah.tojava.taskmanager;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oliviercheah.tojava.taskmanager.model.Deadline;
import oliviercheah.tojava.taskmanager.model.Task;
import oliviercheah.tojava.taskmanager.model.Tasklist;
import oliviercheah.tojava.taskmanager.model.Todo;
import oliviercheah.tojava.taskmanager.util.Storage;
import oliviercheah.tojava.taskmanager.util.TaskManagerException;
import oliviercheah.tojava.taskmanager.util.Ui;
import oliviercheah.tojava.taskmanager.view.TaskOverviewController;
import oliviercheah.tojava.taskmanager.view.ViewTaskDialogController;

public class MainApp extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Storage storage;
    private Ui ui;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TaskManager Lvl 9");

        initRootLayout();
        showTaskOverview();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Load the page with all the existing tasks */

    public void showTaskOverview() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TaskOverview.fxml"));
            AnchorPane personOverview = loader.load();

            // Set Task Overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            TaskOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean showTaskDialog(Task task) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ViewTaskDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Tasks");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ViewTaskDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTask(task);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
           // return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void removeTask(Task task){
        CompletedTask.remove(task);
    }
    /**
     * Returns the main stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    private ObservableList<Task> inCompletedTask = FXCollections.observableArrayList();
    private ObservableList<Task> CompletedTask = FXCollections.observableArrayList();
    private ObservableList<Task> AllTask = FXCollections.observableArrayList();


    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        storage = new Storage("data/test.txt");
        try {
            inCompletedTask.addAll(storage.loadIncompletedTask());
            CompletedTask.addAll(storage.loadCompletedTask());
        //    AllTask.addAll(storage.load());
           // CompletedTask.addAll(storage.load());
        } catch (TaskManagerException e) {
            ui.showToUser("Problem reading file. Starting with an empty task list");
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Returns the data as an observable list of {@link Task}.
     */
    public ObservableList<Task> getAllIncompletedTask() {
        return inCompletedTask;
    }
    public ObservableList<Task> getAllCompletedTask() {
        return CompletedTask;
    }
    public ObservableList<Task> getAllTask() {
        AllTask.addAll(getAllCompletedTask());
        AllTask.addAll(getAllIncompletedTask());
        return AllTask;
    }

    public int getIndex(Task task){
        return inCompletedTask.indexOf(task);
    }
    public void saveAllTasks(){

        storage.save(getAllTask());
        //storage.save(CompletedTask);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

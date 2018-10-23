package oliviercheah.tojava.taskmanager.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oliviercheah.tojava.taskmanager.model.*;

import java.awt.*;


public class ViewTaskDialogController {
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField deadlineField;
    @FXML
    private CheckBox doneCheckBox;

    private Stage dialogStage;
    private Task task;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTask(Task task) {
        this.task = task;

        //Set Description to the dialog.
        descriptionField.setText(task.getDescription());

        //Set deadline to the dialog.
        if(task.getBy().isEmpty())
            deadlineField.setText("No Deadline for this tasks");
        else
            deadlineField.setText(task.getBy());

        doneCheckBox.setSelected(task.isDone());
    }


    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user click ok
     */
    @FXML
    private void handleOk(){
        task.setDescription(descriptionField.getText());
        task.setDone(doneCheckBox.isSelected());
        task.setBy(deadlineField.getText());
        okClicked = true;
        dialogStage.close();
    }
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}

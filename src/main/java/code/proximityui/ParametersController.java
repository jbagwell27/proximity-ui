package code.proximityui;

import code.proximityui.data.Card;
import code.proximityui.data.Parameters;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ParametersController implements Initializable {

    public Label cardname;
    public GridPane rootParamGrid;
    public CheckBox useOfficialArtChkBox;
    public GridPane threadSpinnerGrid;
    public CheckBox reminderTextChkBox;
    public Button submitButton;
    private ProximityModel model;
    public Spinner<Integer> threadSpinner;

    public void setModel(ProximityModel model) {
        this.model = model;
        update();
    }
    public ProximityModel getModel(){
        return this.model;
    }

    private void update() {
        cardname.setText(model.getCurrentCard().getName());
        setCurrentValues();
        setupSpinner();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGridLayout();

    }

    private void setGridLayout() {
        ColumnConstraints[] columns = new ColumnConstraints[2];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new ColumnConstraints();
            rootParamGrid.getColumnConstraints().add(columns[i]);
        }
        columns[0].setPercentWidth(50);
        columns[1].setPercentWidth(50);

        rootParamGrid.setHgap(20);
        rootParamGrid.setVgap(10);
    }

    private void setCurrentValues() {
        if (!model.getCurrentCard().getParameters().isUse_official_art())
            useOfficialArtChkBox.setIndeterminate(true);
        if (!model.getCurrentCard().getParameters().isReminder_text())
            reminderTextChkBox.setIndeterminate(true);

        useOfficialArtChkBox.setSelected(model.getCurrentCard().getParameters().isUse_official_art());
        reminderTextChkBox.setSelected(model.getCurrentCard().getParameters().isReminder_text());
    }

    public void setupSpinner() {
        ColumnConstraints c = new ColumnConstraints();
        ColumnConstraints c1 = new ColumnConstraints();
        c.setPercentWidth(25);
        c1.setPercentWidth(25);
        threadSpinnerGrid.getColumnConstraints().add(c);
        threadSpinnerGrid.getColumnConstraints().add(c1);
        threadSpinnerGrid.setHgap(5);
        threadSpinnerGrid.setVgap(5);
        threadSpinner = new Spinner<>();
        threadSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, model.getCurrentCard().getParameters().getThreads(), 1));
        threadSpinner.setMaxWidth(75);
        threadSpinnerGrid.add(threadSpinner, 0, 0);

    }


    public void onSubmitButtonClick(ActionEvent actionEvent) {
        model.getCurrentCard().getParameters().setUse_official_art(useOfficialArtChkBox.isSelected());
        model.getCurrentCard().getParameters().setReminder_text(reminderTextChkBox.isSelected());
        model.getCurrentCard().getParameters().setThreads(threadSpinner.getValue());
        model.updateSelectedCard();

        submitButton.getScene().getWindow().hide();

    }
}

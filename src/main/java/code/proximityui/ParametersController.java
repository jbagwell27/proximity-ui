package code.proximityui;

import code.proximityui.data.Card;
import code.proximityui.data.Parameters;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    private Card card;
    private Parameters parameters;

    public void setCardInformation(Card c) {
        card = c;
        update();
    }

    private void update() {
        cardname.setText(card.getName());
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

    public void setupSpinner(){
        ColumnConstraints c = new ColumnConstraints();
        ColumnConstraints c1 = new ColumnConstraints();
        c.setPercentWidth(25);
        c1.setPercentWidth(25);
        threadSpinnerGrid.getColumnConstraints().add(c);
        threadSpinnerGrid.getColumnConstraints().add(c1);
        Spinner<Integer> threadSpinner = new Spinner<>();
        threadSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50,card.getParameters().getThreads(),1));
        threadSpinner.setMaxWidth(55);
        threadSpinnerGrid.add(threadSpinner,0,0);
    }


}

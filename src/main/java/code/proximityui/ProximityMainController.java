package code.proximityui;

import code.proximityui.data.Card;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProximityMainController implements Initializable {

    public TextArea cardSelectedParameters;
    public GridPane rootGrid;
    public ListView cardSelectedListView;
    public SearchableComboBox cardAvailableComboBox;

    private ArrayList<String> cardAvailableNames;
    private ArrayList<Card> cardSelectedArrayList;


    /***
     * Fills the ComboBox List with all of the cards. Preferably pulled from mtgjson, or some other database of card names.
     */
    private void fillCardAvailableComboBox() {
        if (!cardAvailableNames.isEmpty() && !cardAvailableNames.equals(null)) {
            cardAvailableComboBox.getItems().addAll(cardAvailableNames);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cardAvailableNames = new ArrayList<>();
        cardSelectedArrayList = new ArrayList<>();
        cardAvailableNames.add("Sol Ring");
        cardAvailableNames.add("Avacyn, Angel of Hope");
        cardAvailableNames.add("Forest");
        cardAvailableNames.add("Mana Crypt");
        fillCardAvailableComboBox();
        setGridLayout();
        addCardSelectionChangedListener();

    }

    private void setGridLayout() {
        ColumnConstraints[] columns = new ColumnConstraints[3];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new ColumnConstraints();
            rootGrid.getColumnConstraints().add(columns[i]);
        }
        columns[0].setPercentWidth(30);
        columns[1].setPercentWidth(30);
        columns[2].setPercentWidth(40);

        rootGrid.setHgap(20);
        rootGrid.setVgap(10);
    }

    private void addCardSelectionChangedListener() {

        cardSelectedListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {

                if (cardSelectedListView.getSelectionModel().getSelectedItem() != null) {

                    int index = cardSelectedListView.getSelectionModel().getSelectedIndex();

                    cardSelectedParameters.setText(cardSelectedArrayList.get(index).getName());
                }
            }
        });
    }

    /***
     * Adds the selected card in the availableCardsList to the selected card list.
     * @param actionEvent
     */
    public void onAddCardButtonClick(ActionEvent actionEvent) {
        String selectedCardName = cardAvailableComboBox.getSelectionModel().getSelectedItem().toString();

        cardSelectedListView.getItems().add(selectedCardName);
        cardSelectedArrayList.add(new Card(selectedCardName));

    }

    /***
     * Removes the selected item from the Selected Cards List
     * @param actionEvent
     */
    public void removeSelectedCard(ActionEvent actionEvent) {
        if (cardSelectedListView.getSelectionModel().getSelectedItem() != null || cardSelectedArrayList.size() > 0) {
            int index = cardSelectedListView.getSelectionModel().getSelectedIndex();
            System.out.println(String.format("Index %s", index));
            cardSelectedListView.getItems().remove(index);
            cardSelectedArrayList.remove(index);
        }
    }

    /***
     * Double Clicking on the selected card will show a parameter window with all elements
     * @param mouseEvent
     */
    public void onCardSelectedDoubleClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            if (cardSelectedListView.getSelectionModel().getSelectedItem() != null) {

                FXMLLoader paramViewLoader = new FXMLLoader(getClass().getResource("parameters-view.fxml"));
                try {
                    Parent root = paramViewLoader.load();

                    ParametersController paramController = paramViewLoader.getController();
                    Card currentCard = cardSelectedArrayList.get(cardSelectedListView.getSelectionModel().getSelectedIndex());
                    paramController.setCardInformation(currentCard);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(currentCard.getName());
                    stage.show();
                } catch (IOException e) {
                    System.out.println("IO Exception was thrown when launching parameters window");
                    e.printStackTrace();
                }


//                String selectedCard = cardSelectedListView.getSelectionModel().getSelectedItem().toString();
//                System.out.println(selectedCard);
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Parameters");
//                alert.setHeaderText(String.format("Parameters for %s", selectedCard));
//                alert.setContentText("This is where parameters will go. Checkboxes, Dropdowns, free form text fields");
//                alert.showAndWait();
            }
        }


    }


//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }


}
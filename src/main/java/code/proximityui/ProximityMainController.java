package code.proximityui;

import code.proximityui.data.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.SearchableComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProximityMainController implements Initializable {

    @FXML
    public TextArea selectedCardParameters;
    @FXML
    private SearchableComboBox availableCardsList;
    private ArrayList<String> allCardNames;

    @FXML
    public ListView selectedCardsList;
    private ArrayList<Card> selectedCards;


    /***
     * Fills the ComboBox List with all of the cards. Preferably pulled from mtgjson, or some other database of card names.
     */
    private void fillAllCardsList() {
        if (!allCardNames.isEmpty() && !allCardNames.equals(null)) {
            availableCardsList.getItems().addAll(allCardNames);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCardNames = new ArrayList<>();
        selectedCards = new ArrayList<>();
        allCardNames.add("Sol Ring");
        allCardNames.add("Avacyn, Angel of Hope");
        allCardNames.add("Forest");
        allCardNames.add("Mana Crypt");
        fillAllCardsList();
        setupSelectedParameters();

    }

    private void setupSelectedParameters() {
        selectedCardParameters.setStyle("");
    }

    /***
     * Adds the selected card in the availableCardsList to the selected card list.
     * @param actionEvent
     */
    public void onAddCardButtonClick(ActionEvent actionEvent) {
        String selectedCardName = availableCardsList.getSelectionModel().getSelectedItem().toString();

        selectedCardsList.getItems().add(selectedCardName);
        selectedCards.add(new Card(selectedCardName));

    }

    /***
     * Removes the selected item from the Selected Cards List
     * @param actionEvent
     */
    public void removeSelectedCard(ActionEvent actionEvent) {
        if (selectedCardsList.getSelectionModel().getSelectedItem() != null) {
            selectedCardsList.getItems().remove(selectedCardsList.getSelectionModel().getSelectedItem());
        }
    }

    /***
     * Double Clicking on the selected card will show a parameter window with all elements
     * @param mouseEvent
     */
    public void onSelectedCardDoubleClick(MouseEvent mouseEvent) {
        if (selectedCardsList.getSelectionModel().getSelectedItem() != null) {
            if (mouseEvent.getClickCount() == 2) {
                String selectedCard = selectedCardsList.getSelectionModel().getSelectedItem().toString();
                System.out.println(selectedCard);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Parameters");
                alert.setHeaderText(String.format("Parameters for %s", selectedCard));
                alert.setContentText("This is where parameters will go. Checkboxes, Dropdowns, free form text fields");
                alert.showAndWait();
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
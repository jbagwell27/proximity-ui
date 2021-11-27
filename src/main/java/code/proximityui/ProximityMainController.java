package code.proximityui;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class ProximityMainController implements Initializable {

    public TextArea cardSelectedParamsText;
    public GridPane rootGrid;
    public ListView cardSelectedListView;
    public SearchableComboBox cardAvailableComboBox;
    public TextArea globalParamsText;

    private ProximityModel model;


    /***
     * Fills the ComboBox List with all of the cards. Preferably pulled from mtgjson, or some other database of card names.
     */
    private void fillCardAvailableComboBox() {

        if (!model.getCardAvailableNames().isEmpty() && model.getCardAvailableNames() != null) {
            cardAvailableComboBox.getItems().addAll(model.getCardAvailableNames());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        model = new ProximityModel();

        model.getCardAvailableNames().add("Sol Ring");
        model.getCardAvailableNames().add("Avacyn, Angel of Hope");
        model.getCardAvailableNames().add("Forest");
        model.getCardAvailableNames().add("Mana Crypt");
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

        cardSelectedListView.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {

                if (cardSelectedListView.getSelectionModel().getSelectedItem() != null) {

                    System.out.println("Selected ListView Index: " + cardSelectedListView.getSelectionModel().getSelectedIndex());
                    model.setCurrentCard(cardSelectedListView.getSelectionModel().getSelectedIndex());
                    System.out.println("Current Card Array Index: " + model.getCardSelectedArrayList().indexOf(model.getCurrentCard()));


                    cardSelectedParamsText.setText(model.getCurrentCard().getParameters().toString());
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

        selectedCardListAdd(selectedCardName);
        cardSelectedListView.refresh();
    }

    /***
     * Removes the selected item from the Selected Cards List
     * @param actionEvent
     */
    public void removeSelectedCard(ActionEvent actionEvent) {
        if (cardSelectedListView.getSelectionModel().getSelectedItem() != null || model.getCardSelectedArrayList().size() > 0) {
            int index = cardSelectedListView.getSelectionModel().getSelectedIndex();
            System.out.println(String.format("Index %s", index));
            selectedCardListRemove(index);
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

                    CardParametersController paramController = paramViewLoader.getController();
                    paramController.setModel(model);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(model.getCurrentCard().getName());
                    stage.showAndWait();

                    model = paramController.getModel();
                    cardSelectedParamsText.setText(model.getCurrentCard().getParameters().toString());


                } catch (IOException e) {
                    System.out.println("IO Exception was thrown when launching parameters window");
                    e.printStackTrace();
                }
            }
        }
    }

    private void selectedCardListAdd(String cardName) {
        cardSelectedListView.getItems().add(cardName);
        System.out.println("Card ListView: " + cardSelectedListView.getItems().size());
        model.addCardSelectedItem(cardName);
        System.out.println("Card Array: " + model.getCardSelectedArrayList().size());
    }

    private void selectedCardListRemove(int index) {
        cardSelectedListView.getItems().remove(index);
        model.getCardSelectedArrayList().remove(index);
    }
}
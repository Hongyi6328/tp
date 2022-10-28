package seedu.address.ui.listpanels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Buyer;
import seedu.address.ui.UiPart;
import seedu.address.ui.displaycards.BuyerCard;


/**
 * Panel containing the list of Buyers.
 */
public class BuyerListPanel extends UiPart<Region> {
    private static final String FXML = "BuyerListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(BuyerListPanel.class);

    @FXML
    private ListView<Buyer> buyerListView;


    /**
     * Creates a {@code BuyerListPanel} with the given {@code ObservableList}.
     */
    public BuyerListPanel(ObservableList<Buyer> buyerList, Logic logic) {
        super(FXML);
        buyerListView.setItems(buyerList);
        buyerListView.setCellFactory(listView -> new BuyerListViewCell(logic));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Buyer} using a {@code BuyerCard}.
     */
    private static class BuyerListViewCell extends ListCell<Buyer> {
        private final Logic logic;

        private BuyerListViewCell(Logic logic) {
            this.logic = logic;
        }

        @Override
        protected void updateItem(Buyer buyer, boolean empty) {
            super.updateItem(buyer, empty);

            if (empty || buyer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BuyerCard(buyer, getIndex() + 1, logic).getRoot());
            }
        }
    }

}


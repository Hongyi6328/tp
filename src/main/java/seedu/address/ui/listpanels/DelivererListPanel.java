package seedu.address.ui.listpanels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Deliverer;
import seedu.address.ui.UiPart;
import seedu.address.ui.displaycards.DelivererCard;


/**
 * Panel containing the list of Deliverers.
 */
public class DelivererListPanel extends UiPart<Region> {
    private static final String FXML = "DelivererListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(DelivererListPanel.class);

    @FXML
    private ListView<Deliverer> delivererListView;


    /**
     * Creates a {@code DelivererListPanel} with the given {@code ObservableList}.
     */
    public DelivererListPanel(ObservableList<Deliverer> delivererList, Logic logic) {
        super(FXML);
        delivererListView.setItems(delivererList);
        delivererListView.setCellFactory(listView -> new DelivererListViewCell(logic));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Deliverer} using a {@code DelivererCard}.
     */
    private static class DelivererListViewCell extends ListCell<Deliverer> {

        private final Logic logic;

        public DelivererListViewCell(Logic logic) {
            this.logic = logic;
        }

        @Override
        protected void updateItem(Deliverer deliverer, boolean empty) {
            super.updateItem(deliverer, empty);

            if (empty || deliverer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DelivererCard(deliverer, getIndex() + 1, logic).getRoot());
            }
        }
    }

}

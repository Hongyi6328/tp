package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommands.AddCommandWithPopup;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalDeliverers;
import seedu.address.testutil.TypicalSuppliers;

public class AddCommandWithPopupTest {
    private Model bModel = new ModelManager(TypicalBuyers.getTypicalBuyerAddressBook(), new UserPrefs());
    private Model dModel = new ModelManager(TypicalDeliverers.getTypicalDelivererAddressBook(), new UserPrefs());
    private Model sModel = new ModelManager(TypicalSuppliers.getTypicalSupplierAddressBook(), new UserPrefs());


    @Test
    public void execute() {
        //For Buyers
        String toAdd = "BUYER";
        AddCommandWithPopup command = new AddCommandWithPopup(toAdd);
        CommandResult expected = CommandResult.createAddByPopupCommandResult(String
                .format(AddCommandWithPopup.MESSAGE_SUCCESS, toAdd), toAdd);
        assertEquals(command.execute(bModel), expected);

        //For Deliverers
        toAdd = "DELIVERER";
        command = new AddCommandWithPopup(toAdd);
        expected = CommandResult.createAddByPopupCommandResult(String.format(AddCommandWithPopup
                .MESSAGE_SUCCESS, toAdd), toAdd);
        assertEquals(command.execute(dModel), expected);

        //For Suppliers
        toAdd = "SUPPLIER";
        command = new AddCommandWithPopup(toAdd);
        expected = CommandResult.createAddByPopupCommandResult(String.format(AddCommandWithPopup
                .MESSAGE_SUCCESS, toAdd), toAdd);
        assertEquals(command.execute(sModel), expected);
    }
}
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.Supplier;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their category and index used in the displayed person list.\n"
            + "Parameters: c/PERSON_CATEGORY i/INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " c/Buyer i/1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_DELETE_PERSON_FAILURE = "Unable to execute Delete Command.";

    private final PersonCategory personCategory;
    private final Index targetIndex;

    public DeleteCommand(PersonCategory personCategory, Index targetIndex) {
        this.personCategory = personCategory;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<? extends Person> lastShownList;

        switch (personCategory.toString()) {
        case "Buyer":
            lastShownList = model.getFilteredBuyerList();
            break;
        case "Deliverer":
            lastShownList = model.getFilteredDelivererList();
            break;
        case "Supplier":
            lastShownList = model.getFilteredSupplierList();
            break;
        default:
            throw new CommandException(MESSAGE_DELETE_PERSON_FAILURE);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());

        switch (personCategory.toString()) {
        case "Buyer":
            model.deleteBuyer((Buyer) personToDelete);
            break;
        case "Deliverer":
            model.deleteDeliverer((Deliverer) personToDelete);
            break;
        case "Supplier":
            model.deleteSupplier((Supplier) personToDelete);
            break;
        default:
            throw new CommandException(MESSAGE_DELETE_PERSON_FAILURE);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}

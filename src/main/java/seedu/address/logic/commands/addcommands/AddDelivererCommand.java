package seedu.address.logic.commands.addcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Deliverer;

/**
 * Adds a deliverer to the address book.
 */
public class AddDelivererCommand extends Command {

    public static final String COMMAND_WORD = "add-d";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deliverer to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New deliverer added: %1$s";
    public static final String MESSAGE_DUPLICATE_DELIVERER = "This deliverer already exists in the address book";

    private final Deliverer toAdd;

    /**
     * Creates an AddDelivererCommand to add the specified {@code Deliverer}.
     */
    public AddDelivererCommand(Deliverer toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDeliverer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DELIVERER);
        }

        model.addDeliverer(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDelivererCommand // instanceof handles nulls
                && toAdd.equals(((AddDelivererCommand) other).toAdd));
    }
}

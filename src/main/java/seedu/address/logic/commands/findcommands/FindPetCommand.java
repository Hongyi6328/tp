package seedu.address.logic.commands.findcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;

/**
 * Finds and lists all pets in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPetCommand extends Command {
    public static final String COMMAND_WORD = "find-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all pets whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "Ashy";

    private final PetNameContainsKeywordsPredicate<Pet> predicate;

    /**
     * Constructs a FindPetCommand.
     *
     * @param predicate A Predicate for Pets.
     * @return FindCommand.
     */
    public FindPetCommand(PetNameContainsKeywordsPredicate<Pet> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPetList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPetCommand // instanceof handles nulls
                && predicate.equals(((FindPetCommand) other).predicate)); // state checck
    }
}

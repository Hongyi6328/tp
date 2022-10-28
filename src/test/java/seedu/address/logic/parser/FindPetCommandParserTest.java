package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.findcommands.FindPetCommand;
import seedu.address.logic.parser.findcommandparser.FindPetCommandParser;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;

public class FindPetCommandParserTest {
    private FindPetCommandParser parser = new FindPetCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPetCommand() {
        // no leading and trailing whitespaces
        FindPetCommand expectedFindCommand =
                new FindPetCommand(new PetNameContainsKeywordsPredicate<>(Arrays.asList("Doja-cat", "Putu")));
        assertParseSuccess(parser, "Doja-cat Putu", expectedFindCommand);
        assertParseSuccess(parser, "\n Doja-cat \n \t Putu  \t", expectedFindCommand);
    }
}

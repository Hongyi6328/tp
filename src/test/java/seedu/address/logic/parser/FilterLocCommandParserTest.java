package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.filtercommands.FilterLocCommand;
import seedu.address.logic.parser.filtercommandparser.FilterLocCommandParser;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;

public class FilterLocCommandParserTest {
    private FilterLocCommandParser parser = new FilterLocCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterLocCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPetCommand() {
        FilterLocCommand expectedFindCommand =
                new FilterLocCommand(new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")),
                        new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")),
                        new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")));
        assertParseSuccess(parser, "Singapore", expectedFindCommand);
        assertParseSuccess(parser, "\n Singapore \n \t  \t", expectedFindCommand);
    }
}

package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

//import seedu.address.logic.commands.addcommand.AddBuyerCommand;
//import seedu.address.logic.commands.addcommand.AddSupplierCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.deletecommands.DeleteBuyerCommand;
import seedu.address.logic.commands.deletecommands.DeleteDelivererCommand;
import seedu.address.logic.commands.deletecommands.DeleteSupplierCommand;
import seedu.address.logic.commands.filtercommands.FilterLocCommand;
import seedu.address.logic.commands.filtercommands.FilterOrderCommand;
import seedu.address.logic.commands.filtercommands.FilterPetCommand;
import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.commands.findcommands.FindPetCommand;
import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.findcommandparser.FindBuyerCommandParser;
import seedu.address.logic.parser.findcommandparser.FindDelivererCommandParser;
import seedu.address.logic.parser.findcommandparser.FindSupplierCommandParser;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.predicates.AdditionalRequestPredicate;
import seedu.address.model.order.predicates.OrderStatusPredicate;
import seedu.address.model.order.predicates.PriceRangePredicate;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.predicates.LocationContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.ColorContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PetNameContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.PriceContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.SpeciesContainsKeywordsPredicate;
import seedu.address.model.pet.predicates.VaccinationStatusPredicate;
//import seedu.address.testutil.PersonUtil;
//import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalPersonCategories;
//import seedu.address.testutil.TypicalSuppliers;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    //    @Test
    //    public void parseCommand_addBuyer() throws Exception {
    //        Buyer buyer = TypicalBuyers.ALICE;
    //        AddBuyerCommand command = (AddBuyerCommand) parser.parseCommand(PersonUtil.getAddCommand(buyer));
    //        assertEquals(new AddBuyerCommand(buyer, new ArrayList<>()), command);
    //    }

    /*
    @Test
    public void parseCommand_addDeliverer() throws Exception {
        Deliverer deliverer = TypicalDeliverers.BENSON;
        AddDelivererCommand command = (AddDelivererCommand) parser.parseCommand(PersonUtil.getAddCommand(deliverer));
        assertEquals(new AddDelivererCommand(deliverer), command);
    }

     */

    //    @Test
    //    public void parseCommand_addSupplier() throws Exception {
    //        Supplier supplier = TypicalSuppliers.ALICE;
    //        AddSupplierCommand command = (AddSupplierCommand) parser.parseCommand(PersonUtil.getAddCommand(supplier));
    //        assertEquals(new AddSupplierCommand(supplier, new ArrayList<>()), command);
    //    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteBuyer() throws Exception {
        DeleteBuyerCommand command = (DeleteBuyerCommand) parser.parseCommand(
                DeleteBuyerCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteBuyerCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteDeliverer() throws Exception {
        DeleteDelivererCommand command = (DeleteDelivererCommand) parser.parseCommand(
                DeleteDelivererCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteDelivererCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteSupplier() throws Exception {
        DeleteSupplierCommand command = (DeleteSupplierCommand) parser.parseCommand(
                DeleteSupplierCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteSupplierCommand(INDEX_FIRST), command);
    }

    /*
    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

     */

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filterLoc() throws Exception {
        FilterLocCommand command = new FilterLocCommand(new LocationContainsKeywordsPredicate<>(
                Arrays.asList("Singapore")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")),
                new LocationContainsKeywordsPredicate<>(Arrays.asList("Singapore")));
        assertEquals(parser.parseCommand(FilterLocCommand.COMMAND_WORD + " Singapore"), command);
    }

    @Test
    public void parseCommand_filterPet() throws Exception {
        ColorContainsKeywordsPredicate colorContainsKeywordsPredicate = new ColorContainsKeywordsPredicate(
                Arrays.asList("grey"));
        PetNameContainsKeywordsPredicate petNameContainsKeywordsPredicate = new PetNameContainsKeywordsPredicate(
                Arrays.asList("ashy"));
        PriceContainsKeywordsPredicate priceContainsKeywordsPredicate = new PriceContainsKeywordsPredicate(
                Arrays.asList(5.5));
        SpeciesContainsKeywordsPredicate speciesContainsKeywordsPredicate = new SpeciesContainsKeywordsPredicate(
                Arrays.asList("cat"));
        VaccinationStatusPredicate vaccinationStatusPredicate = new VaccinationStatusPredicate(true);
        FilterPetCommand command = new FilterPetCommand(
                colorContainsKeywordsPredicate,
                petNameContainsKeywordsPredicate,
                priceContainsKeywordsPredicate,
                speciesContainsKeywordsPredicate,
                vaccinationStatusPredicate);
        String input = FilterPetCommand.COMMAND_WORD + " c/grey n/ashy p/5.5 v/true";
        assertEquals(parser.parseCommand(input), command);
    }

    @Test
    public void parseCommand_filterOrder() throws Exception {
        AdditionalRequestPredicate additionalRequestPredicate = new AdditionalRequestPredicate(Arrays.asList("fat"));
        OrderStatusPredicate orderStatusPredicate = new OrderStatusPredicate(OrderStatus.DELIVERING);
        PriceRangePredicate priceRangePredicate = new PriceRangePredicate(new Price(34.5), new Price(79.9));
        FilterOrderCommand command = new FilterOrderCommand(
                additionalRequestPredicate,
                orderStatusPredicate,
                priceRangePredicate);
        String input = FilterOrderCommand.COMMAND_WORD + " ar/fat os/Delivering pr/34.5-79.9";
        assertEquals(parser.parseCommand(input), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String input = " n/foo";
        Predicate<Buyer> buyerPredicate = PredicateParser.parseBuyer(input);
        Predicate<Deliverer> delivererPredicate = PredicateParser.parseDeliverer(input);
        Predicate<Supplier> supplierPredicate = PredicateParser.parseSupplier(input);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + input);
        FindCommand otherCommand = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                TypicalPersonCategories.PERSON_CATEGORY_BUYER);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findBuyer() throws Exception {
        String input = " n/foo";
        Predicate<Buyer> buyerPredicate = PredicateParser.parseBuyer(input);
        Predicate<Deliverer> delivererPredicate = new Predicate<Deliverer>() {
            @Override
            public boolean test(Deliverer deliverer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Supplier> supplierPredicate = new Predicate<Supplier>() {
            @Override
            public boolean test(Supplier supplier) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        FindCommand command = (FindCommand) parser.parseCommand(
                FindBuyerCommandParser.PARSE_WORD + input);
        FindCommand otherCommand = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                TypicalPersonCategories.PERSON_CATEGORY_BUYER);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findDeliverer() throws Exception {
        String input = " n/foo";
        Predicate<Buyer> buyerPredicate = new Predicate<Buyer>() {
            @Override
            public boolean test(Buyer buyer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Deliverer> delivererPredicate = PredicateParser.parseDeliverer(input);
        Predicate<Supplier> supplierPredicate = new Predicate<Supplier>() {
            @Override
            public boolean test(Supplier supplier) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        FindCommand command = (FindCommand) parser.parseCommand(
                FindDelivererCommandParser.PARSE_WORD + input);
        FindCommand otherCommand = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                TypicalPersonCategories.PERSON_CATEGORY_DELIVERER);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findSupplier() throws Exception {
        String input = " n/foo";
        Predicate<Buyer> buyerPredicate = new Predicate<Buyer>() {
            @Override
            public boolean test(Buyer buyer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Deliverer> delivererPredicate = new Predicate<Deliverer>() {
            @Override
            public boolean test(Deliverer deliverer) {
                return false;
            }
            public boolean equals(Object object) {
                return object instanceof Predicate;
            }
        };
        Predicate<Supplier> supplierPredicate = PredicateParser.parseSupplier(input);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindSupplierCommandParser.PARSE_WORD + input);
        FindCommand otherCommand = new FindCommand(buyerPredicate, delivererPredicate, supplierPredicate,
                TypicalPersonCategories.PERSON_CATEGORY_SUPPLIER);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_findPet() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPetCommand command = (FindPetCommand) parser.parseCommand(
                FindPetCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        FindPetCommand otherCommand = new FindPetCommand(new PetNameContainsKeywordsPredicate<>(keywords));
        assertTrue(parser.parseCommand(FindPetCommand.COMMAND_WORD + " Ashy") instanceof FindPetCommand);
        assertEquals(otherCommand, command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " all") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " b") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " d") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " s") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " o") instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " p") instanceof SortCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}

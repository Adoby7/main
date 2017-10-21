package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.AddressBook;
import seedu.address.model.EventList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEventList;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book and Event list have been cleared!";

    private ReadOnlyAddressBook previousAddressBook;
    private ReadOnlyEventList previousEventList;

    @Override
    public CommandResult executeUndoableCommand() {
        requireNonNull(model);
        this.previousAddressBook = new AddressBook(model.getAddressBook());
        this.previousEventList = new EventList(model.getEventList());
        model.resetData(new AddressBook(), new EventList());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    protected void undo() {
        requireAllNonNull(model, previousAddressBook);
        model.resetData(previousAddressBook, previousEventList);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    protected void redo() {
        requireNonNull(model);
        model.resetData(new AddressBook(), new EventList());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }
}

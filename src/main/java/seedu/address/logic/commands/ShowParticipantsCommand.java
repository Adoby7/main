package seedu.address.logic.commands;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.EventName;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.person.PersonJoinsEventsPredicate;

/**
 * Shows all the participants of an event.
 */
public class ShowParticipantsCommand extends Command {

    public static final String COMMAND_WORD = "showP";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the event identified by the index number used in the last event listing "
            + "and displays all participants the event has involved.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_PARTICIPANTS_SUCCESS = "Show Participants Successfully!";

    private final Index targetIndex;
    private ReadOnlyEvent eventToShow;

    public ShowParticipantsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() throws CommandException {

        List<ReadOnlyEvent> lastShownList = model.getFilteredEventList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        eventToShow = lastShownList.get(targetIndex.getZeroBased());

        EventName name = eventToShow.getEventName();

        String stringName = name.toString();

        String trimmedArgs = stringName.trim();

        String[] nameKeywords = trimmedArgs.split("\\s+");

        PersonJoinsEventsPredicate predicate = new PersonJoinsEventsPredicate(Arrays.asList(nameKeywords));

        model.updateFilteredPersonList(predicate);

        return new CommandResult(String.format(MESSAGE_SHOW_PARTICIPANTS_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowParticipantsCommand // instanceof handles nulls
                && this.targetIndex.equals(((ShowParticipantsCommand) other).targetIndex)); // state check
    }

}

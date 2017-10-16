package seedu.address.model.event;

import javafx.beans.property.ObjectProperty;
import seedu.address.model.person.Person;

import java.util.Set;

public interface ReadOnlyEvent {
    ObjectProperty<EventName> EnameProperty();
    EventName getEName();
    ObjectProperty<EventDescription> descProperty();
    EventDescription getDesc();
    ObjectProperty<EventTime> timeProperty();
    EventTime getETime();
    ObjectProperty<ParticipantList> participantProperty();
    Set<Person> getParticipants();

    /**
     * returns true if both event has the same state
     */
    default boolean EventIsSameStateAs(ReadOnlyEvent other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getEName().equals(this.getEName()) // state checks here onwards
                && other.getETime().equals(this.getETime())
    }

    /**
     * Formats the event as text, showing all event details.
     */
    default String getEventAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEName())
                .append(" Description: ")
                .append(getDesc())
                .append(" Time: ")
                .append(getETime())
                .append(" Participants: ");
        getParticipants().forEach(builder::append);
        return builder.toString();
    }
}

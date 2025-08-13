package university.jala.eventmanager;

import java.util.Objects;

/**
 * Represents an event with a unique name and processing time.
 */
public class Event {
    private final String name;
    private final int processingTime; // in milliseconds
    private boolean processed;

    public Event(String name, int processingTime) {
        this.name = name;
        this.processingTime = processingTime;
        this.processed = false;
    }

    public String getName() {
        return name;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "{" + name + ", " + processingTime + "ms}";
    }
}
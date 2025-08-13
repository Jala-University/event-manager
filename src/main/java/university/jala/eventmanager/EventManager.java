package university.jala.eventmanager;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class EventManager {
    private final Set<Event> allEvents = new HashSet<>();
    private final Queue<Event> eventQueue = new ArrayDeque<>();
    private final Set<Event> processedEvents = new HashSet<>();

    public boolean addEvent(Event event) {
        if (allEvents.add(event)) {
            eventQueue.add(event);
            return true;
        }
        return false;
    }

    public Set<Event> getQueuedEvents() {
        return new HashSet<>(eventQueue);
    }

    public Event getNextEvent() {
        return eventQueue.peek();
    }

    public Event dequeueEvent() {
        return eventQueue.poll();
    }

    public void addProcessedEvent(Event event) {
        processedEvents.add(event);
    }

    public Set<Event> getProcessedEvents() {
        return new HashSet<>(processedEvents);
    }
}
package university.jala.eventmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {
    private EventManager eventManager;
    private final Event event1 = new Event("Event1", 100);
    private final Event event2 = new Event("Event2", 200);

    @BeforeEach
    void setUp() {
        eventManager = new EventManager();
    }

    @Test
    void testAddEvent() {
        assertTrue(eventManager.addEvent(event1));
        assertFalse(eventManager.addEvent(event1)); // Duplicate
        assertTrue(eventManager.addEvent(event2));
    }

    @Test
    void testGetQueuedEvents() {
        eventManager.addEvent(event1);
        eventManager.addEvent(event2);

        Set<Event> queuedEvents = eventManager.getQueuedEvents();
        assertEquals(2, queuedEvents.size());
        assertTrue(queuedEvents.contains(event1));
        assertTrue(queuedEvents.contains(event2));
    }

    @Test
    void testProcessEvent() {
        eventManager.addEvent(event1);
        eventManager.addEvent(event2);

        Event dequeued = eventManager.dequeueEvent();
        assertEquals(event1, dequeued);

        // Should not be in processed set until explicitly added
        Set<Event> processed = eventManager.getProcessedEvents();
        assertTrue(processed.isEmpty());
    }

    @Test
    void testGetProcessedEvents() {
        eventManager.addEvent(event1);
        Event dequeued = eventManager.dequeueEvent();
        eventManager.addProcessedEvent(dequeued);

        Set<Event> processed = eventManager.getProcessedEvents();
        assertEquals(1, processed.size());
        assertTrue(processed.contains(event1));
    }
}
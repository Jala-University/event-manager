package university.jala.eventmanager;

/**
 * Processes events by simulating the processing time.
 */
public class EventProcessor {

    /**
     * Processes an event by sleeping for its processing time.
     *
     * @param event the event to process
     */
    public static void processEvent(Event event) {
        if (event == null) return;

        try {
            // Simulate processing by sleeping
            Thread.sleep(event.getProcessingTime());
            event.setProcessed(true);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Processing was interrupted: " + event.getName());
        }
    }
}
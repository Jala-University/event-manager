package university.jala.eventmanager;

import java.util.Scanner;
import java.util.Set;

public class EventManagerApp {
    private static final EventManager eventManager = new EventManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addEvent();
                case "2" -> viewQueuedEvents();
                case "3" -> processEvent();
                case "4" -> viewProcessedEvents();
                case "5" -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Exiting Event Manager...");
    }

    private static void printMenu() {
        System.out.println("\nEvent Manager Menu");
        System.out.println("1. Add Event");
        System.out.println("2. View Queued Events");
        System.out.println("3. Process Event");
        System.out.println("4. View Processed Events");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addEvent() {
        System.out.print("New Event: ");
        String name = scanner.nextLine();

        System.out.print("Processing Time (ms): ");
        int time = Integer.parseInt(scanner.nextLine());

        Event event = new Event(name, time);
        if (eventManager.addEvent(event)) {
            System.out.println("Event added successfully.");
        } else {
            System.out.println("Event already exists. Duplicates not allowed.");
        }
    }

    private static void viewQueuedEvents() {
        Set<Event> queuedEvents = eventManager.getQueuedEvents();
        if (queuedEvents.isEmpty()) {
            System.out.println("No queued events.");
            return;
        }

        System.out.println("Queued Events:");
        System.out.println(formatEvents(queuedEvents));
    }

    private static void processEvent() {
        Event nextEvent = eventManager.getNextEvent();
        if (nextEvent == null) {
            System.out.println("No events to process.");
            return;
        }

        System.out.println("Processing: " + nextEvent.getName());
        System.out.println("Time: " + nextEvent.getProcessingTime() + "ms");

        Event event = eventManager.dequeueEvent();
        EventProcessor.processEvent(event);
        eventManager.addProcessedEvent(event);

        System.out.println("Processing complete: " + event.getName());
    }

    private static void viewProcessedEvents() {
        Set<Event> processedEvents = eventManager.getProcessedEvents();
        if (processedEvents.isEmpty()) {
            System.out.println("No processed events.");
            return;
        }

        System.out.println("Processed Events:");
        System.out.println(formatEvents(processedEvents));
    }

    private static String formatEvents(Set<Event> events) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Event event : events) {
            sb.append(event);
            if (++count < events.size()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
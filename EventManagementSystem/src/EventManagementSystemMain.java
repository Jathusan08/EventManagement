import java.io.*;
import java.util.*;

public class EventManagementSystemMain {

  private static final LinkedHashMap<String,Event> eventList = new LinkedHashMap<>(); // LinkedHashMap orders collection based
                                                                                // on what you insert first
    private static final Scanner userInput = new Scanner(System.in);

    private static final String fileName = "EventManagement.data";

    public static void main(String[] args) {

        runEventManagementSystemApplication();


    }

    private static void menu(){ // DONE
        System.out.println("***************** MENU *************************");
        System.out.println("|*---------------------------------------------*|");
        System.out.println("|* (1). Add An Event                           *|");
        System.out.println("|* (2). List All the events in the System      *|");
        System.out.println("|* (3). List An Individual Event               *|");
        System.out.println("|* (4). Edit An Event                          *|");
        System.out.println("|* (5). Delete An Event                        *|");
        System.out.println("|* (6). List The Attendees Attending An Event  *|");
        System.out.println("|* (7). Add An Attendee To An Event            *|");
        System.out.println("|* (8). Delete An Attendee From An Event       *|");
        System.out.println("|* (9). Show Menu                              *|");
        System.out.println("|* (10). Close Event Management System         *|");
        System.out.println("|*---------------------------------------------*|");
        System.out.println("************************************************");

    }

    private static void listAllEvent() { // DONE

        if(eventList.isEmpty()){

            System.out.println("Event list is empty, Please add an Event");
        }

        else {

            for (Map.Entry<String, Event> eventSet : eventList.entrySet()) {

                //    Printing all elements of a Map
                System.out.println( " --> Event Name: " + eventSet.getKey());

            }

        }
    }


    private static void listIndividualEvent(String eventName){ // DONE

        try {
            if (!eventList.containsKey(eventName)) { // adding exception if the event does not exist in the list

                throw new EventException("Please type appropriate event Name in order to delete an Attendee");

            }
            else{
                System.out.println("EVENT NAME: " + eventList.get(eventName));

                if(eventList.get(eventName).getAttendeeList().isEmpty()){

                    System.out.println("There are no attendees for this event " + eventName);
                }

                else{

                    System.out.println("There are " + eventList.get(eventName).getAttendeeList().size() +
                            " attendees attending for this event");

                }

            }
        }

        catch (EventException e){

            System.out.println(e);
        }

    }

    private static void addEvent(Event event){ // DONE

        boolean checkEventExist = false;
        if(event != null) {

            for (Map.Entry<String, Event> eventSet : eventList.entrySet()) {

                if(eventSet.getValue().getEventName().equals(event.getEventName())){

                    checkEventExist = true;
                }

            }

            if(checkEventExist){

                System.out.println("Event already exist in the list, Please try with another event ");
            }

            else{
                System.out.println("New Event has been added to the list");
                eventList.put(event.getEventName(),event);


            }

        }

        else{

            System.out.println("Should not be Null, Please type an appropriate Event");
        }

    }

    private static void editEvent(String eventName){

        try {
            if (!eventList.containsKey(eventName)) { // adding exception if the event does not exist in the list

                throw new EventException("Please type appropriate event Name in order to edit an event");

            }
            else{
                int editOption;
                System.out.println("Please select 1 for edit event location");
                System.out.println("Please select 2 for edit description");
                System.out.println("Please select 3 for both");
                System.out.println();
                editOption = userInput.nextInt();
                String editEvent;

                switch(editOption){

                    case 1:
                        System.out.println("Please enter event location you would like to edit");
                        editEvent = userInput.next();
                        eventList.get(eventName).editLocation(editEvent);
                        System.out.println("You have succefully updated "+ eventName + " location");
                    break;

                    case 2:
                        System.out.println("Please enter event description you would like to edit");
                        editEvent = userInput.next();
                        eventList.get(eventName).editDescription(editEvent);
                        System.out.println("You have succefully updated "+ eventName + " description");
                    break;

                    case 3:
                        String eventLocation;
                        String eventDescription;
                        System.out.println("Please enter event location you would like to edit");
                        eventLocation = userInput.next();
                        System.out.println();
                        System.out.println("Please enter event description you would like to edit");
                        eventDescription = userInput.next();
                        eventList.get(eventName).editLocation(eventLocation);
                        eventList.get(eventName).editDescription(eventDescription);
                        System.out.println("You have succefully updated "+ eventName + " location and description");

                }

            }
        }

        catch (EventException e){

            System.out.println(e);
        }

    }

    private static void deleteEvent(String eventName){ // DONE

        try {
            if (!eventList.containsKey(eventName)) { // adding exception if the event does not exist in the list

                throw new EventException("Please type appropriate event Name in order to delete an Event");

            }
            else{
                System.out.println("You have succesfully deleted " + eventName + " event");
                eventList.remove(eventName);

            }
        }

        catch (EventException e){

            System.out.println(e);
        }

    }

    private static void listAttendeesAttendEvent(String eventName){

        try {
            if (!eventList.containsKey(eventName)) { // adding exception if the event does not exist in the list

                throw new EventException("Please type appropriate event Name in order to display all the Attendee attending the event.");

            }
            else{

                if(eventList.get(eventName).getAttendeeList().isEmpty()){


                    System.out.println("There are no attendees for this event " + eventName);
                }

                else{

                    System.out.println("There are " + eventList.get(eventName).getAttendeeList().size() +
                            " attendees attending for this event" );


                    for (Attendee attendee : eventList.get(eventName).getAttendeeList()) { // iterating through attendees for specifc event

                        System.out.println("Attendee ---> First Name: " + attendee.getFirstName()
                                + ", Last Name: " + attendee.getLastName() + ", Age: " + attendee.getAge());
                    }

                }

            }
        }

        catch (EventException e){

            System.out.println(e);
        }

    }


    private static void addAttendeeToEvent(String eventName, Attendee attendee) { // DONE

        try {
            if (!eventList.containsKey(eventName)) { // adding exception if the event does not exist in the list

                throw new EventException("Please type appropriate event Name in order to add an Attendee");

            }
            else{
                eventList.get(eventName).addAttendeeToEvent(attendee);

            }
        }

        catch (EventException e){

            System.out.println(e);
        }

    }

    private static void deleteAttendeeFromEvent(String eventName, String firstName, String lastName){ // DONE

        try {
            if (!eventList.containsKey(eventName)) { // adding exception if the event does not exist in the list

                throw new EventException("Please type appropriate event Name in order to delete an Attendee");

            }
            else{
                eventList.get(eventName).deleteAttendeeFromEvent(firstName, lastName);

            }
        }

        catch (EventException e){

            System.out.println(e);
        }

    }


    public static void runEventManagementSystemApplication(){

        String option;
        String eventName;
        String firstName;
        String lastName;
        int age;

        readDataFromFile();

        menu();
        label:
        while(true){// this is infinite loop when user enters 10 it will terminate the lool
            System.out.println();
            System.out.println("Please enter  an option: ");
            option = userInput.next();

            switch (option) {
                case "1":

                    System.out.println("ADD AN EVENT");
                    System.out.println("Please enter the event name");
                    eventName = userInput.next();
                    addEvent(new Event(eventName));
                    break;

                case "2":

                    System.out.println("SHOW ALL EVENTS");
                    System.out.println();
                    listAllEvent();
                    System.out.println();
                    break;

                case "3":

                    System.out.println("LIST AN INDIVIDUAL EVENT");
                    System.out.println("Please enter the event name you would like to search for");
                    eventName = userInput.next();
                    listIndividualEvent(eventName);
                    break;

                case "4":

                    System.out.println("EDIT AN EVENT");
                    System.out.println();
                    System.out.println("Please select an Event Name");
                    eventName = userInput.next();
                    editEvent(eventName);
                    break;

                case "5":

                    System.out.println("DELETE AN EVENT");
                    System.out.println();
                    System.out.println("Please enter the event name you would like to delete");
                    eventName = userInput.next();
                    deleteEvent(eventName);
                    break;

                case "6":

                    System.out.println("LIST THE ATTENDEES ATTENDING AN EVENT");
                    System.out.println();
                    System.out.println("Please enter the event name you would like in order to show the attendee who is attending the event");
                    eventName = userInput.next();
                    listAttendeesAttendEvent(eventName);
                    break;

                case "7":

                    System.out.println("ADD AN ATTENDEE TO AN EVENT");
                    System.out.println();
                    System.out.println("Please enter the event name you would like in order to add the attendee for the event");
                    eventName = userInput.next();
                    System.out.println();
                    System.out.println("Please enter the first name of the attendee");
                    firstName = userInput.next();
                    System.out.println();
                    System.out.println("Please enter the last name of the attendee");
                    lastName = userInput.next();
                    System.out.println();
                    System.out.println("Please enter the age of the attendee");

                    while (!userInput.hasNextInt()) { // checking if input int type if not it will iterate unless the user input the right type.
                        userInput.next();
                        System.out.println("Input is not a number.");
                    }
                    age = userInput.nextInt();
                    addAttendeeToEvent(eventName, new Attendee(firstName, lastName, age));
                    break;

                case "8":

                    System.out.println("DELETE AN ATTENDEE FROM AN EVENT");
                    System.out.println("Please enter the event name you would like in order to add the attendee for the event");
                    eventName = userInput.next();
                    System.out.println("Please enter the first name of the attendee");
                    firstName = userInput.next();
                    System.out.println("Please enter the last name of the attendee");
                    userInput.nextLine();
                    lastName = userInput.next();
                    deleteAttendeeFromEvent(eventName, firstName, lastName);
                    break;

                case "10":

                    writeDataToFile();

                    System.out.println("CLOSING THE EVENT MANAGEMENT SYSTEM");
                    break label;

                default:
                    System.out.println("PLEASE SEE THE MENU AGAIN:");
                    menu();
                    break;
            }
        } // END OF LOOP

        userInput.close();// Closing the scanner userinput safely

    }

    public static void readDataFromFile(){

        LinkedHashMap<String,Event> eventReadData = null;

        try
        {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            eventReadData = (LinkedHashMap<String, Event>) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();

        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }


        //System.out.println("Deserialized HashMap..");
        // Display content using Iterator
        Set set = eventReadData.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry addEventCollection = (Map.Entry)iterator.next();

            eventList.put((String)addEventCollection.getKey(),(Event)addEventCollection.getValue());
        }




    }



    public static void writeDataToFile() {


        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(eventList);
            oos.close();
            fos.close();
           // System.out.println("Serialised LinkedHashMap data is saved in EventManagement.data");


        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }


    }


}
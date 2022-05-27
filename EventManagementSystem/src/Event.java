import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    private final String eventName;
    private String location;
    private String description;
    private final ArrayList<Attendee> attendeeList;


    public Event(String eventName){

        this.eventName = eventName;
        this.attendeeList = new ArrayList<>();
        this.location = "Undefined location";
        this.description = "Undefined description";

    }


    public String getLocation() {
        return location;
    }

    public void editLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void editDescription(String description) {
        this.description = description;
    }

    public String getEventName() {
        return eventName;
    }

    public ArrayList<Attendee> getAttendeeList() {
        return attendeeList;
    }

    public boolean deleteAttendeeFromEvent(String firstName, String lastName){ // Deleting an existing customer from an event

        int indexOfAttendee = -1;

        for(int i = 0; i < this.attendeeList.size(); i++){

            if((attendeeList.get(i).getFirstName().equals(firstName))&&(attendeeList.get(i).getLastName().equals(lastName))){

                indexOfAttendee = i;
            }

        }

        if(indexOfAttendee >= 0){
            System.out.println("You have sucessfully deleted Attende" + this.attendeeList.get(indexOfAttendee));
            this.attendeeList.remove(indexOfAttendee);
            return true;
        }
        else{

            System.out.println("Sorry could not find the Attende's Details on the Event list.");
            return false;
        }

    }

    public boolean addAttendeeToEvent(Attendee attendee){

        boolean checkFirstNameExist = false;
        boolean checkLastNameExist = false;

        if(attendee != null){ //  we don't want the attendee object to be null

            for (Attendee value : this.attendeeList) {

                if (value.getFirstName().equals(attendee.getFirstName())) { // we're comparing if the first name exist

                    checkFirstNameExist = true;
                }

                if (value.getLastName().equals(attendee.getLastName())) {  // we're comparing if the last name exist

                    checkLastNameExist = true;
                }


            }

            if(checkFirstNameExist && checkLastNameExist){ // checking if both name true then we don't want to add to the event list

                System.out.println("Attendee with the first name " +attendee.getFirstName() + ", last name "
                        + attendee.getLastName() + " already exist in the event " + this.eventName );

                return false;
            }

            else{ // we're adding the new attended to the list if the firstname and surname does not match to the existing attendee

                this.attendeeList.add(attendee);
                System.out.println("new attendee has been added to list");
                return true;

            }

        }

        else{

            System.out.println("Should not be Null, Please add the appropriate attendee");
            return false;
        }

    }

    @Override
    public String toString() {
        return  "eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description;
    }
}

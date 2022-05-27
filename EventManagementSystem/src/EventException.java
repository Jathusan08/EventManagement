public class EventException extends Exception { // always extend to Exception class when you're creating your own
    // exception class

    // this is our own exception class

    private final String message; // here we're creating a string variable which can be an exception meessage


    public EventException(String message){ // here we're creating a paramertized constructor which takes String as an
        // argument
       // super(message);
        this.message = message;

    }

    @Override
    public String toString() { // here we're overriding toString() method.
        return message ;
    }
}

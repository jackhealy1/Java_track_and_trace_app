import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import  java.util.ArrayList;


//CONTROLLER CLASS IS FINAL
public final class Controller {

//     DO WE NEED TO MAKE THIS CLASS, ATTRIBUTES AND METHODS PRIVATE AND IMMUTABLE (FINAL)? - JH

        // Attributes
 private String csv;

   // Constructors

    protected Controller(String establishmentCSVFileURI)
    {
        this.csv = establishmentCSVFileURI;
    }

    protected Controller(){}


    //methods



    // ADD ESTABLISHMENT TO THE LIST OF ESTABLISHMENTS, THROW IO EXCEPTIONS
    protected boolean addEstablishment(Establishment establishment) throws IOException {

        //ASSIGN FILENAME VARIABLE
        String fileName = "data/establishments.csv";

        //IF THE notDuplicate METHOD RETURNS TRUE DO THIS
            if (notDuplicate(establishment.getName(), fileName)) {

                //CREATE FILEWRITER OBJECT WHICH INCLUDES FILE PATH AND SETS APPEND TO TRUE
                FileWriter csvWriter = new FileWriter(fileName, true);

                //ADD THE DETAILS OF THE ESTABLISHMENT TO THE CSV, FLUSH AND CLOSE THEN RETURN TRUE
                csvWriter.append(establishment.getName());
                csvWriter.append(",");
                csvWriter.append(establishment.getFirstLineAddress()); // UNSURE HOW TO PULL FIRST LINE
                csvWriter.append(",");
                csvWriter.append(establishment.getPostcode()); // UNSURE HOW TO PULL POSTCODE
                csvWriter.append(",");
                csvWriter.append(establishment.getMaxOccupancy().toString());
                csvWriter.append("\n");


                csvWriter.flush();
                csvWriter.close();

                return true;
            }

            else{
                //IF notDuplicate IS FALSE RETURN FALSE
                return false;
        }
    }


    // ADD EVENT TO THE LIST OF EVENTS, THROW IO EXCEPTIONS
    protected boolean addEvent(Event event) throws IOException
    {
        //ASSIGN FILENAME VARIABLE
        String fileName = "data/events.csv";

        //IF THE notDuplicate() METHOD RETURNS TRUE DO THIS
        if (notDuplicate(event.getEventID().toString(), fileName)) {

            //CREATE FILEWRITER OBJECT WHICH INCLUDES FILE PATH AND SETS APPEND TO TRUE
            FileWriter csvWriter = new FileWriter(fileName, true);

            //ADD THE DETAILS OF THE EVENT TO THE CSV, FLUSH AND CLOSE THEN RETURN TRUE
            csvWriter.append(event.getEventID().toString());
            csvWriter.append(",");
            csvWriter.append(event.getUser().getName());
            csvWriter.append(",");
            csvWriter.append(event.getUser().getDob().toString());
            csvWriter.append(",");
            csvWriter.append(event.getUser().getEmailAddress());
            csvWriter.append(",");
            csvWriter.append(event.getUser().getPhoneNumber());
            csvWriter.append(",");
            csvWriter.append(event.getUser().getAge().toString());
            csvWriter.append(",");
            csvWriter.append(event.getEventDate());
            csvWriter.append(",");
            csvWriter.append(event.getEventTime().toString());
            csvWriter.append(",");
            csvWriter.append(event.getEstablishment().getName());
            csvWriter.append(",");
            csvWriter.append(event.getEstablishment().getFirstLineAddress());
            csvWriter.append(",");
            csvWriter.append(event.getEstablishment().getPostcode());
            csvWriter.append(",");
            csvWriter.append(event.getEstablishment().getMaxOccupancy().toString());
            csvWriter.append("\n");

            csvWriter.flush();
            csvWriter.close();

            return true;
        }

        else{
            //IF notDuplicate() RETURNS FALSE RETURN FALSE
            return false;
        }
    }

    //retrieve the establishments from the csv as eastablishment object
    protected ArrayList<Establishment> getEstablishments() {

        // CREATE STRING ARRAYLIST OBJECT OF THE CSV CONTENTS. USE LOADCSVFILE METHOD FORM THE FILELOADER CLASS
        ArrayList<String> csvText = FileLoader.loadCSVFile("data/establishments.csv");

        //CHECK IF THE ARRAYLIST IS EMPTY
        assert csvText != null;

        //CREATE AN EMPTY ESTABLISHMENTS ARRAYLIST
        ArrayList<Establishment> establishments = new ArrayList<Establishment>();

        //LOOP THROUGH THE STRING ARRAYLIST
        for (String line : csvText) {

            //IF THE CURRENT LINE IS NOT NULL DO THIS, IF IT IS NULL DO NOT EXECUTE
            if (line != null) {
                //SPLIT THE STRING INTO SEPARATE VALUES, SEPARATE BY COMMAS AND SAVE INTO A STRING ARRAY
                String[] strings = line.split(",");

                //ADD THE SEPARATED STRINGS TO VARIABLES, CONVERTING TYPE IF NECESSARY
                String name = strings[0];
                String firstAddressLine = strings[1];
                String postcode = strings[2];
                Integer occupancy = Integer.parseInt(strings[3]);

                //CREATE ESTABLISHMENT OBJECT USING ABOVE VARIABLES
                Establishment establishment = new Establishment(name, firstAddressLine, postcode, occupancy);

                //ADD ESTABLISHMENT OBJECT TO THE ESTABLISHMENTS ARRAYLIST
                establishments.add(establishment);
            }
        }
        //RETURN THE ESTABLISHMENTS ARRAYLIST
        return establishments;
    }

    //retrieve the events from the csv
        protected ArrayList<Event> getEvent() {

            // CREATE STRING ARRAYLIST OBJECT OF THE CSV CONTENTS. USE LOADCSVFILE METHOD FORM THE FILELOADER CLASS
            ArrayList<String> csvText = FileLoader.loadCSVFile("data/events.csv");

            //CHECK IF THE ARRAYLIST IS EMPTY
            assert csvText != null;

            //CREATE AN EMPTY EVENTS ARRAYLIST
            ArrayList<Event> events = new ArrayList<Event>();

            //LOOP THROUGH THE STRING ARRAYLIST
            for (String line : csvText) {

                //IF THE CURRENT LINE IS NOT NULL DO THIS, IF IT IS NULL DO NOT EXECUTE
                if (line != null) {
                    //SPLIT THE STRING INTO SEPARATE VALUES, SEPARATE BY COMMAS AND SAVE INTO A STRING ARRAY
                    String[] strings = line.split(",");

                    //ADD THE SEPARATED STRINGS TO VARIABLES, CONVERTING TYPE IF NECESSARY
                    String eventID = strings[0];
                    String username = strings[1];
                    LocalDate dob = LocalDate.parse(strings[2], User.formatter); //
                    String email = strings[3];
                    String contactNumber = strings[4];
                    Integer age = Integer.parseInt(strings[5]);
                    LocalDate eventDate = LocalDate.parse(strings[6], User.formatter);
                    LocalTime eventTime = LocalTime.parse(strings[7]);
                    Integer partySize = Integer.parseInt(strings[8]);
                    String establishmentName = strings[9];
                    String firstLineAddress = strings[10];
                    String postcode = strings[11];
                    Integer maxOccupancy = Integer.parseInt(strings[12]);

                    //CREATE USER OBJECT
                    User user = new User(username, dob, email, contactNumber);
                    //CREATE ESTABLISHMENT OBJECT
                    Establishment establishment = new Establishment(establishmentName, firstLineAddress, postcode, maxOccupancy);
                    //CREATE EVENT OBJECT USING THE 2 OBJECTS ABOVE AND EVENT VARIABLES
                    Event event = new Event(user, eventDate, eventTime, partySize, establishment);
                    //ADD EVENT OBJECT TO EVENTS ARRAYLIST
                    events.add(event);
                }
            }
            //RETURN ARRAYLIST OF EVENTS
            return events;
        }

    //****** NEW - METHOD TO CHECK IF ENTERED ESTABLISHMENT/EVENT ALREADY EXISTS IN THE CSV FILE - JH

    private static boolean notDuplicate(String input, String filename) throws IOException {

        //CREATE STRING ARRAYLIST AND LOAD THE CSV FILE GIVEN BY THE FILENAME PARAMETER
        ArrayList<String> csvText = FileLoader.loadCSVFile(filename);

        // ASSERT THAT THE ARRAYLIST IS NOT EMPTY/NULL
        assert csvText != null;

        //LOOP THROUGH THE ARRAYLIST
        for (String line : csvText)
        {
            //IF THE CURRENT LINE IS NOT NULL DO THIS, IF IT IS NULL DO NOT EXECUTE
            if (line != null)
            {
                //SPLIT THE STRING INTO SEPARATE VALUES, SEPARATE BY COMMAS AND SAVE INTO A STRING ARRAY
                String[] values = line.split(",");

                //IF VALUES[0] (THE STORED EVENT ID) IS THE SAME AS THE NEW EVENT ID RETURN FALSE
                if (values[0].equalsIgnoreCase(input))
                {
                    return false;
                }
            }
        }
        // RETURN TRUE IF THE EVENT ID'S ARE DIFFERENT
        return true;
    }
}



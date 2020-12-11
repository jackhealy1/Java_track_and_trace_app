import java.time.LocalDate;
import java.time.LocalTime;

// NEW MAIN CLASS CREATED IN ORDER TO RUN TESTS

public class App {

    //MAIN METHOD FOR APPLICATION - JH

    public static void main(String[] args)
    {


        //CREATE A USER OBJECT - JH

        User Jack = new User
                (
                        "Jack Healy",
                        LocalDate.of(1985, 6, 8) ,
                        "jackhealy85@gmail.com",
                        "07515708720"
                );

        //CREATE AN ESTABLISHMENT OBJECT - JH

        Establishment TwoEgg = new Establishment
                (
                        "The Two Egg Arena",
                        "1263 Sycamore Terrace",
                        "NE1 7RT",
                        2633
                );

//        Establishment FiveSausage = new Establishment      // NEED TO INVESTIGATE HOW TO ADD STRING[] AS CONSTRUCTOR
//                (
//                        "The Five Sausage Arena",
//                        "15 BRIGHT CLOSE, NE30 EBDF",
//                        4567
//                );

        //CREATE AN EVENT OBJECT - JH

        Event TheBigOne = new Event
                (
                        Jack,
                        LocalDate.of(2020, 12, 10),
                        LocalTime.of(12, 30),
                        400,
                        TwoEgg
                );


        //BELOW ARE VARIOUS METHODS TO TEST THE BEHAVIOUR OF THE CLASSES - JH

//        System.out.println(Jack.displayUser());
//        System.out.println();
//        System.out.println(Jack.displayUserShortHand());
//        System.out.println();
//        System.out.println(Jack.getAge());
//        System.out.println();
//        System.out.println(TwoEgg.displayEstablishment());
//        System.out.println();
//        System.out.println(TheBigOne.getUser().getName());
//        System.out.println();
//        System.out.println(TheBigOne.getPartyNumber());
//        System.out.println(TheBigOne.getUser().displayUser());
//        System.out.println(TheBigOne.getEventId());
//        System.out.println(TheBigOne.getEstablishment().displayEstablishment());
//        System.out.println(TheBigOne.displayEvent());
//        System.out.println(TheBigOne.getEventDate());
//        System.out.println(TheBigOne.getEventTime());
    }
}
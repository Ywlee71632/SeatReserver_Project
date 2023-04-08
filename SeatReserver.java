import java.util.Scanner;

public class SeatReserver {

    private boolean[] seatsReserved;
    private boolean[] proposedSeats;

    /**
     * CONSTRUCTOR
     * @param totalSeats - the total number of seats available; used to create arrays seatsReserved and proposedSeats
     */
    public SeatReserver(int totalSeats){

        // create arrays with the same length of totalSeats
        seatsReserved = new boolean[totalSeats];
        proposedSeats = new boolean[totalSeats];
    }

    /**
     * Checks if the seat at index seatNum is reserved
     * @param seatNum the index of the seat whose status is being queried
     * @return false iff the seat is not reserved and true otherwise (including if seatNum is invalid/out of bounds)
     */
    public boolean isSeatReserved(int seatNum){
    
        // 1st condition: if the input is reserved, or is out of the range of the array
        if (seatNum < 0 || seatNum >= seatsReserved.length || seatsReserved [seatNum] == true) {
            return true;
        } 
        // 2nd condition: if the seat is not reserved yet
        else {
            return false;
        }

    }

    /**
     * Reserves a single seat located at seatNum
     * @param seatNum the index of the seat being reserved in the seatsReserved array
     */
    public void reserveIndivSeat(int seatNum){

        // Change the status of the seat into 'true' to represent that the seat is reserved
        seatsReserved[seatNum] = true; 
        
    }


    /**
     * Copies the values from copyFrom into copyInto (such that copyInto will be a copy of copyFrom)
     * @param copyFrom the array to be copied
     * @param copyInto the array which will be filled with the copy
     */
    public void copyArray(boolean[] copyFrom, boolean[] copyInto){
        
        // assign each value of copyFrom array to copyInto array in the same order
        for (int val = 0; val < copyFrom.length; val++) {
            copyInto[val] = copyFrom[val];
        }
        
    }


    /**
     * Checks if the total number of seats available (not reserved) is >= the number of seats that the user wants to reserve
     * @param numSeatsWanted the total number of seats the user wants to reserve
     * @return true iff the total number of seats availale in seatsReserved is greater than or equal to the number of seats wanted
     */
    private boolean nonContiguousAvailable(int numSeatsWanted){

        //count the available seats  
        int seatsAvailable = 0;
        for (int seat = 0; seat < seatsReserved.length; seat++){
            if (seatsReserved[seat] == false){
            seatsAvailable ++;
            }
        }

        //compare the number of available seats and seats wanted 
        if (seatsAvailable >= numSeatsWanted) {                  
            return true;
        } else {
            return false;
        }
    

    }

    /**
     * Checks if it would be possible to reserve numSeatsWanted seats. If it is, it modifies the proposedSeats array to reflect what seatsReserved would look like if the requested seats were reserved too.
     * @param numSeatsWanted the total number of seats that the user wants to reserve
     * @return false if it is not possible to reserve the seats or true if proposedSeats was successfully updated to show what seatsReserved would look like with the new seats reserved.
     */
    public boolean planNonContiguous(int numSeatsWanted){
        
        // check if it is possible to reserved numSeatsWanted seats
        if (nonContiguousAvailable(numSeatsWanted) == true) {

            // copy the array
            copyArray(seatsReserved, proposedSeats);

            //modify the array until the system completes reserving  numSeatsWanted seats 
            int reserved = 0;
                for (int seatNum = 0; reserved < numSeatsWanted; seatNum++){
                    if (proposedSeats[seatNum] == false) {
                        proposedSeats[seatNum] = true;
                        reserved ++;
                    }
                    
                }
            return true;
        }
        else{
            return false;
        }

    }
    
    
    /**
     * Function updates seatsReserved to match proposedSeats
     * 
     * HINT: This method should contain a single function call to a function you've already written!
     */
    public void confirmReservation(){

        // confirm the values of array by copying the values proposedSeats into seatsReserved
        copyArray(proposedSeats, seatsReserved);
    }

    /**
     * Function prints the seats currently reserved
     */
    public void printSeatsReserved(){
        System.out.println(java.util.Arrays.toString(this.seatsReserved));
    }

    /**
     * Function prints the seats which would be reserved if the proposed layout was accepted
     */
    public void printProposedSeats(){
        System.out.println(java.util.Arrays.toString(this.proposedSeats));
    }

    public static void main(String[] args){
        SeatReserver buyer = new SeatReserver(20);

        Scanner scanner = new Scanner(System.in);

        // Ask if they'd like to check a seat's availability, reserve a group, or quit
        System.out.println("Please select an option: (c)heck availability of and reserve a single seat, (r)eserve a group of seats, or (q)uit");
        String option = scanner.nextLine();

        while (!option.equals("q")){

            // if c for check status
            if (option.equals("c")){
                // Prompt for the seat number
                System.out.println("Enter a seat number to check");
                int seat = Integer.parseInt(scanner.nextLine());

                // Print whether it's available or not
                boolean seatReserved = buyer.isSeatReserved(seat);

                // If the seat is available (not reserved)
                if (!seatReserved) { 
                    System.out.printf("Seat %d is available\nDo you wish to reserve it? Enter y or n.\n", seat);

                    String confirm = scanner.nextLine();

                    if (confirm.equals("y")){
                        buyer.reserveIndivSeat(seat);
                        System.out.println("Reservation successful.");
                    }
                }

                else {
                    System.out.printf("Seat %d is NOT available\n", seat);
                }

            }

            // Elif r for reserve
            else if (option.equals("r")){
                // Prompt for how many
                System.out.println("Enter a number of seats to reserve");
                int numSeats = Integer.parseInt(scanner.nextLine());

                boolean isPossible;
                // Make a plan with the seats wherever they're available
                isPossible = buyer.planNonContiguous(numSeats);

                // If it isn't possible to make a plan, let the user know
                if (!isPossible){
                    System.out.printf("A sequence of %d seats meting those criteria is not available.\n", numSeats);
                }

                // Otherwise, show the user the proposed seats and ask them to confirm
                else { 
                    // Show the current available seats (before this transaction)
                    System.out.println("Before, the available seats were:");
                    buyer.printSeatsReserved();

                    // Show the seats after this transaction
                    System.out.println("Including your seats, the available seats are:");
                    buyer.printProposedSeats();

                    System.out.println("Would you like to confirm? Enter y or n.");
                    String confirm = scanner.nextLine();
                    // If they want to confirm, make the change to the array of reserved seats.
                    if (confirm.equals("y")){
                        buyer.confirmReservation();
                        System.out.println("Reservation successful.");
                    }
                }
            }

            System.out.println("Please select an option: (c)heck availability of and reserve a single seat, (r)eserve a group of seats, or (q)uit");
            option = scanner.nextLine();
        }
    }










    /**
     * Interested in trying the challenge? Here's the starter code for it! You can replace 
     * nonContiguousAvailable with findContiguous, and planNonContiguous with planContiguous
     * to test it! Please let Tayloe know in the readme if you've done this. 
     */
    /**
     * Functions finds the start of a set of N available, contiguous seats (if it exists)
     * @param numSeatsWanted the total number of seats requested
     * @return the index of the start of the contigous sequence of seats or -1 if a contigous sequence of seats of that size does not exist
     */
    // private int findContiguous(int numSeatsWanted){
       

    // }

    /**
     * Checks if it would be possible to reserve numSeatsWanted seats in a contiguous sequence. If it is, it modifies the proposedSeats array to reflect what seatsReserved would look like if the requested seats were reserved too.
     * @param numSeatsWanted the total number of seats that the user wants to reserve
     * @return false if it is not possible to reserve the seats or true if proposedSeats was successfully updated to show what seatsReserved would look like with the new seats reserved.
     */
    // public boolean planContiguous(int numSeatsWanted){
       

    // }
    
}

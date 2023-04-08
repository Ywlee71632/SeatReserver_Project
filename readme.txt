Name: Yewon Lee

Assignment: TicketReserver

Attributions: W3Schools - Constructor; W3Schools - Methods; W3Schools - Multi-Dimensional Arrays; W3Schools - Loop through Arrays; Sololearn - Java(Introduction to Java); Professor Heather Pon-Barry; Lab Instructor Tayloe

Questions: 
* In terms of naming the varibles for termination of the loop, is it better to name them with alphabets, or with meaningful words? 
* From isSeatReserved method, why does the condition not work if I order the condition in a different way other than ' input < 0 || input >= seatsReserved.length || seatsReserved[seatNum] == true ' ? 
    ㄴ The reason why the order of termination condition matters: JAVA checks condition from left to right 

Difficulties: 
* I wasn't fully aware of how to create new object using instance properties.
* I didn't know that copying the whole array to the another one would not copy the values of array, but just direct both array to the same reference address.
* It was challenging for me to create a loop that goes through the whole array while counting a numSeatsWanted.
* I started writing the code first before reading through the main method, so it took more time to analyze all the errors and go back from the scratch to fix methods.

Bugs: 
* ArrayIndexOutOfBounds occurred when I ordered the condition of if statement of isSeatReserved method in a different way. 
* NullPointerException occurred when I made a mistake in creating new arrays within the constructor.

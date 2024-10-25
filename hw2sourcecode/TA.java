/**
 * Worker.java
 * 
 * This thread is used to demonstrate the operation of a semaphore.
 * 
 */

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TA implements Runnable {

	private dashboard board;
	private String name;
	private Semaphore hallwaylock;
	private Semaphore officelock;

	public TA(dashboard board, Semaphore hallwaylock, Semaphore officelock, String name) {
		this.name = name;
		this.hallwaylock = hallwaylock;
		this.officelock = officelock;
		this.board = board;
	}

	public void run() {
		

		while (true) {
			System.out.println("Office = " + officelock.availablePermits());
			System.out.println("Hallway = " + hallwaylock.availablePermits());

			
			//sleep for a while if there is no student waiting and no student in the office
			if(hallwaylock.availablePermits() >= 3){
				try {
					if(officelock.availablePermits() >= 1){
						board.officeMessage("TA "+name +" is sleeping");
						SleepUtilities.nap();
					}
					
				} catch (Exception e) {
					
				}
				
			}
			else if(officelock.availablePermits() >= 1){
				try {
					//check whether there are students who are waiting
					board.officeMessage("TA "+name +" is done with students");
					SleepUtilities.nap();
				} catch (Exception e) {
				
				}
			}
			
			else{
			//work with the student
			board.officeMessage("TA "+name +" is working with students");
			SleepUtilities.nap();

			}		
		}
	}

}

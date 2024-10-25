/**
 * Student.java
 * 
 * 
 * 
 */
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class students implements Runnable {

	private dashboard board;
	//private Lock lockX;   // Please remove it if you use other synchroization tools. 
	private String name;
	private Semaphore hallwaylock;
	private Semaphore officelock;

	public students(dashboard board, Semaphore hallwaylock, Semaphore officelock, String name) {
		this.name = name;
		this.board = board;
		this.hallwaylock = hallwaylock;
		this.officelock = officelock;
		//this.lockX = loc;  // Please remove it if you use other synchroization tools.   
	}

	public void run() {
		

		while (true) {
			
			//programming for a while
			SleepUtilities.nap(30);		
			//seek help from TA
			
            //post the status of the students whenever their status change.
			board.postMessage(name+" need help"); 

			// if the waiting room has space,  wait in hallway. 
			// Otherwise do more programming.
			try{
				hallwaylock.acquire();
			} catch(Exception e){

			}
			board.waitHallway(name);

			
			SleepUtilities.nap();
			
			//enter the office if TA is available
			//wait in hallway if TA is not available
			//leave if the hallway is full
			try{
				officelock.acquire();
			} catch (Exception e){

			}
			board.leaveHallway(name);
			board.enterRoom(name);

			try{
				hallwaylock.release();
			} catch(Exception e){

			}
			
			SleepUtilities.nap();

			//leave the office and go back
			board.leaveRoom(name);
			try{
				officelock.release();
			} catch (Exception e){

			}
			
		
		}
	}

}

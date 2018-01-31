/**
 * The Timer class keep track of the run time of code via a start and stop method.
 * 
 * @author Brian Knapp
 * @version 0.0.0.1
 * @since 2018-01-20
 */

public class TimerBrianKnapp {
	private long duration;
	/**
	 * Constructor for the Timer class. Sets the duration to 0 upon instantiation. 
	 */
	TimerBrianKnapp() {
		 duration = 0;
	}
	/**
	 * Starts timing by setting duration to the current nanoTime.
	 */
	public void start() {
		duration = System.nanoTime(); // tracks time in seconds
	}
	/**
	 * Stops timing by setting duration to the current nanoTime minus the start nanoTime.
	 */
	public void stop() {
		long end_time = System.nanoTime();
		duration = end_time - duration;
	}
	/**
	 * Returns the duration variable to the caller.
	 * @return the saved duration.
	 */
	public long getDuration() {
		return duration;
	}

}

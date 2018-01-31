/**
 * Verify argument length is one, else exit
 * Pass the input file to a DPSolver object
 * Start the system clock using the start() in Timer
 * Use the solve() in DPSolver to solve the sat problem in the input file
 * Stop the system clock using the stop() in Timer
 * Print out the time taken to solve the problem
 * 
 * @author Brian Knapp
 * @version 0.0.0.1
 * @since 2018-01-20
 */
public class MainBrianKnapp {

	/**
	 * The main method tries to send a file to the solver and time the runtime via a timer class.
	 *
	 * @param args The file name to be used in the DPSolver.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: <fullFilePath>");
			System.exit(1);
		}
		else {
			DPSolverBrianKnapp dpSolver = new DPSolverBrianKnapp(args[0]);
			TimerBrianKnapp dpTimer = new TimerBrianKnapp();
			dpTimer.start();
			dpSolver.solve();
			dpTimer.stop();
			System.out.format("DPSolver took %d milliseconds.", (dpTimer.getDuration() / 1000000));
		}
	}
}

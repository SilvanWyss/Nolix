//package declaration
package ch.nolix.coreTutorial.sequencerTutorial;

//own imports
import ch.nolix.core.mathematics.Matrix;
import ch.nolix.core.sequencer.ResultFuture;
import ch.nolix.core.sequencer.Sequencer;

//class
/**
 * The result future tutorial is a tutorial for the result future.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 40
 */
public final class ResultFutureTutorial {
	
	//main method
	/**
	 * Calculates the rank of a matrix in background.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Matrix matrix = Matrix.createIdendityMatrix(100);
		
		final ResultFuture<Integer> future = Sequencer.runInBackground(() -> matrix.getRank());
		
		System.out.println("Calculations are done in background.");
		System.out.println("...");
		
		future.waitUntilFinished();
		
		System.out.println("rank: " + future.getResulg());
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can becreated.
	 */
	private ResultFutureTutorial() {}
}

//package declaration
package ch.nolix.commonTutorial.sequencerTutorial;

import ch.nolix.common.math.Matrix;
import ch.nolix.common.sequencer.ResultFuture;
import ch.nolix.common.sequencer.Sequencer;

//class
/**
 * The result future tutorial is a tutorial for the result future.
 * Of this class an instance cannot be created.
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
		
		future.waitUntilIsFinished();
		
		System.out.println("rank: " + future.getResult());
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can becreated.
	 */
	private ResultFutureTutorial() {}
}

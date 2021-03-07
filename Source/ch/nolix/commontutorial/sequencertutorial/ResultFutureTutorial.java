package ch.nolix.commontutorial.sequencertutorial;

//own imports
import ch.nolix.common.math.Matrix;
import ch.nolix.common.programcontrol.sequencer.ResultFuture;
import ch.nolix.common.programcontrol.sequencer.Sequencer;

/**
 * The {@link ResultFutureTutorial} is a tutorial for {@link ResultFuture}s.
 * Of the {@link ResultFutureTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-10-05
 * @lines 40
 */
public final class ResultFutureTutorial {
	
	/**
	 * Calculates the rank of a {@link Matrix} in background.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		final var matrix = Matrix.createIdendityMatrix(2000);
		
		final var resultFuture = Sequencer.runInBackground(matrix::getRank);
		
		System.out.println("Calculations are done in background.");
		System.out.println("...");
		
		resultFuture.waitUntilIsFinished();
		
		System.out.println("rank: " + resultFuture.getResult());
	}
	
	/**
	 * Avoids that an instance of the {@link ResultFutureTutorial} can be created.
	 */
	private ResultFutureTutorial() {}
}

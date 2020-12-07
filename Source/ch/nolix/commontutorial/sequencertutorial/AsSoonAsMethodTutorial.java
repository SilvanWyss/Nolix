package ch.nolix.commontutorial.sequencertutorial;

import java.util.Random;

//own imports
import ch.nolix.common.sequencer.Sequencer;

public final class AsSoonAsMethodTutorial {
	
	public static void main(String[] args) {
		Sequencer
		.asSoonAs(() -> getRandomNumberBetween1And100() == 100)
		.runInBackground(() -> System.out.println("Number 100 occured!"));
	}
	
	private static int getRandomNumberBetween1And100() {
		return (new Random().nextInt(100) + 1);
	}
	
	private AsSoonAsMethodTutorial() {}
}

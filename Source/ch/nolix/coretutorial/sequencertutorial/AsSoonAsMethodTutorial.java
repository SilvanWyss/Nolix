package ch.nolix.coretutorial.sequencertutorial;

import java.util.Random;

//own imports
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;

public final class AsSoonAsMethodTutorial {
	
	private static final Random random = new Random();
	
	public static void main(String[] args) {
		GlobalSequencer
		.asSoonAs(() -> getRandomNumberBetween1And100() == 100)
		.runInBackground(() -> System.out.println("Number 100 occured!"));
	}
	
	private static int getRandomNumberBetween1And100() {
		return (random.nextInt(100) + 1);
	}
	
	private AsSoonAsMethodTutorial() {}
}

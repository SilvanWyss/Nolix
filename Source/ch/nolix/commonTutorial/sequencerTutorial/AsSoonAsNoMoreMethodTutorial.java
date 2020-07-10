package ch.nolix.commonTutorial.sequencerTutorial;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.sequencer.Sequencer;

public final class AsSoonAsNoMoreMethodTutorial {
	
	public static void main(String[] args) {
		
		final var cats = new LinkedList<>("Garfield", "Simba", "Smokey");
		
		Sequencer
		.asSoonAsNoMore(cats::containsAny)
		.runInBackground(() -> System.out.println("Couch is not scratched anymore!"));
		
		cats.clear();
	}
		
	private AsSoonAsNoMoreMethodTutorial() {}
}

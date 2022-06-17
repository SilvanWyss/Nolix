package ch.nolix.coretutorial.sequencertutorial;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;

public final class AsSoonAsNoMoreMethodTutorial {
	
	public static void main(String[] args) {
		
		final var cats = LinkedList.withElements("Garfield", "Simba", "Smokey");
		
		GlobalSequencer
		.asSoonAsNoMore(cats::containsAny)
		.runInBackground(() -> System.out.println("Couch is not scratched anymore!"));
		
		cats.clear();
	}
		
	private AsSoonAsNoMoreMethodTutorial() {}
}

package ch.nolix.coretutorial.programcontroltutorial.sequencertutorial;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;

public final class AsSoonAsNoMoreMethodTutorial {
	
	public static void main(String[] args) {
		
		final var cats = LinkedList.withElement("Garfield", "Simba", "Smokey");
		
		GlobalSequencer
		.asSoonAsNoMore(cats::containsAny)
		.runInBackground(() -> System.out.println("Couch is not scratched anymore!"));
		
		cats.clear();
	}
		
	private AsSoonAsNoMoreMethodTutorial() {}
}

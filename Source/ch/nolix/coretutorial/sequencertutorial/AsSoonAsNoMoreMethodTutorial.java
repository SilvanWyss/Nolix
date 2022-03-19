package ch.nolix.coretutorial.sequencertutorial;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.programcontrol.sequencer.Sequencer;

public final class AsSoonAsNoMoreMethodTutorial {
	
	public static void main(String[] args) {
		
		final var cats = LinkedList.withElements("Garfield", "Simba", "Smokey");
		
		Sequencer
		.asSoonAsNoMore(cats::containsAny)
		.runInBackground(() -> System.out.println("Couch is not scratched anymore!"));
		
		cats.clear();
	}
		
	private AsSoonAsNoMoreMethodTutorial() {}
}

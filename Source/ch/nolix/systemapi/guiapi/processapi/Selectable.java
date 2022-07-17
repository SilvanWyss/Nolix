//package declaration
package ch.nolix.systemapi.guiapi.processapi;

//interface
public interface Selectable<S extends Selectable<S>> extends SelectionRequestable {
	
	//method declaration
	S select();
	
	//method declaration
	S unselect();
}

//package declaration
package ch.nolix.systemapi.guiapi.structureapi;

//interface
public interface VisibilitySettable<VS extends VisibilitySettable<VS>> extends VisibilityRequestable {
	
	//method declaration
	VS setInvisible();
	
	//method declaration
	VS setVisible();
}

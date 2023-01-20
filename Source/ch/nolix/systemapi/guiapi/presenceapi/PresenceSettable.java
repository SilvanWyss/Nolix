//package declaration
package ch.nolix.systemapi.guiapi.presenceapi;

//interface
public interface PresenceSettable<PS extends PresenceSettable<PS>> extends PresenceRequestable {
	
	//method declaration
	PS setCollapsed();
	
	//method declaration
	PS setInvisible();
	
	//method declaration
	PS setVisible();
}

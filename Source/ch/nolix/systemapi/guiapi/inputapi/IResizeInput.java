//package declaration
package ch.nolix.systemapi.guiapi.inputapi;

//interface
public interface IResizeInput<RI extends IResizeInput<RI>> extends IInput<RI> {
	
	//method declaration
	int getViewAreaHeight();
	
	//method declaration
	int getViewAreaWidth();
}

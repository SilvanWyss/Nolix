//package declaration
package ch.nolix.systemapi.webguiapi;

//own imports
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;

//interface
public interface IControl<C extends IControl<C>> extends IConfigurableElement<C> {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	IWebGUI<?> getParentGUI();
}

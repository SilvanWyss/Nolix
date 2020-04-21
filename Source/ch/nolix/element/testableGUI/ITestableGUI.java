//package declaration
package ch.nolix.element.testableGUI;

//own imports
import ch.nolix.common.attributeAPI.Titled;
import ch.nolix.common.skillAPI.IRequestableContainer;
import ch.nolix.element.inputs.IInputTaker;

//interface
public interface ITestableGUI extends IInputTaker, IRequestableContainer, Titled {
	
	//method declaration
	public abstract GUIController getRefGUIController();
	
	//method declaration
	public abstract GUIElement getRefGUIElement(String id);
}

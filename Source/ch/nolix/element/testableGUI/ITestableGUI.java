//package declaration
package ch.nolix.element.testableGUI;

//own imports
import ch.nolix.common.attributeAPI.Titled;
import ch.nolix.common.requestAPI.IContainsElementByIdRequestable;
import ch.nolix.element.input.IInputTaker;

//interface
public interface ITestableGUI extends IInputTaker, IContainsElementByIdRequestable, Titled {
	
	//method declaration
	public abstract GUIController getRefGUIController();
	
	//method declaration
	public abstract GUIElement getRefGUIElement(String id);
}

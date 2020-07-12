//package declaration
package ch.nolix.element.testableGUI;

//own imports
import ch.nolix.common.attributeAPI.Titled;
import ch.nolix.common.requestAPI.IContainsElementByStringIdRequestable;
import ch.nolix.element.input.IInputTaker;

//interface
public interface ITestableGUI extends IInputTaker, IContainsElementByStringIdRequestable, Titled {
	
	//method declaration
	public abstract GUIController getRefGUIController();
	
	//method declaration
	public abstract GUIElement getRefGUIElement(String id);
}

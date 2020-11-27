//package declaration
package ch.nolix.element.testableGUI;

//own imports
import ch.nolix.common.attributeAPI.Titled;
import ch.nolix.common.requestAPI.ContainsElementByStringIdRequestable;
import ch.nolix.element.input.IInputTaker;

//interface
public interface ITestableGUI extends IInputTaker, ContainsElementByStringIdRequestable, Titled {
	
	//method declaration
	GUIController getRefGUIController();
	
	//method declaration
	GUIElement getRefGUIElement(String id);
}

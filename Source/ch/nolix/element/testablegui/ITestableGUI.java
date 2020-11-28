//package declaration
package ch.nolix.element.testablegui;

import ch.nolix.common.attributeapi.Titled;
import ch.nolix.common.requestapi.ContainsElementByStringIdRequestable;
import ch.nolix.element.input.IInputTaker;

//interface
public interface ITestableGUI extends IInputTaker, ContainsElementByStringIdRequestable, Titled {
	
	//method declaration
	GUIController getRefGUIController();
	
	//method declaration
	GUIElement getRefGUIElement(String id);
}

//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;

//interface
public interface IControl<C extends IControl<C>> extends IConfigurableElement<C>, IInputTaker {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	boolean belongsToLayer();
	
	//method declaration
	CursorIcon getCursorIcon();
	
	//method declaration
	IWebGUI<?> getParentGUI();
	
	//method declaration
	ILayer<?> getParentLayer();
		
	//method declaration
	C setCursorIcon(CursorIcon cursorIcon);
}

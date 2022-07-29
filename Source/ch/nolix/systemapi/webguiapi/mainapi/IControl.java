//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.documentapi.htmlapi.HTMLElementTransformable;
import ch.nolix.systemapi.elementapi.configurationapi.IStylableElement;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.webguiapi.controllookapi.IControlLook;

//interface
public interface IControl<
	C extends IControl<C, CL>,
	CL extends IControlLook<CL>
>
extends HTMLElementTransformable, IStylableElement<C>, IInputTaker {
	
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
	CL getRefLook();
	
	//method declaration
	C setCursorIcon(CursorIcon cursorIcon);
}

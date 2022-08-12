//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.documentapi.htmlapi.HTMLElementTransformable;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;

//interface
public interface IControl<
	C extends IControl<C, CL>,
	CL extends IControlStyle<CL>
>
extends HTMLElementTransformable, IStylableElement<C>, IInputTaker {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	boolean belongsToLayer();
	
	//method declaration
	CursorIcon getCursorIcon();
	
	//method declaration
	String getFixedId();
	
	//method declaration
	IWebGUI<?> getParentGUI();
	
	//method declaration
	ILayer<?> getParentLayer();
	
	//method declaration
	CL getRefStyle();
	
	//method declaration
	C setCursorIcon(CursorIcon cursorIcon);
	
	//method declaration
	void technicalSetParentControl(IControl<?, ?> parentControl);
	
	//method declaration
	void technicalSetParentLayer(ILayer<?> parentLayer);
}

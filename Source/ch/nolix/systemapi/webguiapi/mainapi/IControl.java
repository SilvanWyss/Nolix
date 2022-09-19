//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.FixedIdentified;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
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
extends FixedIdentified, HTMLElementTransformable, IStylableElement<C>, IInputTaker {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	boolean belongsToLayer();
	
	//method declaration
	IControlCSSRuleCreator<C, CL> getCSSRuleCreator();
	
	//method declaration
	CursorIcon getCursorIcon();
	
	//method declaration
	IContainer<IControl<?, ?>> getRefChildControls();
	
	//method declaration
	IWebGUI<?> getRefParentGUI();
	
	//method declaration
	ILayer<?> getRefParentLayer();
	
	//method declaration
	CL getRefStyle();
	
	//method declaration
	C setCursorIcon(CursorIcon cursorIcon);
	
	//method declaration
	void technicalSetParentControl(IControl<?, ?> parentControl);
	
	//method declaration
	void technicalSetParentLayer(ILayer<?> parentLayer);
}

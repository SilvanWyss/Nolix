//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.FixedIdentifiedByString;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.containerapi.mainapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTransformable;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.Dimensionable;
import ch.nolix.systemapi.guiapi.presenceapi.PresenceSettable;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;

//interface
public interface IControl<
	C extends IControl<C, CL>,
	CL extends IControlStyle<CL>
>
extends
Dimensionable<C>,
FixedIdentifiedByString,
HTMLElementTransformable,
IStylableElement<C>,
IUserInputCell<C>,
PresenceSettable<C> {
	
	//method declaration
	boolean belongsToControl();
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	boolean belongsToLayer();
	
	//method declaration
	C editStyle(IElementTaker<CL> styleEditor);
	
	//method declaration
	IContainer<ICSSRule<?>> getCSSRules();
	
	//method declaration
	CursorIcon getCursorIcon();
	
	//method declaration
	ISingleContainer<String> getOptionalJavaScriptUserInputFunction();
	
	//method declaration
	IContainer<IControl<?, ?>> getRefChildControls();
	
	//method declaration
	IControl<?, ?> getRefParentControl();
	
	//method declaration
	IWebGUI<?> getRefParentGUI();
	
	//method declaration
	ILayer<?> getRefParentLayer();
	
	//method declaration
	CL getRefStyle();
	
	//method declaration
	void registerHTMLElementEventsAt(IMutableList<IHTMLElementEvent> list);
	
	//method declaration
	void runHTMLEvent(String pHTMLEvent);
	
	//method declaration
	C setCursorIcon(CursorIcon cursorIcon);
	
	//method declaration
	void technicalSetParentControl(IControl<?, ?> parentControl);
	
	//method declaration
	void technicalSetParentLayer(ILayer<?> parentLayer);
}

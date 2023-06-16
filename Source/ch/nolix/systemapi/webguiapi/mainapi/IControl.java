//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.InternalIdentified;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
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
InternalIdentified,
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
	CursorIcon getDefaultCursorIcon();
	
	//method declaration
	ISingleContainer<String> getOptionalJavaScriptUserInputFunction();
	
	//method declaration
	IContainer<IControl<?, ?>> getOriChildControls();
	
	//method declaration
	IControl<?, ?> getOriParentControl();
	
	//method declaration
	IWebGUI<?> getOriParentGUI();
	
	//method declaration
	ILayer<?> getOriParentLayer();
	
	//method declaration
	CL getOriStyle();
	
	//method declaration
	void registerHTMLElementEventsAt(ILinkedList<IHTMLElementEvent> list);
	
	//method declaration
	void runHTMLEvent(String pHTMLEvent);
	
	//method declaration
	C setCursorIcon(CursorIcon cursorIcon);
	
	//method declaration
	void technicalSetParentControl(IControl<?, ?> parentControl);
	
	//method declaration
	void technicalSetParentLayer(ILayer<?> parentLayer);
}

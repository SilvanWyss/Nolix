//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.InternalIdentified;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.singlecontainerapi.ISingleContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTransformable;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasapi.Dimensionable;
import ch.nolix.systemapi.guiapi.presenceapi.PresenceSettable;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;

//interface
public interface IControl<
	C extends IControl<C, CS>,
	CS extends IControlStyle<CS>
>
extends
Dimensionable<C>,
InternalIdentified,
HtmlElementTransformable,
IStylableElement<C>,
IUserInputCell<C>,
PresenceSettable<C> {
	
	//method declaration
	boolean belongsToControl();
	
	//method declaration
	boolean belongsToGui();
	
	//method declaration
	boolean belongsToLayer();
	
	//method declaration
	C editStyle(IElementTaker<CS> styleEditor);
	
	//method declaration
	IContainer<ICssRule<?>> getCssRules();
	
	//method declaration
	CursorIcon getCursorIcon();
	
	//method declaration
	ISingleContainer<String> getOptionalJavaScriptUserInputFunction();
	
	//method declaration
	IContainer<IControl<?, ?>> getStoredChildControls();
	
	//method declaration
	IControl<?, ?> getStoredParentControl();
	
	//method declaration
	IWebGui<?> getStoredParentGui();
	
	//method declaration
	ILayer<?> getStoredParentLayer();
	
	//method declaration
	CS getStoredStyle();
	
	//method declaration
	void registerHtmlElementEventsAt(ILinkedList<IHtmlElementEvent> list);
	
	//method declaration
	void runHtmlEvent(String htmlEvent);
	
	//method declaration
	C setCursorIcon(CursorIcon cursorIcon);
	
	//method declaration
	void technicalSetParentControl(IControl<?, ?> parentControl);
	
	//method declaration
	void technicalSetParentLayer(ILayer<?> parentLayer);
}

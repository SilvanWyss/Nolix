//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.FixedIdentified;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTransformable;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.ICanvas;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;
import ch.nolix.systemapi.guiapi.widgetguiapi.LayerRole;

//interface
public interface ILayer<L extends ILayer<L>>
extends
FixedIdentified,
HTMLElementTransformable,
ICanvas<L>,
IStylableElement<L>,
IInputTaker,
IRootControlOwner<L> {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	ContentPosition getContentPosition();
	
	//method declaration
	ICSSRule<?> getCSSRule();
	
	//method declaration
	IWebGUI<?> getRefParentGUI();
	
	//method declaration
	LayerRole getRole();
	
	//method declaration
	boolean hasRole();
	
	//method declaration
	void removeSelfFromGUI();
	
	//method declaration
	L setContentPosition(ContentPosition contentPosition);
	
	//method declaration
	L setRole(LayerRole role);
}

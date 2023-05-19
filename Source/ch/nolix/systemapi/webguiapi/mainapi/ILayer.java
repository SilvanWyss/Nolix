//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.InternalIdentified;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTransformable;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.ICanvas;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;

//interface
public interface ILayer<L extends ILayer<L>>
extends
InternalIdentified,
HTMLElementTransformable,
ICanvas<L>,
IStylableElement<L>,
IRootControlOwner<L> {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	ContentPosition getContentPosition();
	
	//method declaration
	ICSSRule<?> getCSSRule();
	
	//method declaration
	double getOpacity();
	
	//method declaration
	IWebGUI<?> getOriParentGUI();
	
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
	
	//method declaration
	L setOpacity(double opacity);
	
	//method declaration
	void technicalSetParentGUI(IWebGUI<?> parentGUI);
}

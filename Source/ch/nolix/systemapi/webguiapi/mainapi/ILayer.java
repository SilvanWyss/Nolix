//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.InternalIdentified;
import ch.nolix.coreapi.webapi.cssapi.ICSSRule;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTransformable;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.ICanvas;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;

//interface
public interface ILayer<L extends ILayer<L>>
extends
InternalIdentified,
HtmlElementTransformable,
ICanvas<L>,
IStylableElement<L>,
IRootControlOwner<L> {
	
	//method declaration
	boolean belongsToGui();
	
	//method declaration
	ContentPosition getContentPosition();
	
	//method declaration
	ICSSRule<?> getCSSRule();
	
	//method declaration
	double getOpacity();
	
	//method declaration
	IWebGui<?> getOriParentGui();
	
	//method declaration
	LayerRole getRole();
	
	//method declaration
	boolean hasRole();
	
	//method declaration
	void removeSelfFromGui();
	
	//method declaration
	L setContentPosition(ContentPosition contentPosition);
	
	//method declaration
	L setRole(LayerRole role);
	
	//method declaration
	L setOpacity(double opacity);
	
	//method declaration
	void technicalSetParentGui(IWebGui<?> parentGui);
}

//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.documentapi.htmlapi.HTMLElementTransformable;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.ICanvas;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;

//interface
public interface ILayer<L extends ILayer<L>>
extends HTMLElementTransformable, ICanvas<L>, IConfigurableElement<L>, IInputTaker, IRootControlOwner<L> {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	IWebGUI<?> getParentGUI();
	
	//method declaration
	void removeSelfFromGUI();
}

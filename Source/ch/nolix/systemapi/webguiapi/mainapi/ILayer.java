//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.documentapi.htmlapi.HTMLElementTransformable;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.ICanvas;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.widgetguiapi.LayerRole;

//interface
public interface ILayer
extends
HTMLElementTransformable,
ICanvas<ILayer>,
IConfigurableElement<ILayer>,
IInputTaker,
IRootControlOwner<ILayer> {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	IWebGUI<?> getParentGUI();
	
	//method declaration
	LayerRole getRole();
	
	//method declaration
	boolean hasRole();
	
	//method declaration
	void removeSelfFromGUI();
	
	//method declaration
	ILayer setRole(LayerRole role);
}

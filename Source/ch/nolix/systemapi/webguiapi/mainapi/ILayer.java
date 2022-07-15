//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.canvasapi.ICanvas;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;

//interface
public interface ILayer<L extends ILayer<L>> extends Clearable, ICanvas<L>, IConfigurableElement<L>, IInputTaker {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	IWebGUI<?> getParentGUI();
	
	//method declaration
	void removeSelfFromGUI();
}

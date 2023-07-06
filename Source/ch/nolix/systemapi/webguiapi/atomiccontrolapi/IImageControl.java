//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IImageControl extends Clearable, IControl<IImageControl, IImageControlStyle> {
	
	//method declaration
	IMutableImage<?> getOriImage();
	
	//method declaration
	boolean hasLeftMouseButtonPressAction();
	
	//method declaration
	boolean hasLeftMouseButtonReleaseAction();
	
	//method declaration
	void removeLeftMouseButtonPressAction();
	
	//method declaration
	void removeLeftMouseButtonReleaseAction();
	
	//method declaration
	IImageControl setImage(IImage image);
	
	//method declaration
	IImageControl setLeftMouseButtonPressAction(IAction leftMouseButtonPressAction);
	
	//method declaration
	IImageControl setLeftMouseButtonPressAction(IElementTaker<IImageControl> leftMouseButtonPressAction);
	
	//method declaration
	IImageControl setLeftMouseButtonRelaseAction(IAction leftMouseButtonReleaseAction);
	
	//method declaration
	IImageControl setLeftMouseButtonRelaseAction(IElementTaker<IImageControl> leftMouseButtonReleaseAction);
}

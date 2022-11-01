//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IImageControl<
	IC extends IImageControl<IC, IICS, I>,
	IICS extends IImageControlStyle<IICS>,
	I extends IImage
> extends Clearable, IControl<IC, IICS> {
	
	//method declaration
	I getRefImage();
	
	//method declaration
	boolean hasLeftMouseButtonPressAction();
	
	//method declaration
	boolean hasLeftMouseButtonReleaseAction();
	
	//method declaration
	void removeLeftMouseButtonPressAction();
	
	//method declaration
	void removeLeftMouseButtonReleaseAction();
	
	//method declaration
	IC setImage(IImage image);
	
	//method declaration
	IC setLeftMouseButtonPressAction(IAction leftMouseButtonPressAction);
	
	//method declaration
	IC setLeftMouseButtonPressAction(IElementTaker<IImageControl<?, ?, ?>> leftMouseButtonPressAction);
	
	//method declaration
	IC setLeftMouseButtonRelaseAction(IAction leftMouseButtonReleaseAction);
	
	//method declaration
	IC setLeftMouseButtonRelaseAction(IElementTaker<IImageControl<?, ?, ?>> leftMouseButtonReleaseAction);
}

//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IImageControl<I extends IImage> extends Clearable, IControl<IImageControl<I>, IImageControlStyle> {
	
	//method declaration
	I getRefImage();
	
	//method declaration
	IImageControl<I> setImage(IImage image);
}

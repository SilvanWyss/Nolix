//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
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
	IC setImage(IImage image);
}

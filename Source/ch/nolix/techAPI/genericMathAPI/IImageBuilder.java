//package declaration
package ch.nolix.techAPI.genericMathAPI;

//own imports
import ch.nolix.common.futureAPI.IFuture;
import ch.nolix.element.image.Image;

//interface
public interface IImageBuilder extends IFuture {
	
	//method declaration
	public abstract Image getRefImage();
}

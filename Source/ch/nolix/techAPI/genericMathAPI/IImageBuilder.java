//package declaration
package ch.nolix.techAPI.genericMathAPI;

//own imports
import ch.nolix.core.futureAPI.IFuture;
import ch.nolix.element.image.Image;

//interface
public interface IImageBuilder extends IFuture {
	
	//abstract method
	public abstract Image getRefImage();
}

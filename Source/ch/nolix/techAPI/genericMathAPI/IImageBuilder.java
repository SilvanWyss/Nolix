//package declaration
package ch.nolix.techAPI.genericMathAPI;

//own import
import ch.nolix.element.image.Image;

//interface
public interface IImageBuilder {
	
	//abstract method
	public abstract Image getRefImage();
	
	//abstract method
	public abstract boolean isFinished();
}

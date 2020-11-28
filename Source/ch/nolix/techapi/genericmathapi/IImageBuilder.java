//package declaration
package ch.nolix.techapi.genericmathapi;

//own imports
import ch.nolix.common.futureAPI.IFuture;
import ch.nolix.element.graphic.Image;

//interface
public interface IImageBuilder extends IFuture {
	
	//method declaration
	Image getRefImage();
}

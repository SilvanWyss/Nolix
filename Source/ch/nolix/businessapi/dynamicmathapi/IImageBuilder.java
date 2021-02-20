//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//own imports
import ch.nolix.common.futureapi.IFuture;
import ch.nolix.element.graphic.Image;

//interface
public interface IImageBuilder extends IFuture {
	
	//method declaration
	Image getRefImage();
}

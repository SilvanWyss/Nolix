//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//own imports
import ch.nolix.core.programcontrol.futureapi.IFuture;
import ch.nolix.element.gui.image.MutableImage;

//interface
public interface IImageBuilder extends IFuture {
	
	//method declaration
	MutableImage getRefImage();
}

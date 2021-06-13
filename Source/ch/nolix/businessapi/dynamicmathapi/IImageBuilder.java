//package declaration
package ch.nolix.businessapi.dynamicmathapi;

import ch.nolix.common.programcontrol.futureapi.IFuture;
import ch.nolix.element.gui.image.MutableImage;

//interface
public interface IImageBuilder extends IFuture {
	
	//method declaration
	MutableImage getRefImage();
}

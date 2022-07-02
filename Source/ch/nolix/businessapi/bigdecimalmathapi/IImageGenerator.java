//package declaration
package ch.nolix.businessapi.bigdecimalmathapi;

import ch.nolix.coreapi.programcontrolapi.futureuniversalapi.IFuture;
import ch.nolix.system.gui.image.MutableImage;

//interface
public interface IImageGenerator extends IFuture {
	
	//method declaration
	MutableImage getRefImage();
}

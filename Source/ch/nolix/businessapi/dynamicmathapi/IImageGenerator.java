//package declaration
package ch.nolix.businessapi.dynamicmathapi;

import ch.nolix.core.programcontrol.futureuniversalapi.IFuture;
import ch.nolix.system.gui.image.MutableImage;

//interface
public interface IImageGenerator extends IFuture {
	
	//method declaration
	MutableImage getRefImage();
}

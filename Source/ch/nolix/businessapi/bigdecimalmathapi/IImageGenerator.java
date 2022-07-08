//package declaration
package ch.nolix.businessapi.bigdecimalmathapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.futureuniversalapi.IFuture;
import ch.nolix.systemapi.guiapi.imageapi.IMutableImage;

//interface
public interface IImageGenerator extends IFuture {
	
	//method declaration
	IMutableImage<?> getRefImage();
}

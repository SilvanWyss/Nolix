//package declaration
package ch.nolix.businessapi.mathapi.fractalapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.futureuniversalapi.IFuture;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;

//interface
public interface IImageGenerator extends IFuture {
	
	//method declaration
	IMutableImage<?> getRefImage();
}

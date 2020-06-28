//package declaration
package ch.nolix.element.baseGUI_API;

//own import
import ch.nolix.common.container.SingleContainer;

//interface
public interface IFrontEndReader {
	
	//method declaration
	public abstract SingleContainer<byte[]> readFileToBytes();
}

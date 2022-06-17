//package declaration
package ch.nolix.systemapi.guiapi.baseapi;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.container.SingleContainer;

//interface
public interface IFrontEndReader {
	
	//method declaration
	IContainer<byte[]> getFilesFromClipboard();
	
	//method declaration
	String getTextFromClipboard();
	
	//method declaration
	SingleContainer<byte[]> readFileToBytes();
}

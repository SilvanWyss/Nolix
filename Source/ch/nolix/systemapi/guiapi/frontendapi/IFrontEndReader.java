//package declaration
package ch.nolix.systemapi.guiapi.frontendapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.mainapi.ISingleContainer;

//interface
public interface IFrontEndReader {
	
	//method declaration
	IContainer<byte[]> getFilesFromClipboard();
	
	//method declaration
	String getTextFromClipboard();
	
	//method declaration
	ISingleContainer<byte[]> readFileToBytes();
}

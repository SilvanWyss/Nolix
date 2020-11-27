//package declaration
package ch.nolix.element.baseGUI_API;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;

//interface
public interface IFrontEndReader {
	
	//method declaration
	LinkedList<byte[]> getFilesFromClipboard();
	
	//method declaration
	String getTextFromClipboard();
	
	//method declaration
	SingleContainer<byte[]> readFileToBytes();
}

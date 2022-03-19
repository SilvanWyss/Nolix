//package declaration
package ch.nolix.element.gui.baseapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.SingleContainer;

//interface
public interface IFrontEndReader {
	
	//method declaration
	LinkedList<byte[]> getFilesFromClipboard();
	
	//method declaration
	String getTextFromClipboard();
	
	//method declaration
	SingleContainer<byte[]> readFileToBytes();
}

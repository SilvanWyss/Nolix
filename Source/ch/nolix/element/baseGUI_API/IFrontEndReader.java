//package declaration
package ch.nolix.element.baseGUI_API;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;

//interface
public interface IFrontEndReader {
	
	//method declaration
	public abstract LinkedList<byte[]> getFilesFromClipboard();
	
	//method declaration
	public abstract String getTextFromClipboard();
	
	//method declaration
	public abstract SingleContainer<byte[]> readFileToBytes();
}

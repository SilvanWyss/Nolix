//package declaration
package ch.nolix.systemapi.guiapi.baseapi;

import ch.nolix.core.container.main.SingleContainer;
//own imports
import ch.nolix.core.containerapi.IContainer;

//interface
public interface IFrontEndReader {
	
	//method declaration
	IContainer<byte[]> getFilesFromClipboard();
	
	//method declaration
	String getTextFromClipboard();
	
	//method declaration
	SingleContainer<byte[]> readFileToBytes();
}

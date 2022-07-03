//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface IFrontEndReader {
	
	//method declaration
	IContainer<byte[]> getFilesFromClipboard();
	
	//method declaration
	String getTextFromClipboard();
	
	//method declaration
	SingleContainer<byte[]> readFileToBytes();
}

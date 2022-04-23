//package declaration
package ch.nolix.systemapi.guiapi.baseapi;

//interface
public interface IFrontEndWriter {
	
	//method declaration
	void openNewTabWithURL(String pURL);
	
	//method declaration
	void saveFile(byte[] bytes);
}

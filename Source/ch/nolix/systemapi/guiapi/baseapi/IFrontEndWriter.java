//package declaration
package ch.nolix.systemapi.guiapi.baseapi;

//own imports
import ch.nolix.core.net.targetapi.IApplicationTarget;

//interface
public interface IFrontEndWriter {
	
	//method declaration
	void openNewTabWithURL(String pURL);
	
	//method declaration
	void redirectTo(IApplicationTarget applicationTarget);
	
	//method declaration
	void saveFile(byte[] bytes);
}

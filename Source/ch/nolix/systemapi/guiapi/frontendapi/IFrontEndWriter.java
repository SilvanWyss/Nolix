//package declaration
package ch.nolix.systemapi.guiapi.frontendapi;

//own imports
import ch.nolix.coreapi.netapi.targetuniversalapi.IApplicationTarget;

//interface
public interface IFrontEndWriter {
	
	//method declaration
	void openNewTabWithURL(String pURL);
	
	//method declaration
	void redirectTo(IApplicationTarget applicationTarget);
	
	//method declaration
	void saveFile(byte[] bytes);
}

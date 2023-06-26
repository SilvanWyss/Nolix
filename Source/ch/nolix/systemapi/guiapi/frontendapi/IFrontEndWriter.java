//package declaration
package ch.nolix.systemapi.guiapi.frontendapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;

//interface
public interface IFrontEndWriter {
	
	//method declaration
	void openNewTabWithUrl(String url);
	
	//method declaration
	void redirectTo(IApplicationInstanceTarget applicationInstanceTarget);
	
	//method declaration
	void redirectToUrl(String url);
	
	//method declaration
	void saveFile(byte[] bytes);
	
	//method declaration
	void writeTextToClipboard(String text);
}

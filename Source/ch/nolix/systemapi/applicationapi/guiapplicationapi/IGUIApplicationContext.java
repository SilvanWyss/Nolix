//package declaration
package ch.nolix.systemapi.applicationapi.guiapplicationapi;

//own imports
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//interface
public interface IGUIApplicationContext {
	
	//method declaration
	String getApplicationDescription();
	
	//method declaration
	IImage getApplicationLogo();
	
	//method declaration
	boolean hasApplicationDescription();
	
	//method declaration
	boolean hasApplicationLogo();
}

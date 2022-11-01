//package declaration
package ch.nolix.systemapi.applicationapi.guiapplicationapi;

import ch.nolix.systemapi.graphicapi.imageapi.IImage;

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

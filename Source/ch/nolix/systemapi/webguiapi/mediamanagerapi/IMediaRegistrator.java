//package declaration
package ch.nolix.systemapi.webguiapi.mediamanagerapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//interface
public interface IMediaRegistrator {
	
	//method declaration
	IContainer<IPair<String, IImage>> clearAndGetNewBackgroundImageRegistrationForHTMLElements();
	
	//method declaration
	IContainer<IPair<String, IImage>> clearAndGetNewImageRegistrationsForHMLImageElements();
	
	//method declaration
	void registerBackgroundImageForHTMLElementWithOverwriteMode(String pHTMLElementId, IImage backgroundImage);
	
	//method declaration
	void registerImageForHTMLImageElementWithOverwriteMode(String pHTMLImageElementId, IImage image);
}

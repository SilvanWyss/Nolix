//package declaration
package ch.nolix.system.webgui.mediamanager;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.mediamanagerapi.IMediaRegistrator;

//class
public final class MediaRegistrator implements IMediaRegistrator {
	
	//multi-attribute
	private final LinkedList<IPair<String, IImage>> backgroundImageRegistrationsForHTMLElements = new LinkedList<>();
	
	//multi-attribute
	private final LinkedList<IPair<String, IImage>> newBackgroundImageRegistrationsForHTMLElements =
	new LinkedList<>();
	
	//multi-attribute
	private final LinkedList<IPair<String, IImage>> imageRegistrationsForHTMLImageElements = new LinkedList<>();
	
	//multi-attribute
	private final LinkedList<IPair<String, IImage>> newImageRegistrationsForHTMLImageElements =	new LinkedList<>();
	
	//method
	@Override
	public IContainer<IPair<String, IImage>> clearAndGetNewBackgroundImageRegistrationForHTMLElements() {
		
		final var copy = newBackgroundImageRegistrationsForHTMLElements.getCopy();
		
		newBackgroundImageRegistrationsForHTMLElements.clear();
		
		return copy;
	}
	
	//method
	@Override
	public IContainer<IPair<String, IImage>> clearAndGetNewImageRegistrationsForHMLImageElements() {
		
		final var copy = newImageRegistrationsForHTMLImageElements.getCopy();
		
		newImageRegistrationsForHTMLImageElements.clear();
		
		return copy;
	}
	
	//method
	@Override
	public void registerBackgroundImageForHTMLElementWithOverwriteMode(
		final String pHTMLElementId,
		final IImage backgroundImage
	) {
		
		final var registration =
		backgroundImageRegistrationsForHTMLElements.getRefFirstOrNull(r -> r.getRefElement1().equals(pHTMLElementId));
		
		if (registration == null) {
			registerBackgroundImageForHTMLElement(pHTMLElementId, backgroundImage);
		} else if (!registration.hasElement2(backgroundImage)) {
			backgroundImageRegistrationsForHTMLElements.removeFirst(registration);
			registerBackgroundImageForHTMLElement(pHTMLElementId, backgroundImage);
		}
	}

	//method
	@Override
	public void registerImageForHTMLImageElementWithOverwriteMode(
		final String pHTMLImageElementId,
		final IImage image
	) {
		
		final var registration =
		imageRegistrationsForHTMLImageElements.getRefFirst(r -> r.getRefElement1().equals(pHTMLImageElementId));
		
		if (registration == null) {
			registerImageForHTMLImageElement(pHTMLImageElementId, image);
		} else if (registration.hasElement2(image)) {
			imageRegistrationsForHTMLImageElements.removeFirst(registration);
			registerImageForHTMLImageElement(pHTMLImageElementId, image);
		}
	}
	
	//method
	private void registerBackgroundImageForHTMLElement(final String pHTMLElementId, final IImage backgroundImage) {
		
		final var newRegistration = new Pair<String, IImage>(pHTMLElementId, backgroundImage);
		
		backgroundImageRegistrationsForHTMLElements.addAtEnd(newRegistration);
		newBackgroundImageRegistrationsForHTMLElements.addAtEnd(newRegistration);
	}
	
	//method
	private void registerImageForHTMLImageElement(final String pHTMLImageElementId, final IImage image) {
		
		final var newRegistration = new Pair<String, IImage>(pHTMLImageElementId, image);
		
		imageRegistrationsForHTMLImageElements.addAtEnd(newRegistration);
		newImageRegistrationsForHTMLImageElements.addAtEnd(newRegistration);
	}
}

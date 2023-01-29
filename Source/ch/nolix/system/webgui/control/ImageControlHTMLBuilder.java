//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HTMLElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;

//class
public final class ImageControlHTMLBuilder implements IControlHTMLBuilder<ImageControl> {
	
	//static attribute
	public static final ImageControlHTMLBuilder INSTANCE = new ImageControlHTMLBuilder();
	
	//constructor
	private ImageControlHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final ImageControl imageControl) {
		return
		HTMLElement.withTypeAndAttributes(
			HTMLElementTypeCatalogue.IMG,
			createHTMLAttributesFromImageControl(imageControl)
		);
	}
	
	//method
	private IContainer<HTMLAttribute> createHTMLAttributesFromImageControl(final ImageControl imageControl) {
		
		final var lHTMLAttributes = new LinkedList<HTMLAttribute>();
		
		lHTMLAttributes.addAtEnd(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(imageControl));
		
		if (imageControl.containsAny()) {
			lHTMLAttributes.addAtEnd(
				HTMLAttribute.withNameAndValue(
					HTMLAttributeNameCatalogue.SRC,
					"data:image/jpeg;base64," + imageControl.getRefImage().toJPGString()
				)
			);
		}
		
		return lHTMLAttributes;
	}
}

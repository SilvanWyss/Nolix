//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;

//class
public final class ImageControlHTMLBuilder implements IControlHtmlBuilder<ImageControl> {
	
	//static attribute
	public static final ImageControlHTMLBuilder INSTANCE = new ImageControlHTMLBuilder();
	
	//constructor
	private ImageControlHTMLBuilder() {}
	
	//method
	@Override
	public HTMLElement createHTMLElementForControl(final ImageControl imageControl) {
		return
		HTMLElement.withTypeAndAttributes(
			HtmlElementTypeCatalogue.IMG,
			createHTMLAttributesFromImageControl(imageControl)
		);
	}
	
	//method
	private IContainer<HtmlAttribute> createHTMLAttributesFromImageControl(final ImageControl imageControl) {
		
		final var lHTMLAttributes = new LinkedList<HtmlAttribute>();
		
		lHTMLAttributes.addAtEnd(ControlHelper.INSTANCE.createIdHTMLAttributeForControl(imageControl));
		
		if (imageControl.containsAny()) {
			lHTMLAttributes.addAtEnd(
				HtmlAttribute.withNameAndValue(
					HtmlAttributeNameCatalogue.SRC,
					"data:image/jpeg;base64," + imageControl.getOriImage().toJPGString()
				)
			);
		}
		
		return lHTMLAttributes;
	}
}

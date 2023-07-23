//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.controlhelper.ControlHelper;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControl;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;

//class
public final class ImageControlHtmlBuilder implements IControlHtmlBuilder<IImageControl> {
	
	//constant
	private static final ControlHelper CONTROL_HELPER = new ControlHelper();
	
	//method
	@Override
	public HtmlElement createHtmlElementForControl(final IImageControl imageControl) {
		return
		HtmlElement.withTypeAndAttributes(
			HtmlElementTypeCatalogue.IMG,
			createHtmlAttributesFromImageControl(imageControl)
		);
	}
	
	//method
	private IContainer<HtmlAttribute> createHtmlAttributesFromImageControl(final IImageControl imageControl) {
		
		final var htmlAttributes = new LinkedList<HtmlAttribute>();
		
		htmlAttributes.addAtEnd(CONTROL_HELPER.createIdHtmlAttributeForControl(imageControl));
		
		if (imageControl.containsAny()) {
			htmlAttributes.addAtEnd(
				HtmlAttribute.withNameAndValue(
					HtmlAttributeNameCatalogue.SRC,
					"data:image/jpeg;base64," + imageControl.getStoredImage().toJPGString()
				)
			);
		}
		
		return htmlAttributes;
	}
}

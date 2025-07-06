package ch.nolix.system.webgui.atomiccontrol.imagecontrol;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.imagecontrolapi.IImageControl;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class ImageControlHtmlBuilder implements IControlHtmlBuilder<IImageControl> {

  @Override
  public HtmlElement createHtmlElementForControl(final IImageControl imageControl) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalog.IMG,
      createHtmlAttributesFromImageControl(imageControl));
  }

  private IContainer<IHtmlAttribute> createHtmlAttributesFromImageControl(final IImageControl imageControl) {

    final ILinkedList<IHtmlAttribute> htmlAttributes = LinkedList.createEmpty();

    if (imageControl.containsAny()) {

      final var srcAttribute = //
      HtmlAttribute.withNameAndValue(
        HtmlAttributeNameCatalog.SRC,
        "data:image/jpeg;base64," + imageControl.getStoredImage().toBase64Jpg());

      htmlAttributes.addAtEnd(srcAttribute);
    }

    final var altAttribute = //
    HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ALT, imageControl.getAlternateText());

    htmlAttributes.addAtEnd(altAttribute);

    return htmlAttributes;
  }
}

package ch.nolix.system.webgui.atomiccontrol.imagecontrol;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.systemapi.webgui.atomiccontrol.imagecontrolapi.IImageControl;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

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

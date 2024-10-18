package ch.nolix.system.webgui.atomiccontrol;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControl;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class ImageControlHtmlBuilder implements IControlHtmlBuilder<IImageControl> {

  @Override
  public HtmlElement createHtmlElementForControl(final IImageControl imageControl) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalogue.IMG,
      createHtmlAttributesFromImageControl(imageControl));
  }

  private IContainer<IHtmlAttribute> createHtmlAttributesFromImageControl(final IImageControl imageControl) {

    final ILinkedList<IHtmlAttribute> htmlAttributes = LinkedList.createEmpty();

    if (imageControl.containsAny()) {
      htmlAttributes.addAtEnd(
        HtmlAttribute.withNameAndValue(
          HtmlAttributeNameCatalogue.SRC,
          "data:image/jpeg;base64," + imageControl.getStoredImage().toJPGString()));
    }

    return htmlAttributes;
  }
}

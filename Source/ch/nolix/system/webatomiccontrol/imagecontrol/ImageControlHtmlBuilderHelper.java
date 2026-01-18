/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.imagecontrol;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControl;

/**
 * @author Silvan Wyss
 */
public final class ImageControlHtmlBuilderHelper {
  private ImageControlHtmlBuilderHelper() {
  }

  public static IContainer<IHtmlAttribute> createHtmlAttributesForImageControl(final IImageControl imageControl) {
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

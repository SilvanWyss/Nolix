/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.imagecontrol;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.systemapi.atomiccontrol.imagecontrol.IImageControl;

/**
 * @author Silvan Wyss
 */
public final class ImageControlHtmlBuilderHelper {
  private ImageControlHtmlBuilderHelper() {
  }

  public static ExtendedIterable<IHtmlAttribute> createHtmlAttributesForImageControl(final IImageControl imageControl) {
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

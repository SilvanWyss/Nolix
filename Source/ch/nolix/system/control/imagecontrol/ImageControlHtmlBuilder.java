/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.imagecontrol;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.control.imagecontrol.IImageControl;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class ImageControlHtmlBuilder implements IControlHtmlBuilder<IImageControl> {
  /**
   * {@inheritDoc}
   */
  @Override
  public HtmlElement createHtmlElementForControl(final IImageControl imageControl) {
    final var type = HtmlElementTypeCatalog.IMG;
    final var attributes = ImageControlHtmlBuilderHelper.createHtmlAttributesForImageControl(imageControl);

    return HtmlElement.withTypeAndAttributes(type, attributes);
  }
}

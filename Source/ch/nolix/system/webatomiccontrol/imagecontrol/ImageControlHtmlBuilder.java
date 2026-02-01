/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.imagecontrol;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControl;
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

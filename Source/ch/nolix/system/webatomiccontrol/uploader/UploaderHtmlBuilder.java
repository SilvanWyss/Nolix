/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.uploader;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webatomiccontrol.uploader.IUploader;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class UploaderHtmlBuilder implements IControlHtmlBuilder<IUploader> {
  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement createHtmlElementForControl(final IUploader control) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalog.INPUT,
      ImmutableList.withElements(
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.TYPE, "file"),
        HtmlAttribute.withNameAndValue("multiple", "none"),
        HtmlAttribute.withNameAndValue("data-uploader", "any")));
  }
}

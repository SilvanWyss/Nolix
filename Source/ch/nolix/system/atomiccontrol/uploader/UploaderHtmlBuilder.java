/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.uploader;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.base.web.htmlelementmodel.HtmlElement;
import ch.nolix.baseapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.atomiccontrol.uploader.IUploader;
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

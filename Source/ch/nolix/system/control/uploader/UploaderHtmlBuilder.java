/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.uploader;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.web.htmlmodel.HtmlAttribute;
import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.web.htmlcatalog.HtmlAttributeNameCatalog;
import ch.nolix.baseapi.web.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.control.uploader.IUploader;
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

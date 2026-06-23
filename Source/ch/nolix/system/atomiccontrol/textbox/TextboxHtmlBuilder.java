/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.textbox;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.html.htmlmodel.HtmlAttribute;
import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.html.htmlcatalog.HtmlAttributeNameCatalog;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.atomiccontrol.textbox.ITextbox;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class TextboxHtmlBuilder implements IControlHtmlBuilder<ITextbox> {
  /**
   * {@inheritDoc}
   */
  @Override
  public HtmlElement createHtmlElementForControl(final ITextbox textbox) {
    return HtmlElement.withTypeAndAttributes(
      HtmlElementTypeCatalog.INPUT,
      ImmutableList.withElements(
        HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.VALUE, textbox.getText())));
  }
}

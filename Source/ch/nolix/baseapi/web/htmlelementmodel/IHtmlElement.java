/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.htmlelementmodel;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface IHtmlElement {
  boolean containsAttributes();

  boolean containsChildElements();

  IWellOrderContainer<? extends IHtmlAttribute> getAttributes();

  IWellOrderContainer<? extends IHtmlElement> getChildElements();

  String getInnerText();

  String getType();

  IHtmlElement withAdditionalAttributes(IWellOrderContainer<IHtmlAttribute> additionalAttributes);

  IHtmlElement withAdditionalAttributes(IHtmlAttribute... additionalAttributes);
}

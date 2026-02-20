/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.htmlelementmodel;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IHtmlElement {
  boolean containsAttributes();

  boolean containsChildElements();

  IContainer<? extends IHtmlAttribute> getAttributes();

  IContainer<? extends IHtmlElement> getChildElements();

  String getInnerText();

  String getType();

  IHtmlElement withAttribute(IHtmlAttribute attribute, IHtmlAttribute... attributes);
}

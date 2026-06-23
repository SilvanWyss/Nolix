/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.html.htmlmodel;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IHtmlElement {
  boolean containsAttributes();

  boolean containsChildElements();

  ExtendedIterable<? extends IHtmlAttribute> getAttributes();

  ExtendedIterable<? extends IHtmlElement> getChildElements();

  String getInnerText();

  String getType();

  IHtmlElement withAdditionalAttributes(ExtendedIterable<IHtmlAttribute> additionalAttributes);

  IHtmlElement withAdditionalAttributes(IHtmlAttribute... additionalAttributes);
}

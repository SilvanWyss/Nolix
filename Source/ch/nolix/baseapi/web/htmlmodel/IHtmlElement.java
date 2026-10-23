/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.htmlmodel;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface IHtmlElement {
  /**
   * @return true if the current {@link IHtmlElement} contains attributes, false
   *         otherwise
   */
  boolean containsAttributes();

  /**
   * @return true if the current {@link IHtmlElement} contains child elements,
   *         false otherwise
   */
  boolean containsChildElements();

  /**
   * @return the {@link IHtmlAttribute}s of the current {@link IHtmlElement}
   */
  ExtendedIterable<? extends IHtmlAttribute> getAttributes();

  /**
   * @return the child elements of the current {@link IHtmlElement}
   */
  ExtendedIterable<? extends IHtmlElement> getChildElements();

  /**
   * @return the inner text of the current {@link IHtmlElement}
   */
  String getInnerText();

  /**
   * @return the type of the current {@link IHtmlElement}
   */
  String getType();

  /**
   * @param additionalAttributes
   * @return a new {@link IHtmlElement} with the content of the current
   *         {@link IHtmlElement} and the given additionalAttributes
   */
  IHtmlElement withAdditionalAttributes(ExtendedIterable<IHtmlAttribute> additionalAttributes);

  /**
   * @param additionalAttributes
   * @return a new {@link IHtmlElement} with the content of the current
   *         {@link IHtmlElement} and the given additionalAttributes
   */
  IHtmlElement withAdditionalAttributes(IHtmlAttribute... additionalAttributes);
}

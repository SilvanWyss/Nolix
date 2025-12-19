package ch.nolix.coreapi.web.htmlelementmodel;

import ch.nolix.coreapi.container.base.IContainer;

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

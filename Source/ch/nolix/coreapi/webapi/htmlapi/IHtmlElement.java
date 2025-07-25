package ch.nolix.coreapi.webapi.htmlapi;

import ch.nolix.coreapi.container.base.IContainer;

public interface IHtmlElement {

  boolean containsAttributes();

  boolean containsChildElements();

  IContainer<? extends IHtmlAttribute> getAttributes();

  IContainer<? extends IHtmlElement> getChildElements();

  String getInnerText();

  String getType();

  IHtmlElement withAttribute(IHtmlAttribute attribute, IHtmlAttribute... attributes);
}

package ch.nolix.coreapi.webapi.htmlapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IHtmlElement {

  boolean containsAttributes();

  boolean containsChildElements();

  IContainer<? extends IHtmlAttribute> getAttributes();

  IContainer<? extends IHtmlElement> getChildElements();

  String getInnerText();

  String getType();

  IHtmlElement withAttribute(IHtmlAttribute attribute, IHtmlAttribute... attributes);
}

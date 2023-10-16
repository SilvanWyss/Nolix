//package declaration
package ch.nolix.coreapi.webapi.htmlapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IHtmlElement {

  //method declaration
  boolean containsAttributes();

  //method declaration
  boolean containsChildElements();

  //method declaration
  IContainer<? extends IHtmlAttribute> getAttributes();

  //method declaration
  IContainer<? extends IHtmlElement> getChildElements();

  //method declaration
  String getInnerText();

  //method declaration
  String getType();

  //method declaration
  IHtmlElement withAttribute(final IHtmlAttribute attribute, IHtmlAttribute... attributes);
}

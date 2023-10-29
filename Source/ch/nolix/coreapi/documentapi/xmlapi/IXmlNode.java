//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi.IFluentMutableOptionalNameHolder;
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalValueHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IXmlNode<N extends IXmlNode<N>>
extends IFluentMutableOptionalNameHolder<N>, IOptionalValueHolder<String> {

  //method declaration
  IContainer<IXmlAttribute> getAttributes();

  //method declaration
  boolean containsAttributes();

  //method declaration
  boolean containsChildNodes();

  //method declaration
  IContainer<N> getStoredChildNodes();

  //method declaration
  boolean hasMixedContent();
}

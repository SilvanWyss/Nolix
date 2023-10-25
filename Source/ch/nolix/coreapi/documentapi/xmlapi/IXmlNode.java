//package declaration
package ch.nolix.coreapi.documentapi.xmlapi;

import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi.IFluentOptionalNameHolder;
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalValueHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IXmlNode<N extends IXmlNode<N>> extends IFluentOptionalNameHolder<N>, IOptionalValueHolder<String> {

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

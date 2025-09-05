package ch.nolix.coreapi.document.xml;

import ch.nolix.coreapi.attribute.fluentmutableoptionalattribute.IFluentMutableOptionalNameHolder;
import ch.nolix.coreapi.attribute.optionalattribute.IOptionalValueHolder;
import ch.nolix.coreapi.container.base.IContainer;

public interface IXmlNode<N extends IXmlNode<N>>
extends IFluentMutableOptionalNameHolder<N>, IOptionalValueHolder<String> {
  IContainer<IXmlAttribute> getAttributes();

  boolean containsAttributes();

  boolean containsChildNodes();

  IContainer<N> getStoredChildNodes();

  boolean hasMixedContent();
}

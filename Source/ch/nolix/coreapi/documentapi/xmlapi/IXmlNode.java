package ch.nolix.coreapi.documentapi.xmlapi;

import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi.IFluentMutableOptionalNameHolder;
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalValueHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IXmlNode<N extends IXmlNode<N>>
extends IFluentMutableOptionalNameHolder<N>, IOptionalValueHolder<String> {

  IContainer<IXmlAttribute> getAttributes();

  boolean containsAttributes();

  boolean containsChildNodes();

  IContainer<N> getStoredChildNodes();

  boolean hasMixedContent();
}

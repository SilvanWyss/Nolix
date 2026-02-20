/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

import ch.nolix.baseapi.attribute.fluentmutableoptionalattribute.IFluentMutableOptionalNameHolder;
import ch.nolix.baseapi.attribute.optionalattribute.IOptionalValueHolder;
import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @param <N> is the type of a {@link IXmlNode}.
 */
public interface IXmlNode<N extends IXmlNode<N>>
extends IFluentMutableOptionalNameHolder<N>, IOptionalValueHolder<String> {
  IContainer<IXmlAttribute> getAttributes();

  boolean containsAttributes();

  boolean containsChildNodes();

  IContainer<N> getStoredChildNodes();

  boolean hasMixedContent();
}

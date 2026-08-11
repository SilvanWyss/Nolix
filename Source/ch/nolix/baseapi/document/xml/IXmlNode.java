/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

import ch.nolix.baseapi.attribute.fluentmutableoptionalattribute.FluentMutableOptionalNameHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <N> the type of a {@link IXmlNode}
 */
public interface IXmlNode<N extends IXmlNode<N>>
extends FluentMutableOptionalNameHolder<N> {
  ExtendedIterable<IXmlAttribute> getAttributes();

  boolean containsAttributes();

  boolean containsChildNodes();

  ExtendedIterable<N> getStoredChildNodes();

  String getValue();

  boolean hasMixedContent();

  boolean hasValue();
}

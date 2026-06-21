/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

import ch.nolix.baseapi.attribute.fluentmutableoptionalattribute.FluentMutableOptionalNameHolder;
import ch.nolix.baseapi.attribute.optionalattribute.OptionalValueHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <N> is the type of a {@link IXmlNode}.
 */
public interface IXmlNode<N extends IXmlNode<N>>
extends FluentMutableOptionalNameHolder<N>, OptionalValueHolder<String> {
  ExtendedIterable<IXmlAttribute> getAttributes();

  boolean containsAttributes();

  boolean containsChildNodes();

  ExtendedIterable<N> getStoredChildNodes();

  boolean hasMixedContent();
}

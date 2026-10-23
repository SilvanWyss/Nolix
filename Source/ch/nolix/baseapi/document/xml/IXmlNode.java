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
  /**
   * @return true if the current {@link IXmlNode} contains attributes, false
   *         otherwise
   */
  boolean containsAttributes();

  /**
   * @return true if the current {@link IXmlNode} contains child nodes, false
   *         otherwise
   */
  boolean containsChildNodes();

  /**
   * @return the attributes of the current {@link IXmlNode}
   */
  ExtendedIterable<IXmlAttribute> getAttributes();

  /**
   * @return the child nodes of the current {@link IXmlNode}
   */
  ExtendedIterable<N> getStoredChildNodes();

  /**
   * @return the value of the current {@link IXmlNode}
   * @throws RuntimeException if the current {@link IXmlNode} does not have a
   *                          value
   */
  String getValue();

  /**
   * @return true if the current {@link IXmlNode} has a value and child nodes,
   *         false otherwise
   */
  boolean hasMixedContent();

  /**
   * @return true if the current {@link IXmlNode} has a value, false otherwise
   */
  boolean hasValue();
}

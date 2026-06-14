/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

import ch.nolix.baseapi.attribute.fluentmutableoptionalattribute.IFluentMutableOptionalNameHolder;
import ch.nolix.baseapi.attribute.optionalattribute.IOptionalValueHolder;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 * @param <N> is the type of a {@link IXmlNode}.
 */
public interface IXmlNode<N extends IXmlNode<N>>
extends IFluentMutableOptionalNameHolder<N>, IOptionalValueHolder<String> {
  IWellOrderContainer<IXmlAttribute> getAttributes();

  boolean containsAttributes();

  boolean containsChildNodes();

  IWellOrderContainer<N> getStoredChildNodes();

  boolean hasMixedContent();
}

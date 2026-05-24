/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.base;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.document.node.INode;

/**
 * A {@link AttributesRepresentable} can be represented by a collection of
 * attributes.
 * 
 * @author Silvan Wyss
 */
public interface AttributesRepresentable {
  /**
   * @return the attributes of the current {@link AttributesRepresentable}
   */
  IContainer<INode<?>> getAttributes();
}

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.node;

/**
 * @author Silvan Wyss
 */
public interface NodeRepresentable {
  /**
   * @return a new immutable {@link INode} that represents the current
   *         {@link NodeRepresentable}
   */
  INode<?> toNode();
}

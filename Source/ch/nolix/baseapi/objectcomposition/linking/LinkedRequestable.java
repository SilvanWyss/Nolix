/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.linking;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface LinkedRequestable {
  IContainer<Object> getStoredLinkedObjects();

  boolean isLinkedTo(Object object);

  boolean isLinkedToAnObject();
}

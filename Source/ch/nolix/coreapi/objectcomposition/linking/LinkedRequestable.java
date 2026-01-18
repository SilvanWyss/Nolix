/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.objectcomposition.linking;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface LinkedRequestable {
  IContainer<Object> getStoredLinkedObjects();

  boolean isLinkedTo(Object object);

  boolean isLinkedToAnObject();
}

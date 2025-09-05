package ch.nolix.coreapi.objectcomposition.linking;

import ch.nolix.coreapi.container.base.IContainer;

public interface LinkedRequestable {
  IContainer<Object> getStoredLinkedObjects();

  boolean isLinkedTo(Object object);

  boolean isLinkedToAnObject();
}

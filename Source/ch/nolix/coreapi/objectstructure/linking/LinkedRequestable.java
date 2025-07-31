package ch.nolix.coreapi.objectstructure.linking;

import ch.nolix.coreapi.container.base.IContainer;

public interface LinkedRequestable {

  IContainer<Object> getStoredLinkedObjects();

  boolean isLinkedTo(Object object);

  boolean isLinkedToAnObject();
}

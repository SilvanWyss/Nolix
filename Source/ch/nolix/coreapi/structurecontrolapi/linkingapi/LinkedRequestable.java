package ch.nolix.coreapi.structurecontrolapi.linkingapi;

import ch.nolix.coreapi.container.base.IContainer;

public interface LinkedRequestable {

  IContainer<Object> getStoredLinkedObjects();

  boolean isLinkedTo(Object object);

  boolean isLinkedToAnObject();
}

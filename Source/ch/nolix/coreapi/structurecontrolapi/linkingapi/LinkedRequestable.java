package ch.nolix.coreapi.structurecontrolapi.linkingapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface LinkedRequestable {

  IContainer<Object> getStoredLinkedObjects();

  boolean isLinkedTo(Object object);

  boolean isLinkedToAnObject();
}

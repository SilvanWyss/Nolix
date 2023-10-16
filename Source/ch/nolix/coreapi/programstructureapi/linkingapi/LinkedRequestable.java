//package declaration
package ch.nolix.coreapi.programstructureapi.linkingapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface LinkedRequestable {

  //method declaration
  IContainer<Object> getStoredLinkedObjects();

  //method declaration
  boolean isLinkedTo(Object object);

  //method declaration
  boolean isLinkedToAnObject();
}

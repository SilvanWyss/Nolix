//package declaration
package ch.nolix.coreapi.containerapi.singlecontainerapi;

import ch.nolix.coreapi.methodapi.requestapi.EmptinessRequestable;

//interface
public interface ISingleContainer<E> extends EmptinessRequestable {

  //method declaration
  E getStoredElement();
}

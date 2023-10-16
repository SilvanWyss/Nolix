//package declaration
package ch.nolix.coreapi.containerapi.singlecontainerapi;

import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;

//interface
public interface ISingleContainer<E> extends EmptinessRequestable {

  //method declaration
  E getStoredElement();
}

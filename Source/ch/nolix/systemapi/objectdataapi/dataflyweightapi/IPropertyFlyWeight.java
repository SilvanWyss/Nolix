//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

import ch.nolix.coreapi.functionapi.requestapi.VoidnessRequestable;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {

  //method declaration
  void noteUpdate();

  //method declaration
  void setUpdateAction(Runnable updateAction);
}

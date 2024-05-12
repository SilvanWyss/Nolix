//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

import ch.nolix.coreapi.stateapi.staterequestapi.VoidnessRequestable;

//class
public interface IFieldFlyWeight extends VoidnessRequestable {

  //method declaration
  void noteUpdate();

  //method declaration
  void setUpdateAction(Runnable updateAction);
}

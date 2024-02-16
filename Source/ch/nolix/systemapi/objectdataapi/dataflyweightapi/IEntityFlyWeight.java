//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

import ch.nolix.coreapi.generalstateapi.staterequestapi.VoidnessRequestable;

//interface
public interface IEntityFlyWeight extends VoidnessRequestable {

  //method declaration
  void noteInsert();

  //method declaration
  void setInsertAction(Runnable insertAction);
}

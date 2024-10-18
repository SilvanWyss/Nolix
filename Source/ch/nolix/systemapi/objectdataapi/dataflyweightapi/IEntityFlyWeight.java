package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

import ch.nolix.coreapi.stateapi.staterequestapi.VoidnessRequestable;

public interface IEntityFlyWeight extends VoidnessRequestable {

  void noteInsert();

  void setInsertAction(Runnable insertAction);
}

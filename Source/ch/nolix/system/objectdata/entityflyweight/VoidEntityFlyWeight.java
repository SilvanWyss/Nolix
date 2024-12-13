package ch.nolix.system.objectdata.entityflyweight;

import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IEntityFlyWeight;

public final class VoidEntityFlyWeight implements IEntityFlyWeight {

  @Override
  public boolean isVoid() {
    return true;
  }

  @Override
  public void noteInsert() {
    //Does nothing.
  }
}

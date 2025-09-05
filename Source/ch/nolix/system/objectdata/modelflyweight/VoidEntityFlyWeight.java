package ch.nolix.system.objectdata.modelflyweight;

import ch.nolix.systemapi.objectdata.modelflyweight.IEntityFlyWeight;

public final class VoidEntityFlyWeight implements IEntityFlyWeight {
  @Override
  public boolean isVoid() {
    return true;
  }

  @Override
  public void noteInsertIntoDatabase() {
    //Does nothing.
  }
}

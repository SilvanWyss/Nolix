package ch.nolix.system.objectdata.fieldflyweight;

import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IFieldFlyWeight;

public final class VoidFieldFlyWeight implements IFieldFlyWeight {

  @Override
  public boolean isVoid() {
    return true;
  }

  @Override
  public void noteUpdate() {
    //Does nothing.
  }
}

package ch.nolix.system.objectdata.modelflyweight;

import ch.nolix.systemapi.objectdataapi.modelflyweightapi.IFieldFlyWeight;

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

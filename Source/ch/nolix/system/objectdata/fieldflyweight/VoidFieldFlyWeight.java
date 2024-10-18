package ch.nolix.system.objectdata.fieldflyweight;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
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

  @Override
  public void setUpdateAction(final Runnable updateAction) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUpdateAction");
  }
}

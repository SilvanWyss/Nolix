//package declaration
package ch.nolix.system.objectdata.fieldflyweight;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IFieldFlyWeight;

//class
public final class VoidFieldFlyWeight implements IFieldFlyWeight {

  //method
  @Override
  public boolean isVoid() {
    return true;
  }

  //method
  @Override
  public void noteUpdate() {
    //Does nothing.
  }

  //method
  @Override
  public void setUpdateAction(final Runnable updateAction) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUpdateAction");
  }
}

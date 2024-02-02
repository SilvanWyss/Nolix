//package declaration
package ch.nolix.system.objectdata.propertyflyweight;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IPropertyFlyWeight;

//class
public final class VoidPropertyFlyWeight implements IPropertyFlyWeight {

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

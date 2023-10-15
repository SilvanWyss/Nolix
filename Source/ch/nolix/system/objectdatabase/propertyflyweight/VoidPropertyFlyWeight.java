//package declaration
package ch.nolix.system.objectdatabase.propertyflyweight;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi.IPropertyFlyWeight;

//class
public final class VoidPropertyFlyWeight implements IPropertyFlyWeight {

  // method
  @Override
  public boolean isVoid() {
    return true;
  }

  // method
  @Override
  public void noteUpdate() {
    // Does nothing.
  }

  // method
  @Override
  public void setUpdateAction(final Runnable updateAction) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setUpdateAction");
  }
}

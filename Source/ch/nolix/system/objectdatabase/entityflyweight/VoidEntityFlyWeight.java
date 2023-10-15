//package declaration
package ch.nolix.system.objectdatabase.entityflyweight;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi.IEntityFlyWeight;

//class
public final class VoidEntityFlyWeight implements IEntityFlyWeight {

  // method
  @Override
  public boolean isVoid() {
    return true;
  }

  // method
  @Override
  public void noteInsert() {
    // Does nothing.
  }

  @Override
  public void setInsertAction(final Runnable insertAction) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setInsertAction");
  }
}

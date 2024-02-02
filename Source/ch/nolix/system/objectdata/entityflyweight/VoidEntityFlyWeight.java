//package declaration
package ch.nolix.system.objectdata.entityflyweight;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IEntityFlyWeight;

//class
public final class VoidEntityFlyWeight implements IEntityFlyWeight {

  //method
  @Override
  public boolean isVoid() {
    return true;
  }

  //method
  @Override
  public void noteInsert() {
    //Does nothing.
  }

  @Override
  public void setInsertAction(final Runnable insertAction) {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "setInsertAction");
  }
}

//package declaration
package ch.nolix.system.objectdatabase.entityflyweight;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi.IEntityFlyWeight;

//class
public final class EntityFlyWeight implements IEntityFlyWeight {

  //optional attribute
  private Runnable insertAction;

  @Override
  public boolean isVoid() {
    return false;
  }

  @Override
  public void noteInsert() {
    if (hasInsertAction()) {
      insertAction.run();
    }
  }

  @Override
  public void setInsertAction(final Runnable insertAction) {

    GlobalValidator.assertThat(insertAction).thatIsNamed("insert action").isNotNull();

    this.insertAction = insertAction;
  }

  //method
  private boolean hasInsertAction() {
    return (insertAction != null);
  }
}

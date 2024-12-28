package ch.nolix.system.objectdata.modelflyweight;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.modelflyweightapi.IEntityFlyWeight;

public final class EntityFlyWeight implements IEntityFlyWeight {

  private final Runnable insertAction;

  private EntityFlyWeight(final Runnable insertAction) {

    GlobalValidator.assertThat(insertAction).thatIsNamed("insert action").isNotNull();

    this.insertAction = insertAction;
  }

  public static EntityFlyWeight withInsertAction(final Runnable insertAction) {
    return new EntityFlyWeight(insertAction);
  }

  @Override
  public boolean isVoid() {
    return false;
  }

  @Override
  public void noteInsert() {
    insertAction.run();
  }
}

package ch.nolix.system.objectdata.modelflyweight;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdata.modelflyweight.IFieldFlyWeight;

public final class FieldFlyWeight implements IFieldFlyWeight {
  private final Runnable updateAction;

  private FieldFlyWeight(final Runnable updateAction) {
    Validator.assertThat(updateAction).thatIsNamed("update action").isNotNull();

    this.updateAction = updateAction;
  }

  public static FieldFlyWeight wihUpdateAction(final Runnable updateAction) {
    return new FieldFlyWeight(updateAction);
  }

  @Override
  public boolean isVoid() {
    return false;
  }

  @Override
  public void noteUpdate() {
    updateAction.run();
  }
}

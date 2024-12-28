package ch.nolix.system.objectdata.modelflyweight;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.modelflyweightapi.IFieldFlyWeight;

public final class FieldFlyWeight implements IFieldFlyWeight {

  private final Runnable updateAction;

  private FieldFlyWeight(final Runnable updateAction) {

    GlobalValidator.assertThat(updateAction).thatIsNamed("update action").isNotNull();

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

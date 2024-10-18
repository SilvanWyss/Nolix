package ch.nolix.system.objectdata.fieldflyweight;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IFieldFlyWeight;

public final class FieldFlyWeight implements IFieldFlyWeight {

  private Runnable updateAction;

  @Override
  public boolean isVoid() {
    return false;
  }

  @Override
  public void noteUpdate() {
    if (hasUpdateAction()) {
      updateAction.run();
    }
  }

  @Override
  public void setUpdateAction(final Runnable updateAction) {

    GlobalValidator.assertThat(updateAction).thatIsNamed("update action").isNotNull();

    this.updateAction = updateAction;
  }

  private boolean hasUpdateAction() {
    return (updateAction != null);
  }
}

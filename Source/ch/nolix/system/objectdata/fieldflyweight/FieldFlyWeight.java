//package declaration
package ch.nolix.system.objectdata.fieldflyweight;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IFieldFlyWeight;

//class
public final class FieldFlyWeight implements IFieldFlyWeight {

  //optional attribute
  private Runnable updateAction;

  //method
  @Override
  public boolean isVoid() {
    return false;
  }

  //method
  @Override
  public void noteUpdate() {
    if (hasUpdateAction()) {
      updateAction.run();
    }
  }

  //method
  @Override
  public void setUpdateAction(final Runnable updateAction) {

    GlobalValidator.assertThat(updateAction).thatIsNamed("update action").isNotNull();

    this.updateAction = updateAction;
  }

  //method
  private boolean hasUpdateAction() {
    return (updateAction != null);
  }
}

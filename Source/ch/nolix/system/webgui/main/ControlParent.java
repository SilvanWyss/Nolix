//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

//class
final class ControlParent {

  //static method
  public static ControlParent forControl(final IControl<?, ?> control) {
    return new ControlParent(control);
  }

  //static method
  public static ControlParent forLayer(final ILayer<?> layer) {
    return new ControlParent(layer);
  }

  //static method
  public ControlParent withLayer(final ILayer<?> layer) {
    return new ControlParent(layer);
  }

  //optional attribute
  private final ILayer<?> layer;

  //optional attribute
  private final IControl<?, ?> control;

  //constructor
  private ControlParent(final IControl<?, ?> control) {

    GlobalValidator.assertThat(control).thatIsNamed(IControl.class).isNotNull();

    layer = null;
    this.control = control;
  }

  //constructor
  private ControlParent(final ILayer<?> layer) {

    GlobalValidator.assertThat(layer).thatIsNamed(ILayer.class).isNotNull();

    this.layer = layer;
    control = null;
  }

  //method
  public boolean belongsToGui() {

    if (isControl()) {
      return control.belongsToGui();
    }

    return layer.belongsToGui();
  }

  //method
  public boolean belongsToLayer() {

    if (isControl()) {
      return control.belongsToLayer();
    }

    return true;
  }

  //method
  public IControl<?, ?> getStoredControl() {

    assertIsControl();

    return control;
  }

  //method
  public Object getStoredElement() {

    if (isControl()) {
      return control;
    }

    return layer;
  }

  //method
  public ILayer<?> getStoredRootLayer() {

    if (isLayer()) {
      return layer;
    }

    return control.getStoredParentLayer();
  }

  //method
  public boolean isControl() {
    return (control != null);
  }

  //method
  public boolean isLayer() {
    return (layer != null);
  }

  //method
  private void assertIsControl() {
    if (!isControl()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not a Control");
    }
  }
}

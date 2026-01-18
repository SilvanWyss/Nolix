/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.ILayer;

final class ControlParent {
  private final ILayer<?> layer;

  private final IControl<?, ?> control;

  private ControlParent(final IControl<?, ?> control) {
    Validator.assertThat(control).thatIsNamed(IControl.class).isNotNull();

    layer = null;
    this.control = control;
  }

  private ControlParent(final ILayer<?> layer) {
    Validator.assertThat(layer).thatIsNamed(ILayer.class).isNotNull();

    this.layer = layer;
    control = null;
  }

  public static ControlParent forControl(final IControl<?, ?> control) {
    return new ControlParent(control);
  }

  public static ControlParent forLayer(final ILayer<?> layer) {
    return new ControlParent(layer);
  }

  public static ControlParent withLayer(final ILayer<?> layer) {
    return new ControlParent(layer);
  }

  public boolean belongsToGui() {
    if (isControl()) {
      return control.belongsToGui();
    }

    return layer.belongsToGui();
  }

  public boolean belongsToLayer() {
    if (isControl()) {
      return control.belongsToLayer();
    }

    return true;
  }

  public IControl<?, ?> getStoredControl() {
    assertIsControl();

    return control;
  }

  public Object getStoredElement() {
    if (isControl()) {
      return control;
    }

    return layer;
  }

  public ILayer<?> getStoredRootLayer() {
    if (isLayer()) {
      return layer;
    }

    return control.getStoredParentLayer();
  }

  public boolean isControl() {
    return (control != null);
  }

  public boolean isLayer() {
    return (layer != null);
  }

  private void assertIsControl() {
    if (!isControl()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not a Control");
    }
  }
}

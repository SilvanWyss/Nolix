/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.IControlParent;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class ControlParent implements IControlParent {
  private final ILayer layer;

  private final Control<?, ?> control;

  private ControlParent(final Control<?, ?> control) {
    Validator.assertThat(control).thatIsNamed(Control.class).isNotNull();

    this.layer = null;
    this.control = control;
  }

  private ControlParent(final ILayer layer) {
    Validator.assertThat(layer).thatIsNamed(ILayer.class).isNotNull();

    this.layer = layer;
    this.control = null;
  }

  public static ControlParent forControl(final Control<?, ?> control) {
    return new ControlParent(control);
  }

  public static ControlParent forLayer(final ILayer layer) {
    return new ControlParent(layer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToControl() {
    return isControl() && control.belongsToControl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToGui() {
    if (isControl()) {
      return control.belongsToGui();
    }

    return layer.belongsToGui();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToLayer() {
    if (isControl()) {
      return control.belongsToLayer();
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Control<?, ?> getStoredControl() {
    assertIsControl();

    return control;
  }

  @Override
  public ILayer getStoredLayer() {
    assertIsLayer();

    return layer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Control<?, ?> getStoredParentControl() {
    assertBelongsToControl();

    return control.getStoredParentControl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWebGui<?> getStoredParentGui() {
    if (isControl()) {
      return control.getStoredParentGui();
    }

    return layer.getStoredParentGui();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILayer getStoredParentLayer() {
    if (isLayer()) {
      return layer;
    }

    return control.getStoredParentLayer();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isControl() {
    return control != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isLayer() {
    return layer != null;
  }

  private void assertBelongsToControl() {
    if (!belongsToControl()) {
      throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(this, Control.class);
    }
  }

  private void assertIsControl() {
    if (!isControl()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not a Control");
    }
  }

  private void assertIsLayer() {
    if (!isLayer()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not a Control");
    }
  }
}

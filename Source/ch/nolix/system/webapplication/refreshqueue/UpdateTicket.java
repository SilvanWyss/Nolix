/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.refreshqueue;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class UpdateTicket {
  private final boolean updateConstellationOrStyle;

  private final IWebGui<?> webGui;

  private final ImmutableList<IControl<?, ?>> controls;

  private UpdateTicket(final IWebGui<?> webGui, final boolean updateConstellationOrStyle) {
    Validator.assertThat(webGui).thatIsNamed(IWebGui.class).isNotNull();

    this.updateConstellationOrStyle = updateConstellationOrStyle;
    this.webGui = webGui;
    controls = null;
  }

  private UpdateTicket(final ExtendedIterable<IControl<?, ?>> controls, final boolean updateConstellationOrStyle) {
    this.updateConstellationOrStyle = updateConstellationOrStyle;
    webGui = null;
    this.controls = ImmutableList.fromIterable(controls);
  }

  public static UpdateTicket forControls(
    final ExtendedIterable<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    return new UpdateTicket(controls, updateConstellationOrStyle);
  }

  public static UpdateTicket forWebGui(
    final IWebGui<?> webGui,
    final boolean updateConstellationOrStyle) {
    return new UpdateTicket(webGui, updateConstellationOrStyle);
  }

  public ExtendedIterable<IControl<?, ?>> getStoredControls() {
    assertIsForSpecificControls();

    return controls;
  }

  public IWebGui<?> getStoredWebGui() {
    assertIsForWholeWebGui();

    return webGui;
  }

  public boolean isForWholeWebGui() {
    return (webGui != null);
  }

  public boolean isForSpecificControls() {
    return !isForWholeWebGui();
  }

  public boolean shouldUpdateConstellationOrStyle() {
    return updateConstellationOrStyle;
  }

  private void assertIsForSpecificControls() {
    if (!isForSpecificControls()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not for specifici controls");
    }
  }

  private void assertIsForWholeWebGui() {
    if (!isForWholeWebGui()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not for whole web GUI");
    }
  }
}

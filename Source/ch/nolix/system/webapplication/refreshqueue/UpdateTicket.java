/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.refreshqueue;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class UpdateTicket {
  private final boolean updateConstellationOrStyle;

  private final IWebGui<?> webGui;

  private final ImmutableList<Control<?, ?>> controls;

  private UpdateTicket(final IWebGui<?> webGui, final boolean updateConstellationOrStyle) {
    Validator.assertThat(webGui).thatIsNamed(IWebGui.class).isNotNull();

    this.updateConstellationOrStyle = updateConstellationOrStyle;
    this.webGui = webGui;
    controls = null;
  }

  private UpdateTicket(final ExtendedIterable<Control<?, ?>> controls, final boolean updateConstellationOrStyle) {
    this.updateConstellationOrStyle = updateConstellationOrStyle;
    webGui = null;
    this.controls = ImmutableList.fromIterable(controls);
  }

  /**
   * @param controls
   * @param updateConstellationOrStyle
   * @return a new {@link UpdateTicket} for the given controls
   */
  public static UpdateTicket forControls(
    final ExtendedIterable<Control<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    return new UpdateTicket(controls, updateConstellationOrStyle);
  }

  /**
   * @param webGui
   * @param updateConstellationOrStyle
   * @return a new {@link UpdateTicket} for the given webGui
   */
  public static UpdateTicket forWebGui(
    final IWebGui<?> webGui,
    final boolean updateConstellationOrStyle) {
    return new UpdateTicket(webGui, updateConstellationOrStyle);
  }

  /**
   * @return the controls of the current {@link UpdateTicket}
   */
  public ExtendedIterable<Control<?, ?>> getStoredControls() {
    assertIsForSpecificControls();

    return controls;
  }

  /**
   * @return the web GUI of the current {@link UpdateTicket}
   */
  public IWebGui<?> getStoredWebGui() {
    assertIsForWholeWebGui();

    return webGui;
  }

  /**
   * @return true if the current {@link UpdateTicket} is for specific controls,
   *         false otherwise
   */
  public boolean isForSpecificControls() {
    return !isForWholeWebGui();
  }

  /**
   * @return true if the current {@link UpdateTicket} is for a whole web GUI,
   *         false otherwise
   */
  public boolean isForWholeWebGui() {
    return webGui != null;
  }

  /**
   * @return true if the current {@link UpdateTicket} should update the
   *         constellation or style, false otherwise
   */
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

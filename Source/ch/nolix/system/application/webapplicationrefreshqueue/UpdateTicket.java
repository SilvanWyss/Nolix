package ch.nolix.system.application.webapplicationrefreshqueue;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

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

  private UpdateTicket(final IContainer<IControl<?, ?>> controls, final boolean updateConstellationOrStyle) {
    this.updateConstellationOrStyle = updateConstellationOrStyle;
    webGui = null;
    this.controls = ImmutableList.forIterable(controls);
  }

  public static UpdateTicket forControls(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    return new UpdateTicket(controls, updateConstellationOrStyle);
  }

  public static UpdateTicket forWebGui(
    final IWebGui<?> webGui,
    final boolean updateConstellationOrStyle) {
    return new UpdateTicket(webGui, updateConstellationOrStyle);
  }

  public IContainer<IControl<?, ?>> getStoredControls() {

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

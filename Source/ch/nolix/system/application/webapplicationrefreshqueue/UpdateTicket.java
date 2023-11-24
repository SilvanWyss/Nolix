//package declaration
package ch.nolix.system.application.webapplicationrefreshqueue;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class UpdateTicket {

  //attribute
  private final boolean updateConstellationOrStyle;

  //optional attribute
  private final IWebGui<?> webGui;

  //multi-attribute
  private final ImmutableList<IControl<?, ?>> controls;

  //constructor
  private UpdateTicket(final IWebGui<?> webGui, final boolean updateConstellationOrStyle) {

    GlobalValidator.assertThat(webGui).thatIsNamed(IWebGui.class).isNotNull();

    this.updateConstellationOrStyle = updateConstellationOrStyle;
    this.webGui = webGui;
    controls = null;
  }

  //constructor
  private UpdateTicket(final IContainer<IControl<?, ?>> controls, final boolean updateConstellationOrStyle) {
    this.updateConstellationOrStyle = updateConstellationOrStyle;
    webGui = null;
    this.controls = ImmutableList.forIterable(controls);
  }

  //static method
  public static UpdateTicket forControls(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    return new UpdateTicket(controls, updateConstellationOrStyle);
  }

  //static method
  public static UpdateTicket forWebGui(
    final IWebGui<?> webGui,
    final boolean updateConstellationOrStyle) {
    return new UpdateTicket(webGui, updateConstellationOrStyle);
  }

  //method
  public IContainer<IControl<?, ?>> getStoredControls() {

    assertIsForSpecificControls();

    return controls;
  }

  //method
  public IWebGui<?> getStoredWebGui() {

    assertIsForWholeWebGui();

    return webGui;
  }

  //method
  public boolean isForWholeWebGui() {
    return (webGui != null);
  }

  //method
  public boolean isForSpecificControls() {
    return !isForWholeWebGui();
  }

  //method
  public boolean shouldUpdateConstellationOrStyle() {
    return updateConstellationOrStyle;
  }

  //method
  private void assertIsForSpecificControls() {
    if (!isForSpecificControls()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not for specifici controls");
    }
  }

  //method
  private void assertIsForWholeWebGui() {
    if (!isForWholeWebGui()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not for whole web GUI");
    }
  }
}

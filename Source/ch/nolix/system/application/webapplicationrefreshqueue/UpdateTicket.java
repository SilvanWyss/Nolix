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

  //optional attribute
  private final IWebGui<?> webGui;

  //multi-attribute
  private final ImmutableList<IControl<?, ?>> controls;

  //constructor
  private UpdateTicket(final IWebGui<?> webGui) {

    GlobalValidator.assertThat(webGui).thatIsNamed(IWebGui.class).isNotNull();

    this.webGui = webGui;
    controls = null;
  }

  //constructor
  private UpdateTicket(final IContainer<IControl<?, ?>> controls) {
    webGui = null;
    this.controls = ImmutableList.forIterable(controls);
  }

  //static method
  public static UpdateTicket forControls(final IContainer<IControl<?, ?>> controls) {
    return new UpdateTicket(controls);
  }

  //static method
  public static UpdateTicket forWebGui(final IWebGui<?> webGui) {
    return new UpdateTicket(webGui);
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

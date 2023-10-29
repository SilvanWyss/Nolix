//package declaration
package ch.nolix.system.application.maintestutil;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.main.BackendClient;
import ch.nolix.system.application.main.Session;

//class
public final class TestSession<BC extends BackendClient<BC, AC>, AC>
extends Session<BC, AC> {

  //attribute
  private final Class<?> clientClass;

  //constructor
  private TestSession(final Class<?> clientClass) {

    GlobalValidator.assertThat(clientClass).thatIsNamed("client class").isNotNull();

    this.clientClass = clientClass;
  }

  //static method
  public static <BC2 extends BackendClient<BC2, AC2>, AC2> TestSession<BC2, AC2> withClientClass(
    final Class<BC2> clientClass) {
    return new TestSession<>(clientClass);
  }

  //method
  @Override
  protected void fullInitialize() {
    //Does nothing.
  }

  //method
  @Override
  protected Class<?> getClientClass() {
    return clientClass;
  }

  //method
  @Override
  protected void updateCounterpart() {
    //Does nothing.
  }
}

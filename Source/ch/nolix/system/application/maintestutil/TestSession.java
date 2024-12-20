package ch.nolix.system.application.maintestutil;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.main.BackendClient;
import ch.nolix.system.application.main.Session;

public final class TestSession<BC extends BackendClient<BC, AC>, AC>
extends Session<BC, AC> {

  private final Class<?> clientClass;

  private TestSession(final Class<?> clientClass) {

    GlobalValidator.assertThat(clientClass).thatIsNamed("client class").isNotNull();

    this.clientClass = clientClass;
  }

  public static <BC2 extends BackendClient<BC2, AC2>, AC2> TestSession<BC2, AC2> withClientClass(
    final Class<BC2> clientClass) {
    return new TestSession<>(clientClass);
  }

  @Override
  public void refresh() {
    //Does nothing.
  }

  @Override
  protected void fullInitialize() {
    //Does nothing.
  }

  @Override
  protected Class<?> getClientClass() {
    return clientClass;
  }
}

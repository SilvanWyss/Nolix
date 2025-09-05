package ch.nolix.system.application.maintestutil;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.application.main.AbstractBackendClient;
import ch.nolix.system.application.main.AbstractSession;

public final class TestSession<C extends AbstractBackendClient<C, S>, S> extends AbstractSession<C, S> {
  private final Class<?> clientClass;

  private TestSession(final Class<?> clientClass) {
    Validator.assertThat(clientClass).thatIsNamed("client class").isNotNull();

    this.clientClass = clientClass;
  }

  public static <C2 extends AbstractBackendClient<C2, S2>, S2> TestSession<C2, S2> withClientClass(
    final Class<C2> clientClass) {
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

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.sessionservertestutil;

import ch.nolix.base.net.sessionserver.AbstractBackendClient;
import ch.nolix.base.net.sessionserver.AbstractSession;
import ch.nolix.base.net.sessionserver.Application;
import ch.nolix.base.validation.validator.Validator;

/**
 * @author Silvan Wyss
 * @param <C> the type of the parent {@link AbstractBackendClient} of a
 *            {@link TestSession}.
 * @param <S> the type of the application service of the parent
 *            {@link Application} of the parent {@link AbstractBackendClient} of
 *            a {@link TestSession}.
 */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void refresh() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fullInitialize() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?> getClientClass() {
    return clientClass;
  }
}

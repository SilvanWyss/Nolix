/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.validation.validator.Validator;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link AbstractBackendClient}s of a
 *            {@link StandardApplication}.
 * @param <S> the type of the application service of a {@link StandardApplication}.
 */
public final class StandardApplication<C extends AbstractBackendClient<C, S>, S> extends AbstractApplication<C, S> {
  private final String applicationName;

  private final Class<?> initialSessionClass;

  private <T extends AbstractSession<C, S>> StandardApplication(
    final String applicationName,
    final Class<T> initialSessionClass,
    final S applicationService) {
    super(applicationService);

    Validator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
    Validator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();

    this.applicationName = applicationName;
    this.initialSessionClass = initialSessionClass;
  }

  public static <C2 extends AbstractBackendClient<C2, S2>, T extends AbstractSession<C2, S2>, S2> //
  StandardApplication<C2, S2> //
  withNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final S2 applicationService) {
    return new StandardApplication<>(applicationName, initialSessionClass, applicationService);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return applicationName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?> getInitialSessionClass() {
    return initialSessionClass;
  }
}

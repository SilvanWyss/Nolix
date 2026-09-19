/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.clientserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link AbstractBackendClient}s of a
 *            {@link StandardApplication}
 * @param <S> the type of the application service of a
 *            {@link StandardApplication}
 */
public final class StandardApplication<C extends AbstractBackendClient<C, S>, S> extends AbstractApplication<C, S> {
  private final String name;

  private final Class<?> initialSessionClass;

  private <T extends AbstractSession<C, S>> StandardApplication(
    final String name,
    final S applicationService,
    final Class<T> initialSessionClass) {
    super(applicationService);

    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();
    Validator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();

    this.name = name;
    this.initialSessionClass = initialSessionClass;
  }

  public static <C2 extends AbstractBackendClient<C2, S2>, T extends AbstractSession<C2, S2>, S2> //
  StandardApplication<C2, S2> //
  withNameAndApplicationServiceAndInitialSessionClass(
    final String applicationName,
    final S2 applicationService,
    final Class<T> initialSessionClass) {
    return new StandardApplication<>(applicationName, applicationService, initialSessionClass);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?> getInitialSessionClass() {
    return initialSessionClass;
  }
}

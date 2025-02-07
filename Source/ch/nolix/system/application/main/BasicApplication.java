package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;

public final class BasicApplication<C extends AbstractBackendClient<C, S>, S> extends Application<C, S> {

  private final String applicationName;

  private final Class<?> initialSessionClass;

  private <T extends AbstractSession<C, S>> BasicApplication(
    final String applicationName,
    final Class<T> initialSessionClass,
    final S applicationService) {

    super(applicationService);

    GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
    GlobalValidator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();

    this.applicationName = applicationName;
    this.initialSessionClass = initialSessionClass;
  }

  public static <C2 extends AbstractBackendClient<C2, S2>, T extends AbstractSession<C2, S2>, S2> //
  BasicApplication<C2, S2> //
  withNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final S2 applicationService) {
    return new BasicApplication<>(applicationName, initialSessionClass, applicationService);
  }

  @Override
  public String getApplicationName() {
    return applicationName;
  }

  @Override
  protected Class<?> getInitialSessionClass() {
    return initialSessionClass;
  }
}

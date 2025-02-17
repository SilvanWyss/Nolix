package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;

public final class BasicApplication<C extends BackendClient<C, S>, S> extends Application<C, S> {

  private final String applicationName;

  private final Class<?> initialSessionClass;

  private <T extends Session<C, S>> BasicApplication(
    final String applicationName,
    final Class<T> initialSessionClass,
    final S applicationContext) {

    super(applicationContext);

    GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
    GlobalValidator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();

    this.applicationName = applicationName;
    this.initialSessionClass = initialSessionClass;
  }

  public static <C2 extends BackendClient<C2, S2>, T extends Session<C2, S2>, S2> BasicApplication<C2, S2> //
  withNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<T> initialSessionClass,
    final S2 applicationContext) {
    return new BasicApplication<>(applicationName, initialSessionClass, applicationContext);
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

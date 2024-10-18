package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;

public final class BasicApplication<BC extends BackendClient<BC, AC>, AC>
extends Application<BC, AC> {

  private final String applicationName;

  private final Class<?> initialSessionClass;

  private <S extends Session<BC, AC>> BasicApplication(
    final String applicationName,
    final Class<S> initialSessionClass,
    final AC applicationContext) {

    super(applicationContext);

    GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
    GlobalValidator.assertThat(initialSessionClass).thatIsNamed("initial session class").isNotNull();

    this.applicationName = applicationName;
    this.initialSessionClass = initialSessionClass;
  }

  public static <BC2 extends BackendClient<BC2, AC2>, S extends Session<BC2, AC2>, AC2> BasicApplication<BC2, AC2> //
  withNameAndInitialSessionClassAndContext(
    final String applicationName,
    final Class<S> initialSessionClass,
    final AC2 applicationContext) {
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

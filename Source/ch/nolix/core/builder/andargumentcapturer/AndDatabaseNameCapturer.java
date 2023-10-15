//package declaration
package ch.nolix.core.builder.andargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public class AndDatabaseNameCapturer<N> extends ArgumentCapturer<String, N> {

  // method
  public AndDatabaseNameCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  // method
  public final N andDatabase(final String databaseName) {

    GlobalValidator.assertThat(databaseName).thatIsNamed(LowerCaseCatalogue.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetNext(databaseName);
  }

  // method
  public final String getDatabaseName() {
    return getStoredArgument();
  }
}

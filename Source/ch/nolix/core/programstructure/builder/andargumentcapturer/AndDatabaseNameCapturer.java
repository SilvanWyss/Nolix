//package declaration
package ch.nolix.core.programstructure.builder.andargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndDatabaseNameCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public AndDatabaseNameCapturer() {
  }

  //constructor
  public AndDatabaseNameCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final N andDatabase(final String databaseName) {

    GlobalValidator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalogue.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetNext(databaseName);
  }

  //method
  public final String getDatabaseName() {
    return getStoredArgument();
  }
}

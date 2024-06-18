//package declaration
package ch.nolix.core.programstructure.builder.andargumentcaptor;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCaptor;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndDatabaseNameCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public AndDatabaseNameCaptor() {
  }

  //constructor
  public AndDatabaseNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
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

//package declaration
package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
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

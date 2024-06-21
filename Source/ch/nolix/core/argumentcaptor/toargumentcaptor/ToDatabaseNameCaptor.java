//package declaration
package ch.nolix.core.argumentcaptor.toargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class ToDatabaseNameCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public ToDatabaseNameCaptor() {
  }

  //constructor
  public ToDatabaseNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final String getDatabaseName() {
    return getStoredArgument();
  }

  //method
  public final N toDatabase(final String databaseName) {

    GlobalValidator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalogue.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetNext(databaseName);
  }
}

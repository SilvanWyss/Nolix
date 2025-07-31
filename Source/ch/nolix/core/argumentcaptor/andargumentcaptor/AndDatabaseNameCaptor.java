package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public class AndDatabaseNameCaptor<N> extends ArgumentCaptor<String, N> {

  public AndDatabaseNameCaptor() {
  }

  public AndDatabaseNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andDatabase(final String databaseName) {

    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetNext(databaseName);
  }

  public final String getDatabaseName() {
    return getStoredArgument();
  }
}

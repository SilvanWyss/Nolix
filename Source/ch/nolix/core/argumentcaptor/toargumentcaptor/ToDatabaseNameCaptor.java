package ch.nolix.core.argumentcaptor.toargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public class ToDatabaseNameCaptor<N> extends ArgumentCaptor<String, N> {
  public ToDatabaseNameCaptor() {
  }

  public ToDatabaseNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getDatabaseName() {
    return getStoredArgument();
  }

  public final N toDatabase(final String databaseName) {
    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetNext(databaseName);
  }
}

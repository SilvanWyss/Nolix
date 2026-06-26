/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <N> the type of the next thing of a {@link AndDatabaseNameCaptor}.
 */
public class AndDatabaseNameCaptor<N> extends ArgumentCaptor<String, N> {
  public AndDatabaseNameCaptor() {
  }

  public AndDatabaseNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andDatabase(final String databaseName) {
    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableNameCatalog.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetNext(databaseName);
  }

  public final String getDatabaseName() {
    return getStoredArgument();
  }
}

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.toargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <N> the type of the next thing of a {@link ToDatabaseNameCaptor}.
 */
public class ToDatabaseNameCaptor<N> extends AbstractArgumentCaptor<String, N> {
  public ToDatabaseNameCaptor() {
  }

  public ToDatabaseNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getDatabaseName() {
    return getStoredArgument();
  }

  public final N toDatabase(final String databaseName) {
    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableNameCatalog.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetStoredSuccessor(databaseName);
  }
}

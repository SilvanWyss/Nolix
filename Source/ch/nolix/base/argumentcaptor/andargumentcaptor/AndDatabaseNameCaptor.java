/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndDatabaseNameCaptor;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link AndDatabaseNameCaptor}
 */
public class AndDatabaseNameCaptor<S> extends AbstractArgumentCaptor<String, S> implements IAndDatabaseNameCaptor<S> {
  public AndDatabaseNameCaptor() {
  }

  public AndDatabaseNameCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andDatabase(final String databaseName) {
    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableNameCatalog.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetStoredSuccessor(databaseName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getDatabaseName() {
    return getStoredArgument();
  }
}

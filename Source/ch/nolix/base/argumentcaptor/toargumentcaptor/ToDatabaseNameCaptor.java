/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.toargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.toargumentcaptor.IToDatabaseNameCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ToDatabaseNameCaptor}.
 */
public class ToDatabaseNameCaptor<S> extends AbstractArgumentCaptor<String, S> implements IToDatabaseNameCaptor<S> {
  public ToDatabaseNameCaptor() {
  }

  public ToDatabaseNameCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getDatabaseName() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S toDatabase(final String databaseName) {
    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableNameCatalog.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetStoredSuccessor(databaseName);
  }
}

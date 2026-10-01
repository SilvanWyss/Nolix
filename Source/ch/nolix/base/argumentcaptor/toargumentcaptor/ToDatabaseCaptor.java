/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.toargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.toargumentcaptor.IToDatabaseCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ToDatabaseCaptor}
 */
public class ToDatabaseCaptor<S> extends AbstractArgumentCaptor<String, S> implements IToDatabaseCaptor<S> {
  public ToDatabaseCaptor() {
  }

  public ToDatabaseCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getDatabase() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S toDatabase(final String database) {
    Validator.assertThat(database).thatIsNamed(LowerCaseVariableNameCatalog.DATABASE).isNotBlank();

    return setArgumentAndGetStoredSuccessor(database);
  }
}

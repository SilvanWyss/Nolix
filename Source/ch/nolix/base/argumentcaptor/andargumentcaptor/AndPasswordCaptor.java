/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndPasswordCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link AndPasswordCaptor}
 */
public class AndPasswordCaptor<S> extends AbstractArgumentCaptor<String, S> implements IAndPasswordCaptor<S> {
  public AndPasswordCaptor() {
  }

  public AndPasswordCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andPassword(final String password) {
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableNameCatalog.PASSWORD).isNotNull();

    return setArgumentAndGetStoredSuccessor(password);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getPassword() {
    return getStoredArgument();
  }
}

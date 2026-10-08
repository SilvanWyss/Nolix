/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithPasswordCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link WithPasswordCaptor}
 */
public class WithPasswordCaptor<S> extends AbstractArgumentCaptor<String, S> implements IWithPasswordCaptor<S> {
  public WithPasswordCaptor() {
  }

  public WithPasswordCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getPassword() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withPassword(final String password) {
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableNameCatalog.PASSWORD).isNotNull();

    return setArgumentAndGetStoredSuccessor(password);
  }
}

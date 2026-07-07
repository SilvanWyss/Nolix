/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndLoginPasswordCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link AndLoginPasswordCaptor}
 */
public class AndLoginPasswordCaptor<S> extends AbstractArgumentCaptor<String, S> implements IAndLoginPasswordCaptor<S> {
  public AndLoginPasswordCaptor() {
  }

  public AndLoginPasswordCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andLoginPassword(final String loginPassword) {
    Validator.assertThat(loginPassword).thatIsNamed(LowerCaseVariableNameCatalog.LOGIN_PASSWORD).isNotBlank();

    return setArgumentAndGetStoredSuccessor(loginPassword);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getLoginPassword() {
    return getStoredArgument();
  }
}

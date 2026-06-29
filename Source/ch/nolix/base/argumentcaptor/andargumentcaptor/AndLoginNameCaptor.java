/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndLoginNameCaptor;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link AndLoginNameCaptor}
 */
public class AndLoginNameCaptor<S> extends AbstractArgumentCaptor<String, S> implements IAndLoginNameCaptor<S> {
  public AndLoginNameCaptor() {
  }

  public AndLoginNameCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andLoginName(final String loginName) {
    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableNameCatalog.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetStoredSuccessor(loginName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getLoginName() {
    return getStoredArgument();
  }
}

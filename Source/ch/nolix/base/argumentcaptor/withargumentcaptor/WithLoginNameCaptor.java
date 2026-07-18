/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithLoginNameCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link WithLoginNameCaptor}
 */
public class WithLoginNameCaptor<S> extends AbstractArgumentCaptor<String, S> implements IWithLoginNameCaptor<S> {
  public WithLoginNameCaptor() {
  }

  public WithLoginNameCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getLoginName() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withLoginName(final String loginName) {
    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableNameCatalog.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetStoredSuccessor(loginName);
  }
}

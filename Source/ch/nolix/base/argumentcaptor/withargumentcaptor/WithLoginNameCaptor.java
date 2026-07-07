/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <N> the type of the next thing of a {@link WithLoginNameCaptor}.
 */
public class WithLoginNameCaptor<N> extends AbstractArgumentCaptor<String, N> {
  public WithLoginNameCaptor() {
  }

  public WithLoginNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getLoginName() {
    return getStoredArgument();
  }

  public final N withLoginName(final String loginName) {
    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableNameCatalog.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetStoredSuccessor(loginName);
  }
}

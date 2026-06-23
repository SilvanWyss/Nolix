/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a {@link AndLoginNameCaptor}.
 */
public class AndLoginNameCaptor<N> extends ArgumentCaptor<String, N> {
  public AndLoginNameCaptor() {
  }

  public AndLoginNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andLoginName(final String loginName) {
    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableNameCatalog.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }

  public final String getLoginName() {
    return getStoredArgument();
  }
}

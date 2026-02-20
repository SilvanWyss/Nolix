/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a {@link WithLoginNameCaptor}.
 */
public class WithLoginNameCaptor<N> extends ArgumentCaptor<String, N> {
  public WithLoginNameCaptor() {
  }

  public WithLoginNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getLoginName() {
    return getStoredArgument();
  }

  public final N withLoginName(final String loginName) {
    Validator.assertThat(loginName).thatIsNamed(LowerCaseVariableCatalog.LOGIN_NAME).isNotBlank();

    return setArgumentAndGetNext(loginName);
  }
}

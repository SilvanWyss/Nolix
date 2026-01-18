/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a {@link AndLoginPasswordCaptor}.
 */
public class AndLoginPasswordCaptor<N> extends ArgumentCaptor<String, N> {
  public AndLoginPasswordCaptor() {
  }

  public AndLoginPasswordCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andLoginPassword(final String loginPassword) {
    Validator.assertThat(loginPassword).thatIsNamed(LowerCaseVariableCatalog.LOGIN_PASSWORD).isNotBlank();

    return setArgumentAndGetNext(loginPassword);
  }

  public final String getLoginPassword() {
    return getStoredArgument();
  }
}

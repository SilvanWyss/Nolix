/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a {@link AndPasswordCaptor}.
 */
public class AndPasswordCaptor<N> extends ArgumentCaptor<String, N> {
  public AndPasswordCaptor() {
  }

  public AndPasswordCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andPassword(final String password) {
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableCatalog.PASSWORD).isNotNull();

    return setArgumentAndGetNext(password);
  }

  public final String getPassword() {
    return getStoredArgument();
  }
}

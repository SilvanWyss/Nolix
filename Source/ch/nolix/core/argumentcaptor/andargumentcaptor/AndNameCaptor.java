/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a {@link AndNameCaptor}.
 */
public class AndNameCaptor<N> extends ArgumentCaptor<String, N> {
  public AndNameCaptor() {
  }

  public AndNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getName() {
    return getStoredArgument();
  }

  public final N andName(final String name) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    return setArgumentAndGetNext(name);
  }
}

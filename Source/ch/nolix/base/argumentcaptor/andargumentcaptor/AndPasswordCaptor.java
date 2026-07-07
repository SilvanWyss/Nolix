/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <N> the type of the next thing of a {@link AndPasswordCaptor}.
 */
public class AndPasswordCaptor<N> extends AbstractArgumentCaptor<String, N> {
  public AndPasswordCaptor() {
  }

  public AndPasswordCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andPassword(final String password) {
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableNameCatalog.PASSWORD).isNotNull();

    return setArgumentAndGetStoredSuccessor(password);
  }

  public final String getPassword() {
    return getStoredArgument();
  }
}

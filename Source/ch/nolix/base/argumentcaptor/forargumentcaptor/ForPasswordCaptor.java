/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForPasswordCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ForPasswordCaptor}
 */
public class ForPasswordCaptor<S> extends AbstractArgumentCaptor<String, S> implements IForPasswordCaptor<S> {
  public ForPasswordCaptor() {
  }

  public ForPasswordCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forPassword(String password) {
    Validator.assertThat(password).thatIsNamed(LowerCaseVariableNameCatalog.PASSWORD).isNotBlank();

    return setArgumentAndGetStoredSuccessor(password);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getPassword() {
    return getStoredArgument();
  }
}

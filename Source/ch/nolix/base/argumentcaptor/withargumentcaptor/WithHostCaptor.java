/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithHostCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link WithHostCaptor}
 */
public class WithHostCaptor<S> extends AbstractArgumentCaptor<String, S> implements IWithHostCaptor<S> {
  public WithHostCaptor() {
  }

  public WithHostCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getHost() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withHost(final String host) {
    Validator.assertThat(host).thatIsNamed(LowerCaseVariableNameCatalog.HOST).isNotBlank();

    return setArgumentAndGetStoredSuccessor(host);
  }
}

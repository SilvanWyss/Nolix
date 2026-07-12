/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForHostCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.IPv4Catalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ForHostCaptor}
 */
public class ForHostCaptor<S> extends AbstractArgumentCaptor<String, S> implements IForHostCaptor<S> {
  public ForHostCaptor() {
  }

  public ForHostCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forHost(final String host) {
    Validator.assertThat(host).thatIsNamed(LowerCaseVariableNameCatalog.HOST).isNotBlank();

    return setArgumentAndGetStoredSuccessor(host);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forLocalHost() {
    return setArgumentAndGetStoredSuccessor(IPv4Catalog.LOOP_BACK_ADDRESS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getHost() {
    return getStoredArgument();
  }
}

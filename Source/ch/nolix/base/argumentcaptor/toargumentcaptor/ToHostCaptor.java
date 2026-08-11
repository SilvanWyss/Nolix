/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.toargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.toargumentcaptor.IToHostCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.IPv4Catalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ToHostCaptor}
 */
public class ToHostCaptor<S> extends AbstractArgumentCaptor<String, S> implements IToHostCaptor<S> {
  public ToHostCaptor() {
  }

  public ToHostCaptor(final S nextArgumentCaptor) {
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
  public final S toHost(final String host) {
    Validator.assertThat(host).thatIsNamed(LowerCaseVariableNameCatalog.HOST).isNotBlank();

    return setArgumentAndGetStoredSuccessor(host);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S toLocalHost() {
    return toHost(IPv4Catalog.LOOP_BACK_ADDRESS);
  }
}

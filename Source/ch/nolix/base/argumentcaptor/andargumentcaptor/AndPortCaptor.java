/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.andargumentcaptor.IAndPortCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link AndPortCaptor}
 */
public class AndPortCaptor<S> extends AbstractArgumentCaptor<Integer, S> implements IAndPortCaptor<S> {
  public AndPortCaptor() {
  }

  public AndPortCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andHttpPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.HTTP);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andHttpsPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.HTTPS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andMsSqlPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.MS_SQL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andPort(final int port) {
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isPort();

    return setArgumentAndGetStoredSuccessor(port);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getPort() {
    return getStoredArgument();
  }
}

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.withargumentcaptor.IWithPortCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link WithPortCaptor}
 */
public class WithPortCaptor<S> extends AbstractArgumentCaptor<Integer, S> implements IWithPortCaptor<S> {
  public WithPortCaptor() {
  }

  public WithPortCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getPort() {
    return getStoredArgument();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withHttpPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.HTTP);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withHttpsPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.HTTPS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withMsSqlPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.MS_SQL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S withPort(final int port) {
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isPort();

    return setArgumentAndGetStoredSuccessor(port);
  }
}

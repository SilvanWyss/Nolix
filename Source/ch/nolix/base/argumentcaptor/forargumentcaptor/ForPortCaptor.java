/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.argumentcaptor.forargumentcaptor.IForPortCaptor;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ForPortCaptor}
 */
public class ForPortCaptor<S> extends AbstractArgumentCaptor<Integer, S> implements IForPortCaptor<S> {
  public ForPortCaptor() {
  }

  public ForPortCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forHttpPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.HTTP);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forHttpsPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.HTTPS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forMsSqlPort() {
    return setArgumentAndGetStoredSuccessor(PortCatalog.MS_SQL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S forPort(final int port) {
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

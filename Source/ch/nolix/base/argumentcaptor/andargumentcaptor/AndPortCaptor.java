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
 * @param <S> the type of the next thing of a {@link AndPortCaptor}.
 */
public class AndPortCaptor<S> extends AbstractArgumentCaptor<Integer, S> implements IAndPortCaptor<S> {
  public AndPortCaptor() {
  }

  public AndPortCaptor(final S nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final S andHttpPort() {
    return andPort(PortCatalog.HTTP);
  }

  public final S andHttpsPort() {
    return andPort(PortCatalog.HTTPS);
  }

  public final S andMsSqlPort() {
    return andPort(PortCatalog.MS_SQL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S andPort(final int port) {
    Validator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableNameCatalog.PORT)
      .isBetween(PortCatalog.MIN_PORT, PortCatalog.MAX_PORT);

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

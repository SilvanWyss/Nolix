/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.andargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;

/**
 * @author Silvan Wyss
 * @param <N> the type of the next thing of a {@link AndPortCaptor}.
 */
public class AndPortCaptor<N> extends AbstractArgumentCaptor<Integer, N> {
  public AndPortCaptor() {
  }

  public AndPortCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andHttpPort() {
    return andPort(PortCatalog.HTTP);
  }

  public final N andHttpsPort() {
    return andPort(PortCatalog.HTTPS);
  }

  public final N andMsSqlPort() {
    return andPort(PortCatalog.MS_SQL);
  }

  public final N andPort(final int port) {
    Validator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableNameCatalog.PORT)
      .isBetween(PortCatalog.MIN_PORT, PortCatalog.MAX_PORT);

    return setArgumentAndGetStoredSuccessor(port);
  }

  public final int getPort() {
    return getStoredArgument();
  }
}

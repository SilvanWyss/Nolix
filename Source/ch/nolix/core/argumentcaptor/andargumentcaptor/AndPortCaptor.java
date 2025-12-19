package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.net.netconstant.PortCatalog;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a {@link AndPortCaptor}.
 */
public class AndPortCaptor<N> extends ArgumentCaptor<Integer, N> {
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
      .thatIsNamed(LowerCaseVariableCatalog.PORT)
      .isBetween(PortCatalog.MIN_PORT, PortCatalog.MAX_PORT);

    return setArgumentAndGetNext(port);
  }

  public final int getPort() {
    return getStoredArgument();
  }
}

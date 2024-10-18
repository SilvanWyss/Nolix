package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public class AndPortCaptor<N> extends ArgumentCaptor<Integer, N> {

  public AndPortCaptor() {
  }

  public AndPortCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final N andHttpPort() {
    return andPort(PortCatalogue.HTTP);
  }

  public final N andHttpsPort() {
    return andPort(PortCatalogue.HTTPS);
  }

  public final N andMsSqlPort() {
    return andPort(PortCatalogue.MS_SQL);
  }

  public final N andPort(final int port) {

    GlobalValidator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableCatalogue.PORT)
      .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    return setArgumentAndGetNext(port);
  }

  public final int getPort() {
    return getStoredArgument();
  }
}

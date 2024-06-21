//package declaration
package ch.nolix.core.argumentcaptor.andargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndPortCaptor<N> extends ArgumentCaptor<Integer, N> {

  //constructor
  public AndPortCaptor() {
  }

  //constructor
  public AndPortCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final N andHttpPort() {
    return andPort(PortCatalogue.HTTP);
  }

  //method
  public final N andHttpsPort() {
    return andPort(PortCatalogue.HTTPS);
  }

  //method
  public final N andMsSqlPort() {
    return andPort(PortCatalogue.MS_SQL);
  }

  //method
  public final N andPort(final int port) {

    GlobalValidator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableCatalogue.PORT)
      .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    return setArgumentAndGetNext(port);
  }

  //method
  public final int getPort() {
    return getStoredArgument();
  }
}

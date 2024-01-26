//package declaration
package ch.nolix.core.programstructure.builder.andargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndPortCapturer<N> extends ArgumentCapturer<Integer, N> {

  //constructor
  public AndPortCapturer() {
  }

  //constructor
  public AndPortCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
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
    return andPort(PortCatalogue.MSSQL);
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

//package declaration
package ch.nolix.core.programstructure.builder.andargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
public class AndPortCapturer<N> extends ArgumentCapturer<Integer, N> {

  //attribute
  private final int defaultPort;

  //constructor
  public AndPortCapturer(final int defaultPort) {

    GlobalValidator
      .assertThat(defaultPort)
      .thatIsNamed("default port")
      .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    this.defaultPort = defaultPort;
  }

  //constructor
  public AndPortCapturer(final int defaultPort, final N nextArgumentCapturer) {

    super(nextArgumentCapturer);

    GlobalValidator
      .assertThat(defaultPort)
      .thatIsNamed("default port")
      .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    this.defaultPort = defaultPort;
  }

  //method
  public final N andDefaultPort() {
    return andPort(defaultPort);
  }

  //method
  public final N andPort(final int port) {

    GlobalValidator
      .assertThat(port)
      .thatIsNamed(LowerCaseCatalogue.PORT)
      .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    return setArgumentAndGetNext(port);
  }

  //method
  public final int getPort() {
    return getStoredArgument();
  }
}

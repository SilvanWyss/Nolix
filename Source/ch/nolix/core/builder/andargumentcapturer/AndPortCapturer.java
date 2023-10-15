//package declaration
package ch.nolix.core.builder.andargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public class AndPortCapturer<N> extends ArgumentCapturer<Integer, N> {

  // attribute
  private final int defaultPort;

  // constructor
  public AndPortCapturer(final int defaultPort, final N nextArgumentCapturer) {

    super(nextArgumentCapturer);

    GlobalValidator
        .assertThat(defaultPort)
        .thatIsNamed("default port")
        .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    this.defaultPort = defaultPort;
  }

  // method
  public final N andDefaultPort() {
    return andPort(defaultPort);
  }

  // method
  public final N andPort(final int port) {

    GlobalValidator
        .assertThat(port)
        .thatIsNamed(LowerCaseCatalogue.PORT)
        .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    return setArgumentAndGetNext(port);
  }

  // method
  public final int getPort() {
    return getStoredArgument();
  }
}

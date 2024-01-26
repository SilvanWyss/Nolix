//package declaration
package ch.nolix.core.programstructure.builder.toargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;

//class
public class ToIpOrDomainCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public ToIpOrDomainCapturer() {
  }

  //constructor
  public ToIpOrDomainCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final String getIpOrDomain() {
    return getStoredArgument();
  }

  //method
  public final N toIpOrDomain(final String ipOrDomain) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or domain").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  //method
  public final N toLocalAddress() {
    return toIpOrDomain(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }
}

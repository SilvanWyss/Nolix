//package declaration
package ch.nolix.core.builder.withargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.IPv4Catalogue;

//class
public class WithIpOrDomainCapturer<N> extends ArgumentCapturer<String, N> {

  // constructor
  public WithIpOrDomainCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  // method
  public final String getIpOrDomain() {
    return getStoredArgument();
  }

  // method
  public final N withIpOrDomain(final String ipOrDomain) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  // method
  public final N withLocalAddress() {
    return withIpOrDomain(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }
}

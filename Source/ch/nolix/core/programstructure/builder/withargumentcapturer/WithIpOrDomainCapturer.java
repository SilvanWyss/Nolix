//package declaration
package ch.nolix.core.programstructure.builder.withargumentcapturer;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;

//class
public class WithIpOrDomainCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public WithIpOrDomainCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final String getIpOrDomain() {
    return getStoredArgument();
  }

  //method
  public final N withIpOrDomain(final String ipOrDomain) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  //method
  public final N withLocalAddress() {
    return withIpOrDomain(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }
}

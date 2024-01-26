//package declaration
package ch.nolix.core.programstructure.builder.forargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;

//class
public class ForIpOrDomainCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public ForIpOrDomainCapturer() {
  }

  //constructor
  public ForIpOrDomainCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final String getIpOrDomain() {
    return getStoredArgument();
  }

  //method
  public final N forIpOrDomain(final String ipOrDomain) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or domain").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  //method
  public final N forLocalAddress() {
    return forIpOrDomain(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }
}

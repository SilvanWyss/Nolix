//package declaration
package ch.nolix.core.argumentcaptor.withargumentcaptor;

//own imports
import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;

//class
public class WithIpOrDomainCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public WithIpOrDomainCaptor() {
  }

  //constructor
  public WithIpOrDomainCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final String getIpOrDomain() {
    return getStoredArgument();
  }

  //method
  public final N withIpOrDomain(final String ipOrDomain) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or domain").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  //method
  public final N withLocalAddress() {
    return withIpOrDomain(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }
}

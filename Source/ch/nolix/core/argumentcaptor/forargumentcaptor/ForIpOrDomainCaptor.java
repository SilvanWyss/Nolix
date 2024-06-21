//package declaration
package ch.nolix.core.argumentcaptor.forargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;

//class
public class ForIpOrDomainCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public ForIpOrDomainCaptor() {
  }

  //constructor
  public ForIpOrDomainCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
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

//package declaration
package ch.nolix.core.programstructure.builder.toargumentcaptor;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCaptor;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;

//class
public class ToIpOrDomainCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public ToIpOrDomainCaptor() {
  }

  //constructor
  public ToIpOrDomainCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
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

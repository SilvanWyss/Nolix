package ch.nolix.core.argumentcaptor.withargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.net.netconstant.IPv4Catalog;

public class WithIpOrDomainCaptor<N> extends ArgumentCaptor<String, N> {
  public WithIpOrDomainCaptor() {
  }

  public WithIpOrDomainCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getIpOrDomain() {
    return getStoredArgument();
  }

  public final N withIpOrDomain(final String ipOrDomain) {
    Validator.assertThat(ipOrDomain).thatIsNamed("ip or domain").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  public final N withLocalAddress() {
    return withIpOrDomain(IPv4Catalog.LOOP_BACK_ADDRESS);
  }
}

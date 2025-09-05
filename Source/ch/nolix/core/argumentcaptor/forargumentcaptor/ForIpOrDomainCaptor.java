package ch.nolix.core.argumentcaptor.forargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.net.netconstant.IPv4Catalog;

public class ForIpOrDomainCaptor<N> extends ArgumentCaptor<String, N> {
  public ForIpOrDomainCaptor() {
  }

  public ForIpOrDomainCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getIpOrDomain() {
    return getStoredArgument();
  }

  public final N forIpOrDomain(final String ipOrDomain) {
    Validator.assertThat(ipOrDomain).thatIsNamed("ip or domain").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  public final N forLocalAddress() {
    return forIpOrDomain(IPv4Catalog.LOOP_BACK_ADDRESS);
  }
}

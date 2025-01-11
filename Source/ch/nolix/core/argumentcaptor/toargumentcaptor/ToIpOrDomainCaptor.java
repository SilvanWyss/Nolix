package ch.nolix.core.argumentcaptor.toargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalog;

public class ToIpOrDomainCaptor<N> extends ArgumentCaptor<String, N> {

  public ToIpOrDomainCaptor() {
  }

  public ToIpOrDomainCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  public final String getIpOrDomain() {
    return getStoredArgument();
  }

  public final N toIpOrDomain(final String ipOrDomain) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or domain").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  public final N toLocalAddress() {
    return toIpOrDomain(IPv4Catalog.LOOP_BACK_ADDRESS);
  }
}

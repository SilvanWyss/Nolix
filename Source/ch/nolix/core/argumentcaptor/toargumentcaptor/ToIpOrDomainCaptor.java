/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.argumentcaptor.toargumentcaptor;

import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.net.netconstant.IPv4Catalog;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a {@link ToIpOrDomainCaptor}.
 */
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
    Validator.assertThat(ipOrDomain).thatIsNamed("ip or domain").isNotBlank();

    return setArgumentAndGetNext(ipOrDomain);
  }

  public final N toLocalAddress() {
    return toIpOrDomain(IPv4Catalog.LOOP_BACK_ADDRESS);
  }
}

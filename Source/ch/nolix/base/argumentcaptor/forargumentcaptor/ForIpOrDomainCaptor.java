/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.forargumentcaptor;

import ch.nolix.base.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.net.netconstant.IPv4Catalog;

/**
 * @author Silvan Wyss
 * @param <N> is the type of the next thing of a {@link ForIpOrDomainCaptor}.
 */
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

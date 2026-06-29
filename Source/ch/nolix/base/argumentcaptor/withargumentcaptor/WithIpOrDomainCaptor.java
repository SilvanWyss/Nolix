/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.argumentcaptor.withargumentcaptor;

import ch.nolix.base.argumentcaptor.base.AbstractArgumentCaptor;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.net.netcatalog.IPv4Catalog;

/**
 * @author Silvan Wyss
 * @param <N> the type of the next thing of a {@link WithIpOrDomainCaptor}.
 */
public class WithIpOrDomainCaptor<N> extends AbstractArgumentCaptor<String, N> {
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

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.toargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IToHostCaptor}
 */
public interface IToHostCaptor<S> extends ArgumentCaptor<S> {
  String getHost();

  S toHost(final String host);

  S toLocalHost();
}

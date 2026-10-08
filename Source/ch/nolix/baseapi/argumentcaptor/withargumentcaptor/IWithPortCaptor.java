/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithPortCaptor}
 */
public interface IWithPortCaptor<S> extends ArgumentCaptor<S> {
  int getPort();

  S withHttpPort();

  S withHttpsPort();

  S withMsSqlPort();

  S withPort(int port);
}

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForPortCaptor}
 */
public interface IForPortCaptor<S> extends ArgumentCaptor<S> {
  S forHttpPort();

  S forHttpsPort();

  S forMsSqlPort();

  S forPort(int port);

  int getPort();
}

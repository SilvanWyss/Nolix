package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndHostCaptor}
 */
public interface IAndHostCaptor<S> extends ArgumentCaptor<S> {
  S andHost(String host);

  S andLocalHost();

  String getHost();
}

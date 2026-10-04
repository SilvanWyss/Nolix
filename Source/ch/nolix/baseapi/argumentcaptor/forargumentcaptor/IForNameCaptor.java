package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForNameCaptor}
 */
public interface IForNameCaptor<S> extends ArgumentCaptor<S> {
  S forName(String name);

  String getName();
}

package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForDatabaseCaptor}
 */
public interface IForDatabaseCaptor<S> extends ArgumentCaptor<S> {
  S forDatabase(String database);

  String getDatabase();
}

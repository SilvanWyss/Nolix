package ch.nolix.systemapi.databaseobject.model;

import ch.nolix.systemapi.databaseobject.request.DeletionRequestable;

/**
 * @author Silvan Wyss
 */
public interface Deletable extends DeletionRequestable {
  void delete();
}

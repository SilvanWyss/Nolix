package ch.nolix.systemapi.databaseobject.model;

import ch.nolix.systemapi.databaseobject.request.DeletionRequestable;

public interface Deletable extends DeletionRequestable {

  void delete();
}

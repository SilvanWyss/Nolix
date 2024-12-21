package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.DeletionRequestable;

public interface Deletable extends DeletionRequestable {

  void delete();
}

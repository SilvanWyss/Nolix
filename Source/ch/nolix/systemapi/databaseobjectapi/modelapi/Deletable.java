package ch.nolix.systemapi.databaseobjectapi.modelapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectrequestapi.DeletionRequestable;

public interface Deletable extends DeletionRequestable {

  void delete();
}

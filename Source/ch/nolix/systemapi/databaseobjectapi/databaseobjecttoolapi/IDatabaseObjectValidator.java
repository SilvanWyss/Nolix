package ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi;

import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

public interface IDatabaseObjectValidator extends IResourceValidator<IDatabaseObject> {

  void assertIsConnectedWithRealDatabase(IDatabaseObject databaseObject);

  void assertIsLoaded(IDatabaseObject databaseObject);

  void assertIsNew(IDatabaseObject databaseObject);

  void assertIsNotConnectedWithRealDatabase(IDatabaseObject databaseObject);

  void assertIsNotDeleted(IDatabaseObject databaseObject);

  void assertIsNotNew(IDatabaseObject databaseObject);
}

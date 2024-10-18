package ch.nolix.systemapi.databaseobjectapi.databasevalidatorapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

public interface IDatabaseObjectValidator {

  void assertIsLinkedWithRealDatabase(IDatabaseObject databaseObject);

  void assertIsLoaded(IDatabaseObject databaseObject);

  void assertIsNew(IDatabaseObject databaseObject);

  void assertIsNotDeleted(IDatabaseObject databaseObject);

  void assertIsNotLinkedWithRealDatabase(IDatabaseObject databaseObject);

  void assertIsNotNew(IDatabaseObject databaseObject);

  void assertIsOpen(IDatabaseObject databaseObject);
}

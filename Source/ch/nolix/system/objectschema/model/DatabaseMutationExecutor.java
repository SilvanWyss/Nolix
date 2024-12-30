package ch.nolix.system.objectschema.model;

final class DatabaseMutationExecutor {

  public void addTableToDatabase(
    final Database database,
    final Table table) {

    database.addTableAttribute(table);
    table.setParentDatabase(database);

    if (database.isConnectedWithRealDatabase()) {
      database.internalGetStoredRawSchemaAdapter().addTable(table);
    }

    database.internalSetEdited();
  }
}

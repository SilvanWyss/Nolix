package ch.nolix.system.objectschema.schema;

final class DatabaseMutationExecutor {

  public void addTableToDatabase(
    final Database database,
    final Table table) {

    database.addTableAttribute(table);
    table.setParentDatabase(database);

    if (database.isLinkedWithRealDatabase()) {
      database.internalGetRefRawSchemaAdapter().addTable(table);
    }

    database.internalSetEdited();
  }
}

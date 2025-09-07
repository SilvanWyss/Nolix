package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.system.objectschema.modelmutationexaminer.TableMutationExaminer;
import ch.nolix.system.objectschema.modeltool.ColumnTool;
import ch.nolix.system.objectschema.modeltool.DatabaseTool;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelexaminer.IDatabaseExaminer;
import ch.nolix.systemapi.objectschema.modelexaminer.ITableExaminer;
import ch.nolix.systemapi.objectschema.modelmutationexaminer.ITableMutationExaminer;
import ch.nolix.systemapi.objectschema.modeltool.IColumnTool;

public final class DatabaseExaminer implements IDatabaseExaminer {
  private static final ITableExaminer TABLE_EXAMINER = new TableExaminer();

  private static final ITableMutationExaminer TABLE_MUTATION_EXAMINER = new TableMutationExaminer();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

  @Override
  public boolean allBackReferencesAreValid(final IDatabase database) {
    return //
    new DatabaseTool()
      .getStoredAllBackReferenceColumns(database)
      .containsOnly(COLUMN_TOOL::isAValidBackReferenceColumn);
  }

  @Override
  public boolean canAddTable(final IDatabase database) {
    return //
    database != null
    && database.isOpen();
  }

  @Override
  public boolean canAddTable(final IDatabase database, final ITable table) {
    return //
    canAddTable(database)
    && TABLE_MUTATION_EXAMINER.canBeAddedToDatabase(table)
    && !containsTableWithName(database, table.getName())
    && canAddGivenTableBecauseOfColumns(database, table);
  }

  @Override
  public boolean canSetName(final String name) {
    return //
    name != null
    && !name.isBlank();
  }

  @Override
  public boolean containsBackReferencededColumnByColumn(
    final IDatabase database,
    final IColumn column) {
    //This part is not mandatory, but provides a better performance.
    if (!COLUMN_TOOL.isABackReferenceColumn(column)) {
      return false;
    }

    return //
    database != null
    && //
    database.getStoredTables().containsAny(t -> TABLE_EXAMINER.containsColumnThatIsBackReferencedByColumn(t, column));
  }

  @Override
  public boolean containsTable(final IDatabase database, ITable table) {
    return //
    database != null
    && database.getStoredTables().contains(table);
  }

  @Override
  public boolean containsTableReferencedByColumn(final IDatabase database, final IColumn column) {
    //This part is not mandatory, but provides a better performance.
    if (!COLUMN_TOOL.isAReferenceColumn(column)) {
      return false;
    }

    return //
    database != null
    && database.getStoredTables().containsAny(t -> COLUMN_TOOL.referencesGivenTable(column, t));
  }

  @Override
  public boolean containsTableWithColumn(final IDatabase database, final IColumn column) {
    return //
    database != null
    && database.getStoredTables().containsAny(t -> TABLE_EXAMINER.containsColumn(t, column));
  }

  @Override
  public boolean containsTableWithName(final IDatabase database, final String name) {
    return //
    database != null
    && database.getStoredTables().containsAny(t -> t.hasName(name));
  }

  private boolean canAddGivenTableBecauseOfColumns(final IDatabase database, final ITable table) {
    return table.getStoredColumns().containsOnly(c -> canAddGivenTableBecauseOfGivenColumn(database, table, c));
  }

  private boolean canAddGivenTableBecauseOfGivenColumn(
    final IDatabase database,
    final ITable table,
    final IColumn column) {
    final var baseFieldType = COLUMN_TOOL.getBaseFieldType(column);

    return //
    switch (baseFieldType) {
      case BASE_VALUE_FIELD ->
        true;
      case BASE_REFERENCE ->
        canAddGivenTableBecauseOfGivenReferenceColumn(database, table, column);
      case BASE_BACK_REFERENCE ->
        true;
      default ->
        true;
    };
  }

  private boolean canAddGivenTableBecauseOfGivenReferenceColumn(
    final IDatabase database,
    final ITable table,
    final IColumn referenceColumn) {
    return //
    containsTableReferencedByColumn(database, referenceColumn)
    || COLUMN_TOOL.referencesGivenTable(referenceColumn, table);
  }
}

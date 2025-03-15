package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.system.objectschema.modelmutationexaminer.TableMutationExaminer;
import ch.nolix.system.objectschema.schematool.ColumnTool;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.IDatabaseExaminer;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.ITableExaminer;
import ch.nolix.systemapi.objectschemaapi.modelmutationexaminer.ITableMutationExaminer;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IColumnTool;

public final class DatabaseExaminer implements IDatabaseExaminer {

  private static final ITableExaminer TABLE_EXAMINER = new TableExaminer();

  private static final ITableMutationExaminer TABLE_MUTATION_EXAMINER = new TableMutationExaminer();

  private static final IColumnTool COLUMN_TOOL = new ColumnTool();

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

    //This check is theoretically not necessary, but provides a better performance
    //for some cases.
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

    //This check is theoretically not necessary, but provides a better performance
    //for some cases.
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

    final var baseContentType = COLUMN_TOOL.getBaseFieldType(column);

    return switch (baseContentType) {
      case BASE_VALUE ->
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

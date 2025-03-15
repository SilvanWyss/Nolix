package ch.nolix.systemapi.objectschemaapi.modelexaminerapi;

import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface IDatabaseExaminer {

  boolean canAddTable(IDatabase database);

  boolean canAddTable(IDatabase database, ITable table);

  boolean canSetName(String name);

  boolean containsBackReferencededColumnByColumn(IDatabase database, IColumn column);

  boolean containsTable(IDatabase database, ITable table);

  boolean containsTableReferencedByColumn(IDatabase database, IColumn column);

  boolean containsTableWithColumn(IDatabase database, IColumn column);

  boolean containsTableWithName(IDatabase database, String name);
}

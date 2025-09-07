package ch.nolix.systemapi.objectschema.modelexaminer;

import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

public interface IDatabaseExaminer {
  boolean allBackReferencesAreValid(IDatabase database);

  boolean canAddTable(IDatabase database);

  boolean canAddTable(IDatabase database, ITable table);

  boolean canSetName(String name);

  boolean containsBackReferencededColumnByColumn(IDatabase database, IColumn column);

  boolean containsTable(IDatabase database, ITable table);

  boolean containsTableReferencedByColumn(IDatabase database, IColumn column);

  boolean containsTableWithColumn(IDatabase database, IColumn column);

  boolean containsTableWithName(IDatabase database, String name);
}

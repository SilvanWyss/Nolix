//package declaration
package ch.nolix.system.objectschema.schemahelper;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
public final class DatabaseHelper extends DatabaseObjectHelper implements IDatabaseHelper{
	
	//static attributes
	private static final ITableHelper tableHelper = new TableHelper();
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	@Override
	public boolean allBackReferencesAreValid(final IDatabase<?> database) {
		return getRefAllBackReferenceColumns(database).containsOnly(columnHelper::isAValidBackReferenceColumn);
	}
	
	//method
	@Override
	public void assertAllBackReferencesAreValid(final IDatabase<?> database) {
		if (!allBackReferencesAreValid(database)) {
			throw new InvalidArgumentException(database, "contains invalid back references");
		}
	}
	
	//method
	@Override
	public void assertCanAddGivenTable(final IDatabase<?> database, final ITable<?> table) {
		if (!canAddGivenTable(database, table)) {
			throw new InvalidArgumentException(database, "cannot add the given table '" + table.getName() + "'");
		}
	}
	
	//method
	@Override
	public void assertCanSetGivenNameToDatabase(final String name) {
		if (!canSetGivenNameToDatabase(name)) {
			throw new InvalidArgumentException(LowerCaseCatalogue.NAME, name, "cannot be set to database");
		}
	}
	
	//method
	@Override
	public void assertContainsGivenTable(final IDatabase<?> database, final ITable<?> table) {
		if (!containsGivenTable(database, table)) {
			throw new ArgumentDoesNotContainElementException(database, table);
		}
	}
	
	//method
	@Override
	public void assertContainsTableReferencedByGivenColumn(
		final IDatabase<?> database,
		final IColumn<?> column
	) {
		if (!containsTableReferencedByGivenColumn(database, column)) {
			throw
			new InvalidArgumentException(
				database,
				"does not contain a table that is referenced by the column " + column.getNameInQuotes()
			);
		}
	}
	
	//method
	@Override
	public void assertContainsTableWithColumnBackReferencedByGivenColumn(
		final IDatabase<?> database,
		final IColumn<?> column
	) {
		if (!containsTableWithColumnBackReferencedByGivenColumn(database, column)) {
			throw
			new InvalidArgumentException(
				this,
				"does not contain a table with a column that references back the column " + column.getName()
			);
		}
		
	}
	
	//method
	@Override
	public void assertContainsTableWithGivenColumn(final IDatabase<?> database, final IColumn<?> column) {
		if (!containsTableWithGivenColumn(database, column)) {
			throw new ArgumentDoesNotContainElementException(this, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainTableWithGivenName(final IDatabase<?> database, final String name) {
		if (containsTableWithGivenName(database, name)) {
			throw new InvalidArgumentException(this, "contains a table with the name '" + name + "'");
		}
	}
	
	//method
	@Override
	public boolean canAddGivenTable(final IDatabase<?> database, final ITable<?> table) {
		return
		canAddTable(database)
		&& table != null
		&& table.isOpen()
		&& !containsTableWithGivenName(database, table.getName())
		&& canAddGivenTableBecauseOfColumns(database, table);
	}
	
	//method
	@Override
	public boolean canAddTable(final IDatabase<?> database) {
		return
		database != null
		&& database.isOpen();
	}
	
	//method
	@Override
	public boolean canSetGivenNameToDatabase(final String name) {
		return !name.isBlank();
	}
	
	//method
	@Override
	public boolean containsGivenTable(final IDatabase<?> database, ITable<?> table) {
		return database.getRefTables().contains(table);
	}
	
	//method
	@Override
	public boolean containsTableReferencedByGivenColumn(
		final IDatabase<?> database,
		final IColumn<?> column
	) {
		
		//This check is theoretically not necessary, but provides a better performance for some cases.
		if (!columnHelper.isAReferenceColumn(column)) {
			return false;
		}
		
		return database.getRefTables().containsAny(t -> columnHelper.referencesGivenTable(column, t));
	}
	
	//method
	@Override
	public boolean containsTableWithColumnBackReferencedByGivenColumn(
		final IDatabase<?> database,
		final IColumn<?> column
	) {
		
		//This check is theoretically not necessary, but provides a better performance for some cases.
		if (!columnHelper.isABackReferenceColumn(column)) {
			return false;
		}
		
		return
		database.getRefTables().containsAny(t -> tableHelper.containsColumnBackReferencedByGivenColumn(t, column));
	}
	
	//method
	@Override
	public boolean containsTableWithGivenColumn(final IDatabase<?> database, final IColumn<?> column) {
		return database.getRefTables().containsAny(t -> tableHelper.containsGivenColumn(t, column));
	}
	
	//method
	@Override
	public boolean containsTableWithGivenName(final IDatabase<?> database, final String name) {
		return database.getRefTables().containsAny(t -> t.hasName(name));
	}
	
	//method
	@Override
	public void deleteTableWithGivenName(final IDatabase<?> database, final String name) {
		getRefTableWithGivenName(database, name).delete();
	}
	
	//method
	@Override
	public <IMPL> IContainer<IColumn<IMPL>> getRefAllBackReferenceColumns(final IDatabase<IMPL> database) {
		return database.getRefTables().toFromMany(tableHelper::getRefBackReferenceColumns);
	}
	
	//method
	@Override
	public <IMPL> ITable<IMPL> getRefTableWithGivenName(final IDatabase<IMPL> database, final String name) {
		return database.getRefTables().getRefFirst(t -> t.hasName(name));
	}
	
	//method
	@Override
	public int getTableCount(final IDatabase<?> database) {
		return database.getRefTables().getElementCount();
	}
	
	//method
	private boolean canAddGivenTableBecauseOfColumns(final IDatabase<?> database, final ITable<?> table) {
		return table.getRefColumns().containsOnly(c -> canAddGivenTableBecauseOfGivenColumn(database, table, c));
	}
	
	//method
	private boolean canAddGivenTableBecauseOfGivenColumn(
		final IDatabase<?> database,
		final ITable<?> table,
		final IColumn<?> column
	) {
		switch (columnHelper.getBasePropertyType(column)) {
			case BASE_VALUE:
				return true;
			case BASE_REFERENCE:
				return canAddGivenTableBecauseOfGivenReferenceColumn(database, table, column);
			case BASE_BACK_REFERENCE:
				return true;
			default:
				return true;
		}
	}
	
	//method
	private boolean canAddGivenTableBecauseOfGivenReferenceColumn(
		final IDatabase<?> database,
		final ITable<?> table,
		final IColumn<?> referenceColumn
	) {
		return
		containsTableReferencedByGivenColumn(database, referenceColumn)
		|| columnHelper.referencesGivenTable(referenceColumn, table);
	}
}

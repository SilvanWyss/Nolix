//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
public final class DatabaseHelper extends DatabaseObjectHelper implements IDatabaseHelper{
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//static attribute
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	@Override
	public boolean allBackReferencesAreValid(final IDatabase database) {
		return getOriAllBackReferenceColumns(database).containsOnly(columnHelper::isAValidBackReferenceColumn);
	}
	
	//method
	@Override
	public void assertAllBackReferencesAreValid(final IDatabase database) {
		if (!allBackReferencesAreValid(database)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(database, "contains invalid back references");
		}
	}
	
	//method
	@Override
	public void assertCanAddGivenTable(final IDatabase database, final ITable table) {
		if (!canAddGivenTable(database, table)) {
			
			if (table == null) {
				throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.TABLE);
			}
			
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				database,
				"cannot add the given table '" + table.getName() + "'"
			);
		}
	}
	
	//method
	@Override
	public void assertCanSetGivenNameToDatabase(final String name) {
		if (!canSetGivenNameToDatabase(name)) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				LowerCaseCatalogue.NAME,
				name,
				"cannot be set to database"
			);
		}
	}
	
	//method
	@Override
	public void assertContainsGivenTable(final IDatabase database, final ITable table) {
		if (!containsGivenTable(database, table)) {
			throw ArgumentDoesNotContainElementException.forArgumentAndElement(database, table);
		}
	}
	
	//method
	@Override
	public void assertContainsTableReferencedByGivenColumn(
		final IDatabase database,
		final IColumn column
	) {
		if (!containsTableReferencedByGivenColumn(database, column)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				database,
				"does not contain a table that is referenced by the column " + column.getNameInQuotes()
			);
		}
	}
	
	//method
	@Override
	public void assertContainsTableWithColumnBackReferencedByGivenColumn(
		final IDatabase database,
		final IColumn column
	) {
		if (!containsTableWithColumnBackReferencedByGivenColumn(database, column)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"does not contain a table with a column that references back the column " + column.getName()
			);
		}
		
	}
	
	//method
	@Override
	public void assertContainsTableWithGivenColumn(final IDatabase database, final IColumn column) {
		if (!containsTableWithGivenColumn(database, column)) {
			throw ArgumentDoesNotContainElementException.forArgumentAndElement(this, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainTableWithGivenName(final IDatabase database, final String name) {
		if (containsTableWithGivenName(database, name)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "contains a table with the name '" + name + "'");
		}
	}
	
	//method
	@Override
	public boolean canAddGivenTable(final IDatabase database, final ITable table) {
		return
		canAddTable(database)
		&& tableHelper.canBeAddedToDatabase(table)
		&& !containsTableWithGivenName(database, table.getName())
		&& canAddGivenTableBecauseOfColumns(database, table);
	}
	
	//method
	@Override
	public boolean canAddTable(final IDatabase database) {
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
	public boolean containsGivenTable(final IDatabase database, ITable table) {
		return database.getOriTables().contains(table);
	}
	
	//method
	@Override
	public boolean containsTableReferencedByGivenColumn(
		final IDatabase database,
		final IColumn column
	) {
		
		//This check is theoretically not necessary, but provides a better performance for some cases.
		if (!columnHelper.isAReferenceColumn(column)) {
			return false;
		}
		
		return database.getOriTables().containsAny(t -> columnHelper.referencesGivenTable(column, t));
	}
	
	//method
	@Override
	public boolean containsTableWithColumnBackReferencedByGivenColumn(
		final IDatabase database,
		final IColumn column
	) {
		
		//This check is theoretically not necessary, but provides a better performance for some cases.
		if (!columnHelper.isABackReferenceColumn(column)) {
			return false;
		}
		
		return
		database.getOriTables().containsAny(t -> tableHelper.containsColumnBackReferencedByGivenColumn(t, column));
	}
	
	//method
	@Override
	public boolean containsTableWithGivenColumn(final IDatabase database, final IColumn column) {
		return database.getOriTables().containsAny(t -> tableHelper.containsGivenColumn(t, column));
	}
	
	//method
	@Override
	public boolean containsTableWithGivenName(final IDatabase database, final String name) {
		return database.getOriTables().containsAny(t -> t.hasName(name));
	}
	
	//method
	@Override
	public void deleteTableWithGivenName(final IDatabase database, final String name) {
		getOriTableWithGivenName(database, name).delete();
	}
	
	//method
	@Override
	public  IContainer<IColumn> getOriAllBackReferenceColumns(final IDatabase database) {
		return database.getOriTables().toFromGroups(tableHelper::getOriBackReferenceColumns);
	}
	
	//method
	@Override
	public  ITable getOriTableWithGivenName(final IDatabase database, final String name) {
		return database.getOriTables().getOriFirst(t -> t.hasName(name));
	}
	
	//method
	@Override
	public int getTableCount(final IDatabase database) {
		return database.getOriTables().getElementCount();
	}
	
	//method
	private boolean canAddGivenTableBecauseOfColumns(final IDatabase database, final ITable table) {
		return table.getOriColumns().containsOnly(c -> canAddGivenTableBecauseOfGivenColumn(database, table, c));
	}
	
	//method
	private boolean canAddGivenTableBecauseOfGivenColumn(
		final IDatabase database,
		final ITable table,
		final IColumn column
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
		final IDatabase database,
		final ITable table,
		final IColumn referenceColumn
	) {
		return
		containsTableReferencedByGivenColumn(database, referenceColumn)
		|| columnHelper.referencesGivenTable(referenceColumn, table);
	}
}

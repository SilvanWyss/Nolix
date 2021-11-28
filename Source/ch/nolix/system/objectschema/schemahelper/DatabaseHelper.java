//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IDatabaseHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
public final class DatabaseHelper extends DatabaseObjectHelper implements IDatabaseHelper{
	
	//static attributes
	private static final ITableHelper tableHelper = new TableHelper();
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	@Override
	public void assertContainsGivenTable(final IDatabase<?, ?, ?, ?> database, final ITable<?, ?, ?> table) {
		if (!containsGivenTable(database, table)) {
			throw new ArgumentDoesNotContainElementException(database, table);
		}
	}
	
	//method
	@Override
	public void assertContainsTableReferencedByGivenColumn(
		final IDatabase<?, ?, ?, ?> database,
		final IColumn<?, ?> column
	) {
		if (!containsTableReferencedByGivenColumn(database, column)) {
			throw
			new InvalidArgumentException(
				database,
				"does not contain a table that is referenced by the column " + column.getHeaderInQuotes()
			);
		}
	}
	
	//method
	@Override
	public void assertContainsTableWithColumnBackReferencedByGivenColumn(
		final IDatabase<?, ?, ?, ?> database,
		final IColumn<?, ?> column
	) {
		if (!containsTableWithColumnBackReferencedByGivenColumn(database, column)) {
			throw
			new InvalidArgumentException(
				this,
				"does not contain a table with a column that references back the column " + column.getHeader()
			);
		}
		
	}
	
	//method
	@Override
	public void assertContainsTableWithGivenColumn(final IDatabase<?, ?, ?, ?> database, final IColumn<?, ?> column) {
		if (!containsTableWithGivenColumn(database, column)) {
			throw new ArgumentDoesNotContainElementException(this, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainTableWithGivenName(final IDatabase<?, ?, ?, ?> database, final String name) {
		if (containsTableWithGivenName(database, name)) {
			throw new InvalidArgumentException(this, "contains a table with the name '" + name + "'");
		}
	}
	
	//method
	@Override
	public boolean containsGivenTable(final IDatabase<?, ?, ?, ?> database, ITable<?, ?, ?> table) {
		return database.getRefTables().contains(table);
	}
	
	//method
	@Override
	public boolean containsTableReferencedByGivenColumn(
		final IDatabase<?, ?, ?, ?> database,
		final IColumn<?, ?> column
	) {
		
		//For a better performance, this check, that is theoretically not necessary, excludes many cases.
		if (!columnHelper.isAReferenceColumn(column)) {
			return false;
		}
		
		return database.getRefTables().containsAny(t -> columnHelper.referencesGivenTable(column, t));
	}
	
	//method
	@Override
	public boolean containsTableWithColumnBackReferencedByGivenColumn(
		final IDatabase<?, ?, ?, ?> database,
		final IColumn<?, ?> column
	) {
		
		//For a better performance, this check, that is theoretically not necessary, excludes many cases.
		if (!columnHelper.isABackReferenceColumn(column)) {
			return false;
		}
		
		return
		database.getRefTables().containsAny(t -> tableHelper.containsColumnBackReferencedByGivenColumn(t, column));
	}
	
	//method
	@Override
	public boolean containsTableWithGivenColumn(final IDatabase<?, ?, ?, ?> database, final IColumn<?, ?> column) {
		return database.getRefTables().containsAny(t -> tableHelper.containsGivenColumn(t, column));
	}
	
	//method
	@Override
	public boolean containsTableWithGivenName(final IDatabase<?, ?, ?, ?> database, final String name) {
		return database.getRefTables().containsAny(t -> t.hasName(name));
	}
	
	//method
	@Override
	public void deleteTableWithGivenName(final IDatabase<?, ?, ?, ?> database, final String name) {
		getRefTableWithGivenName(database, name).delete();
	}
	
	//method
	@Override
	public ITable<?, ?, ?> getRefTableWithGivenName(final IDatabase<?, ?, ?, ?> database, final String name) {
		return database.getRefTables().getRefFirst(t -> t.hasName(name));
	}
	
	//method
	@Override
	public int getTableCount(final IDatabase<?, ?, ?, ?> database) {
		return database.getRefTables().getElementCount();
	}
}

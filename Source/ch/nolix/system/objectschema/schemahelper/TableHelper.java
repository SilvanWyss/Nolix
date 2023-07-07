//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
public final class TableHelper extends DatabaseObjectHelper implements ITableHelper {
	
	//constant
	private static final IColumnHelper COLUMN_HELPER = new ColumnHelper();
	
	//method
	@Override
	public void assertContainsGivenColumn(final ITable table, final IColumn column) {
		if (!containsGivenColumn(table, column)) {
			throw ArgumentDoesNotContainElementException.forArgumentAndElement(table, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotBelongToDatabase(final ITable table) {
		if (table.belongsToDatabase()) {
			throw ArgumentBelongsToParentException.forArgumentAndParent(table, table.getParentDatabase());
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainGivenColumn(final ITable table, final IColumn column) {
		if (containsGivenColumn(table, column)) {
			throw ArgumentContainsElementException.forArgumentAndElement(table, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainColumnWithGivenName(final ITable table, final String name) {
		if (containsColumnWithGivenName(table, name)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				table,
				"contains already a column with the given name '" + name + "'"
			);
		}
	}
	
	//method
	@Override
	public void assertIsNotReferenced(final ITable table) {
		if (isReferenced(table)) {
			throw ReferencedArgumentException.forArgument(table);
		}
	}
	
	//method
	@Override
	public boolean canBeAddedToDatabase(final ITable table) {
		return
		table != null
		&& table.isOpen()
		&& !table.belongsToDatabase();
	}
	
	//method
	@Override
	public boolean containsGivenColumn(final ITable table, final IColumn column) {
		return table.getOriColumns().contains(column);
	}
	
	//method
	@Override
	public boolean containsColumnBackReferencedByGivenColumn(final ITable table, final IColumn column) {
		
		//This check is theoretically not necessary, but provides a better performance for some cases.
		if (!COLUMN_HELPER.isABackReferenceColumn(column)) {
			return false;
		}
		
		return table.getOriColumns().containsAny(c -> COLUMN_HELPER.referencesBackGivenColumn(c, column));
	}
	
	//method
	@Override
	public boolean containsColumnThatReferencesBackGivenColumn(final ITable table, final IColumn column) {
		
		//This check is theoretically not necessary, but provides a better performance for some cases.
		if (!COLUMN_HELPER.isAReferenceColumn(column)) {
			return false;
		}
		
		return table.getOriColumns().containsAny(c -> COLUMN_HELPER.referencesBackGivenColumn(c, column));
	}
	
	//method
	@Override
	public boolean containsColumnThatReferencesGivenTable(
		final ITable table,
		final ITable probableReferencedTable
	) {
		return table.getOriColumns().containsAny(c -> COLUMN_HELPER.referencesGivenTable(c, table));
	}
	
	//method
	@Override
	public boolean containsColumnWithGivenName(final ITable table, final String name) {
		return table.getOriColumns().containsAny(c -> c.hasName(name));
	}
	
	//method
	@Override
	public int getColumnCount(final ITable table) {
		return table.getOriColumns().getElementCount();
	}
	
	//method
	@Override
	public  IContainer<IColumn> getOriBackReferenceColumns(final ITable table) {
		return table.getOriColumns().getOriSelected(COLUMN_HELPER::isABackReferenceColumn);
	}
	
	//method
	@Override
	public  IContainer<IColumn> getOriBackReferencingColumns(final ITable table) {
		
		if (!table.belongsToDatabase()) {
			return getOriBackReferencingColumnsWhenDoesNotBelongToDatabase(table);
		}
		
		return getOriBackReferencingColumnsWhenBelongsToDatabase(table);
	}
	
	//method
	@Override
	public  IContainer<IColumn> getOriReferencingColumns(final ITable table) {
		
		if (!table.belongsToDatabase()) {
			return getOriReferencingColumnsWhenDoesNotBelongToDatabase(table);
		}
		
		return getOriReferencingColumnsWhenBelongsToDatabase(table);
	}
	
	//method
	@Override
	public boolean isReferenced(final ITable table) {
		return 
		table.belongsToDatabase()
		&& table.getParentDatabase().getOriTables().containsAny(t -> containsColumnThatReferencesGivenTable(t, table));
	}
	
	//method
	private  IContainer<IColumn> getOriBackReferencingColumnsWhenBelongsToDatabase(
		final ITable table
	) {
		
		final var columns = table.getParentDatabase().getOriTables().toFromGroups(ITable::getOriColumns);
		
		return
		table
		.getOriColumns()
		.getOriSelected(c -> columns.containsAny(c2 -> COLUMN_HELPER.referencesBackGivenColumn(c, c2)));
	}
	
	//method
	private  IContainer<IColumn> getOriBackReferencingColumnsWhenDoesNotBelongToDatabase(
		final ITable table
	) {
		
		final var columns = table.getOriColumns();
		
		return columns.getOriSelected(c -> columns.containsAny(c2 -> COLUMN_HELPER.referencesBackGivenColumn(c, c2)));
	}
	
	//method
	private  IContainer<IColumn> getOriReferencingColumnsWhenBelongsToDatabase(final ITable table) {
		return
		table
		.getParentDatabase()
		.getOriTables()
		.toFromGroups(ITable::getOriColumns)
		.getOriSelected(c -> COLUMN_HELPER.referencesGivenTable(c, table));
	}
	
	//method
	private  IContainer<IColumn> getOriReferencingColumnsWhenDoesNotBelongToDatabase(
		final ITable table
	) {
		return table.getOriColumns().getOriSelected(c -> COLUMN_HELPER.referencesGivenTable(c, table));
	}
}

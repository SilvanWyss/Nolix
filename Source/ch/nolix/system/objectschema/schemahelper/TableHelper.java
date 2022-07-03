//package declaration
package ch.nolix.system.objectschema.schemahelper;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
public final class TableHelper extends DatabaseObjectHelper implements ITableHelper {
	
	//static attribute
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	@Override
	public void assertContainsGivenColumn(final ITable<?> table, final IColumn<?> column) {
		if (!containsGivenColumn(table, column)) {
			throw ArgumentDoesNotContainElementException.forArgumentAndElement(table, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotBelongToDatabase(final ITable<?> table) {
		if (table.belongsToDatabase()) {
			throw ArgumentBelongsToParentException.forArgumentAndParent(table, table.getParentDatabase());
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainGivenColumn(final ITable<?> table, final IColumn<?> column) {
		if (containsGivenColumn(table, column)) {
			throw ArgumentContainsElementException.forArgumentAndElement(table, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainColumnWithGivenName(final ITable<?> table, final String name) {
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
	public void assertIsNotReferenced(final ITable<?> table) {
		if (isReferenced(table)) {
			throw ReferencedArgumentException.forArgument(table);
		}
	}
	
	//method
	@Override
	public boolean containsGivenColumn(final ITable<?> table, final IColumn<?> column) {
		return table.getRefColumns().contains(column);
	}
	
	//method
	@Override
	public boolean containsColumnBackReferencedByGivenColumn(final ITable<?> table, final IColumn<?> column) {
		
		//This check is theoretically not necessary, but provides a better performance for some cases.
		if (!columnHelper.isABackReferenceColumn(column)) {
			return false;
		}
		
		return table.getRefColumns().containsAny(c -> columnHelper.referencesBackGivenColumn(c, column));
	}
	
	//method
	@Override
	public boolean containsColumnThatReferencesBackGivenColumn(final ITable<?> table, final IColumn<?> column) {
		
		//This check is theoretically not necessary, but provides a better performance for some cases.
		if (!columnHelper.isAReferenceColumn(column)) {
			return false;
		}
		
		return table.getRefColumns().containsAny(c -> columnHelper.referencesBackGivenColumn(c, column));
	}
	
	//method
	@Override
	public boolean containsColumnThatReferencesGivenTable(
		final ITable<?> table,
		final ITable<?> probableReferencedTable
	) {
		return table.getRefColumns().containsAny(c -> columnHelper.referencesGivenTable(c, table));
	}
	
	//method
	@Override
	public boolean containsColumnWithGivenName(final ITable<?> table, final String name) {
		return table.getRefColumns().containsAny(c -> c.hasName(name));
	}
	
	//method
	@Override
	public int getColumnCount(final ITable<?> table) {
		return table.getRefColumns().getElementCount();
	}
	
	//method
	@Override
	public <IMPL> IContainer<IColumn<IMPL>> getRefBackReferenceColumns(final ITable<IMPL> table) {
		return table.getRefColumns().getRefSelected(columnHelper::isABackReferenceColumn);
	}
	
	//method
	@Override
	public <IMPL> IContainer<IColumn<IMPL>> getRefBackReferencingColumns(final ITable<IMPL> table) {
		
		if (!table.belongsToDatabase()) {
			return getRefBackReferencingColumnsWhenDoesNotBelongToDatabase(table);
		}
		
		return getRefBackReferencingColumnsWhenBelongsToDatabase(table);
	}
	
	//method
	@Override
	public <IMPL> IContainer<IColumn<IMPL>> getRefReferencingColumns(final ITable<IMPL> table) {
		
		if (!table.belongsToDatabase()) {
			return getRefReferencingColumnsWhenDoesNotBelongToDatabase(table);
		}
		
		return getRefReferencingColumnsWhenBelongsToDatabase(table);
	}
	
	//method
	@Override
	public boolean isReferenced(final ITable<?> table) {
		return 
		table.belongsToDatabase()
		&& table.getParentDatabase().getRefTables().containsAny(t -> containsColumnThatReferencesGivenTable(t, table));
	}
	
	//method
	private <IMPL> IContainer<IColumn<IMPL>> getRefBackReferencingColumnsWhenBelongsToDatabase(
		final ITable<IMPL> table
	) {
		
		final var columns = table.getParentDatabase().getRefTables().toFromMany(ITable::getRefColumns);
		
		return
		table
		.getRefColumns()
		.getRefSelected(c -> columns.containsAny(c2 -> columnHelper.referencesBackGivenColumn(c, c2)));
	}
	
	//method
	private <IMPL> IContainer<IColumn<IMPL>> getRefBackReferencingColumnsWhenDoesNotBelongToDatabase(
		final ITable<IMPL> table
	) {
		
		final var columns = table.getRefColumns();
		
		return columns.getRefSelected(c -> columns.containsAny(c2 -> columnHelper.referencesBackGivenColumn(c, c2)));
	}
	
	//method
	private <IMPL> IContainer<IColumn<IMPL>> getRefReferencingColumnsWhenBelongsToDatabase(final ITable<IMPL> table) {
		return
		table
		.getParentDatabase()
		.getRefTables()
		.toFromMany(ITable::getRefColumns)
		.getRefSelected(c -> columnHelper.referencesGivenTable(c, table));
	}
	
	//method
	private <IMPL> IContainer<IColumn<IMPL>> getRefReferencingColumnsWhenDoesNotBelongToDatabase(
		final ITable<IMPL> table
	) {
		return table.getRefColumns().getRefSelected(c -> columnHelper.referencesGivenTable(c, table));
	}
}

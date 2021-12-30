//package declaration
package ch.nolix.system.objectschema.schemahelper;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.ITableHelper;

//class
public final class TableHelper extends DatabaseObjectHelper implements ITableHelper {
	
	//static attribute
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//method
	@Override
	public void assertContainsGivenColumn(final ITable<?> table, final IColumn<?> column) {
		if (!containsGivenColumn(table, column)) {
			throw new ArgumentDoesNotContainElementException(table, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotBelongToDatabase(final ITable<?> table) {
		if (table.belongsToDatabase()) {
			throw new ArgumentBelongsToParentException(table, table.getParentDatabase());
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainGivenColumn(final ITable<?> table, final IColumn<?> column) {
		if (containsGivenColumn(table, column)) {
			throw new ArgumentContainsElementException(table, column);
		}
	}
	
	//method
	@Override
	public void assertDoesNotContainColumnWithGivenHeader(final ITable<?> table, final String header) {
		if (containsColumnWithGivenHeader(table, header)) {
			throw new InvalidArgumentException(table, "contains already a column with the header '" + header + "'");
		}
	}
	
	//method
	@Override
	public void assertIsNotReferenced(final ITable<?> table) {
		if (isReferenced(table)) {
			throw new ReferencedArgumentException(table);
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
	public boolean containsColumnWithGivenHeader(final ITable<?> table, final String header) {
		return table.getRefColumns().containsAny(c -> c.hasHeader(header));
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
	public <IMPL> LinkedList<IColumn<IMPL>> getRefBackReferencingColumns(final ITable<IMPL> table) {
		
		if (!table.belongsToDatabase()) {
			return getRefBackReferencingColumnsWhenDoesNotBelongToDatabase(table);
		}
		
		return getRefBackReferencingColumnsWhenBelongsToDatabase(table);
	}
	
	//method
	@Override
	public <IMPL> IColumn<IMPL> getRefColumnWithGivenHeader(final ITable<IMPL> table, final String header) {
		return table.getRefColumns().getRefFirst(c -> c.hasHeader(header));
	}
	
	//method
	@Override
	public <IMPL> LinkedList<IColumn<IMPL>> getRefReferencingColumns(final ITable<IMPL> table) {
		
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
	private <IMPL> LinkedList<IColumn<IMPL>> getRefBackReferencingColumnsWhenBelongsToDatabase(
		final ITable<IMPL> table
	) {
		
		final var columns = table.getParentDatabase().getRefTables().toFromMany(ITable::getRefColumns);
		
		return
		table
		.getRefColumns()
		.getRefSelected(c -> columns.containsAny(c2 -> columnHelper.referencesBackGivenColumn(c, c2)));
	}
	
	//method
	private <IMPL> LinkedList<IColumn<IMPL>> getRefBackReferencingColumnsWhenDoesNotBelongToDatabase(
		final ITable<IMPL> table
	) {
		
		final var columns = table.getRefColumns();
		
		return columns.getRefSelected(c -> columns.containsAny(c2 -> columnHelper.referencesBackGivenColumn(c, c2)));
	}
	
	//method
	private <IMPL> LinkedList<IColumn<IMPL>> getRefReferencingColumnsWhenBelongsToDatabase(final ITable<IMPL> table) {
		return
		table
		.getParentDatabase()
		.getRefTables()
		.toFromMany(t -> t.getRefColumns())
		.getRefSelected(c -> columnHelper.referencesGivenTable(c, table));
	}
	
	//method
	private <IMPL> LinkedList<IColumn<IMPL>> getRefReferencingColumnsWhenDoesNotBelongToDatabase(
		final ITable<IMPL> table
	) {
		return table.getRefColumns().getRefSelected(c -> columnHelper.referencesGivenTable(c, table));
	}
}

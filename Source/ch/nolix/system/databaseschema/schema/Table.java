//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.databaseschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.IdType;
import ch.nolix.system.databaseschema.schemadto.ColumnDTO;
import ch.nolix.system.databaseschema.schemadto.TableDTO;
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedTable;
import ch.nolix.techapi.intermediateschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public final class Table extends DatabaseObject implements IExtendedTable<Table, Column, ParametrizedPropertyType<?>> {
	
	//static attributes
	private static final TableMutationPreValidator mutationPreValidator = new TableMutationPreValidator();
	private static final TableMutationExecutor mutationExecutor = new TableMutationExecutor();
	
	//static method
	public static Table fromFlatDTO(final IFlatTableDTO flatTableDTO) {
		return new Table(flatTableDTO.getName());
	}
	
	//attributes
	private String name;
	private boolean loadedColumnsFromDatabase;
	
	//optional attribute
	private Database parentDatabase;
	
	//multi-attribute
	private LinkedList<Column> columns = new LinkedList<>();
	
	//constructor
	public Table(final String name) {
		
		setName(name);
		
		addIdColumn();
	}
	
	//method
	@Override
	public Table addColumn(final Column column) {
		
		mutationPreValidator.assertCanAddColumnToTable(this, column);
		mutationExecutor.addColumnToTable(this, column);
		
		return this;
	}
	
	//method
	public boolean belongsToDatabase() {
		return (parentDatabase != null);
	}
	
	//method
	@Override
	public Table createColumnWithHeaderAndParametrizedPropertyType( 
		final String header,
		final ParametrizedPropertyType<?> parametrizedPropertyType
	) {
		return addColumn(new Column(header, parametrizedPropertyType));
	}
	
	//method
	@Override
	public void delete() {
		mutationPreValidator.assertCanDeleteTable(this);
		mutationExecutor.deleteTable(this);
	}
	
	//method
	@Override
	public IFlatTableDTO getFlatDTO() {
		return new FlatTableDTO(getName());
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	public Database getParentDatabase() {
		
		assertBelongsToDatabase();
		
		return parentDatabase;
	}
	
	//method
	@Override
	public IContainer<Column> getRefColumns() {
		
		loadColumnsFromDatabaseIfNeeded();
		
		return columns;
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		return (belongsToDatabase() && getParentDatabase().isLinkedWithRealDatabase());
	}
	
	//method
	@Override
	public Table setName(final String name) {
		
		mutationPreValidator.assertCanSetNameToTable(this, name);
		mutationExecutor.setNameToTable(this, name);
		
		return this;
	}
	
	//method
	@Override
	public TableDTO toDTO() {
		return new TableDTO(getName(), createColumnDTOs());
	}
	
	//method
	@Override
	protected void noteCloseDatabaseObject() {
		
		//Does not call getRefColumns method to avoid that the columns need to be loaded from the database.
		columns.forEach(Column::close);
	}
	
	//method
	void addColumnAttribute(final Column column) {
		columns.addAtEnd(column);
	}
	
	//method
	IntermediateSchemaAdapter getRefRealSchemaAdapter() {
		return getParentDatabase().getRefRealSchemaAdapter();
	}
	
	//method
	void removeColumnAttribute(final Column column) {
		columns.removeFirst(column);
	}
	
	//method
	void setNameAttribute(final String name) {
		this.name = name;
	}
	
	//method
	void setParentDatabase(final Database parentDatabase) {
		
		Validator.assertThat(parentDatabase).thatIsNamed("parent database").isNotNull();
		assertDoesNotBelongToDatabase();
		
		this.parentDatabase = parentDatabase;
	}
	
	//method
	private void addIdColumn() {
		addColumn(createIdColumn());
	}
	
	//method
	private void assertBelongsToDatabase() {
		if (!belongsToDatabase()) {
			throw new ArgumentDoesNotBelongToParentException(this, Database.class);
		}
	}
	
	//method
	private LinkedList<ColumnDTO> createColumnDTOs() {
		return getRefColumns().to(Column::toDTO);
	}
	
	//method
	private Column createIdColumn() {
		return new Column(PascalCaseCatalogue.ID, new IdType());
	}
	
	//method
	private boolean hasLoadedColumnsFromDatabase() {
		return loadedColumnsFromDatabase;
	}
	
	//method
	private void loadColumnsFromDatabase() {
		
		loadedColumnsFromDatabase = true;
		
		final var tables = getParentDatabase().getRefTables();
		
		columns =
		getRefRealSchemaAdapter()
		.getRefIntermediateSchemaReader()
		.loadColumnsOfTable(this).to(c -> Column.fromDTO(c, tables));
		
		columns.forEach(Column::setLoaded);
		columns.forEach(c -> c.setParentTableAttribute(this));
	}
	
	//method
	private void loadColumnsFromDatabaseIfNeeded() {
		if (needsToLoadColumnsFromDatabase()) {
			loadColumnsFromDatabase();
		}
	}
	
	//method
	private boolean needsToLoadColumnsFromDatabase() {
		return (isLoaded() && !hasLoadedColumnsFromDatabase());
	}
}

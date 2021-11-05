//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDTO;
import ch.nolix.system.objectschema.schemadto.TableDTO;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedTable;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ISaveStampConfigurationDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.SaveStampStrategy;

//class
public final class Table extends DatabaseObject implements IExtendedTable<Table, Column, ParametrizedPropertyType<?>> {
	
	//static attributes
	private static final TableMutationValidator mutationValidator = new TableMutationValidator();
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
	}
	
	//method
	@Override
	public Table addColumn(final Column column) {
		
		mutationValidator.assertCanAddColumnToTable(this, column);
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
		mutationValidator.assertCanDeleteTable(this);
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
	public Table setName(final String name) {
		
		mutationValidator.assertCanSetNameToTable(this, name);
		mutationExecutor.setNameToTable(this, name);
		
		return this;
	}
	
	//method
	@Override
	public TableDTO toDTO() {
		return new TableDTO(getName(), createSaveStampConfigurationDTO(), createColumnDTOs());
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
	private void assertBelongsToDatabase() {
		if (!belongsToDatabase()) {
			throw new ArgumentDoesNotBelongToParentException(this, Database.class);
		}
	}
	
	//method
	private LinkedList<IColumnDTO> createColumnDTOs() {
		return getRefColumns().to(Column::toDTO);
	}
	
	//method
	private ISaveStampConfigurationDTO createSaveStampConfigurationDTO() {
		return new SaveStampConfigurationDTO(SaveStampStrategy.OWN_SAVE_STAMP);
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

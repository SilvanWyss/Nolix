//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.databaseschema.schemadto.ColumnDTO;
import ch.nolix.system.databaseschema.schemadto.TableDTO;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedTable;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.schemaaccessorapi.ITableAccessor;

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
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	private boolean loadedColumnsFromDatabase;
	
	//optional attribute
	private Database parentDatabase;
	private ITableAccessor accessor;
	
	//multi-attribute
	private LinkedList<Column> columns = new LinkedList<>();
	
	//constructor
	public Table(final String name) {
		setName(name);
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
	public DatabaseObjectState getState() {
		return state;
	}
	
	//method
	@Override
	public boolean isLinkedWithActualDatabase() {
		return (belongsToDatabase() && getParentDatabase().isLinkedWithActualDatabase());
	}
	
	//method
	@Override
	public void noteClose() {
		
		state = DatabaseObjectState.CLOSED;
		
		//Does not call getRefColumns method to avoid that the columns need to be loaded from the database.
		columns.forEach(Column::close);
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
	void assertDoesNotContainIdColumn() {
		if (containsIdColumn()) {
			throw new ArgumentHasAttributeException(this, "id column");
		}
	}
	
	//method
	void assertIsNotReferenced() {
		if (isReferenced()) {
			throw new InvalidArgumentException(this, "is referenced");
		}
	}
	
	//method
	ITableAccessor getRefAccessor() {
		
		gainAccessorIfNeeded();
		
		return accessor;
	}
	
	//method
	void setLoaded() {
		
		assertIsNew();
		
		state = DatabaseObjectState.LOADED;
	}
	
	//method
	private void assertBelongsToDatabase() {
		if (!belongsToDatabase()) {
			throw new ArgumentDoesNotBelongToParentException(this, Database.class);
		}
	}
	
	//method
	private boolean containsIdColumn() {
		return getRefColumns().containsAny(Column::isIdColumn);
	}
	
	//method
	private LinkedList<ColumnDTO> createColumnDTOs() {
		return getRefColumns().to(Column::toDTO);
	}
	
	//method
	private void gainAccessor() {
		accessor = getParentDatabase().getRefAccessor().getAccessorForTableWithName(getName());
	}
	
	//method
	private void gainAccessorIfNeeded() {
		if (!hasAccessor()) {
			gainAccessor();
		}
	}
	
	//method
	private boolean hasAccessor() {
		return (accessor != null);
	}

	//method
	private boolean hasLoadedColumnsFromDatabase() {
		return loadedColumnsFromDatabase;
	}
	
	//method
	private boolean isReferenced() {
		return 
		belongsToDatabase()
		&& getParentDatabase().getRefTables().containsAny(t -> t.containsColumnThatReferencesTable(this));
	}
	
	//method
	private void loadColumnsFromDatabase() {
						
		columns = getRefAccessor().loadColumnsOfCurrentTableFromDatabase().to(Column::fromDTO);
		columns.forEach(Column::setLoaded);
		
		loadedColumnsFromDatabase = true;
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

//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.data.GlobalIdCreator;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDTO;
import ch.nolix.system.objectschema.schemadto.TableDTO;
import ch.nolix.system.objectschema.schemahelper.TableHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.ITableHelper;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ISaveStampConfigurationDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.SaveStampStrategy;

//class
public final class Table extends SchemaObject implements ITable<SchemaImplementation> {
	
	//static attributes
	private static final TableMutationValidator mutationValidator = new TableMutationValidator();
	private static final TableMutationExecutor mutationExecutor = new TableMutationExecutor();
	
	//static attribute
	private static final ITableHelper tableHelper = new TableHelper();
	
	//static method
	public static Table fromFlatDTO(final IFlatTableDTO flatTableDTO) {
		return new Table(flatTableDTO.getId(), flatTableDTO.getName());
	}
	
	//attribute
	private final String id;
	
	//attribute
	private String name;
	
	//attribute
	private boolean loadedColumnsFromDatabase;
	
	//optional attribute
	private Database parentDatabase;
	
	//multi-attribute
	private LinkedList<IColumn<SchemaImplementation>> columns = new LinkedList<>();
	
	//constructor
	public Table(final String name) {
		this(
			GlobalIdCreator.createIdOf10HexadecimalCharacters(),
			name
		);
	}
	
	//constructor
	public Table(final String id, final String name) {
		
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id = id;
		setName(name);
	}
	
	//method
	@Override
	public Table addColumn(final IColumn<SchemaImplementation> column) {
		
		mutationValidator.assertCanAddColumnToTable(this, (Column)column);
		mutationExecutor.addColumnToTable(this, (Column)column);
		
		return this;
	}
	
	//method
	public boolean belongsToDatabase() {
		return (parentDatabase != null);
	}
	
	//method
	@Override
	public Table createColumnWithNameAndParametrizedPropertyType( 
		final String name,
		final IParametrizedPropertyType<SchemaImplementation> parametrizedPropertyType
	) {
		return addColumn(new Column(name, parametrizedPropertyType));
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
		return new FlatTableDTO(getId(), getName());
	}
	
	//method
	@Override
	public String getId() {
		return id;
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
	public IContainer<IColumn<SchemaImplementation>> getRefColumns() {
		
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
		
		mutationValidator.assertCanSetNameToTable(this, name);
		mutationExecutor.setNameToTable(this, name);
		
		return this;
	}
	
	//method
	@Override
	public TableDTO toDTO() {
		return new TableDTO(getId(), getName(), createSaveStampConfigurationDTO(), createColumnDTOs());
	}
	
	//method
	@Override
	protected void noteClose() {
		
		//Does not call getRefColumns method to avoid that the columns need to be loaded from the database.
		for (final var c : columns) {
			((Column)c).internalClose();
		}
	}
	
	//method
	void addColumnAttribute(final IColumn<SchemaImplementation> column) {
		columns.addAtEnd(column);
	}
	
	//method
	RawSchemaAdapter internalgetRefRawSchemaAdapter() {
		return getParentDatabase().internalGetRefRawSchemaAdapter();
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
		
		GlobalValidator.assertThat(parentDatabase).thatIsNamed("parent database").isNotNull();
		tableHelper.assertDoesNotBelongToDatabase(this);
		
		this.parentDatabase = parentDatabase;
	}
	
	//method
	private void assertBelongsToDatabase() {
		if (!belongsToDatabase()) {
			throw new ArgumentDoesNotBelongToParentException(this, Database.class);
		}
	}
	
	//method
	private IContainer<IColumnDTO> createColumnDTOs() {
		return getRefColumns().to(IColumn::toDTO);
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
		
		columns = LinkedList.fromIterable(internalgetRefRawSchemaAdapter().loadColumnsOfTable(this).to(c -> Column.fromDTO(c, tables)));
		
		for (final var c : columns) {
			final var column = (Column)c;
			column.internalSetLoaded();
			column.setParentTableAttribute(this);
		}
	}
	
	//method
	private void loadColumnsFromDatabaseIfNeeded() {
		if (needsToLoadColumnsFromDatabase()) {
			loadColumnsFromDatabase();
		}
	}
	
	//method
	private boolean needsToLoadColumnsFromDatabase() {
		return (tableHelper.isLoaded(this) && !hasLoadedColumnsFromDatabase());
	}
}

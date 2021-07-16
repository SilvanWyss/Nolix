//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.databaseschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.databaseschema.schemadto.ColumnDTO;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedColumn;
import ch.nolix.techapi.databaseschemaapi.schemaaccessorapi.IColumnAccessor;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//class
public final class Column extends DatabaseObject implements IExtendedColumn<Column, ParametrizedPropertyType<?>> {
	
	//constant
	private static final String INITIAL_HEADER = StringCatalogue.DEFAULT_STRING;
	
	//constant
	private static final ParametrizedPropertyType<?> INITIAL_PROPERTY_TYPE =
	new ParametrizedValueType<>(Integer.class);
	
	//static attribute
	private static final ParametrizedPropertyTypeFactory parametrizedPropertyTypeFactory =
	new ParametrizedPropertyTypeFactory();
	
	//static attributes
	private static final ColumnMutationPreValidator mutationPreValidator = new ColumnMutationPreValidator();
	private static final ColumnMutationExecutor mutationExecutor = new ColumnMutationExecutor();
	
	//static method
	public static Column fromDTO(final IColumnDTO columnDTO) {
		return 
		new Column(
			columnDTO.getHeader(),
			parametrizedPropertyTypeFactory.createParametrizedPropertyTypefromDTOUnchecked(
				columnDTO.getParametrizedPropertyType()
			)
		);
	}
	
	//attributes
	private String header = INITIAL_HEADER;
	private ParametrizedPropertyType<?> parametrizedPropertyType = INITIAL_PROPERTY_TYPE;
	private DatabaseObjectState state = DatabaseObjectState.NEW;
	
	//optional attributes
	private Table parentTable;
	private IColumnAccessor accessor;
	
	//constructor
	public Column(final String header, final ParametrizedPropertyType<?> parametrizedPropertyType) {
		setHeader(header);
		setParametrizedPropertyType(parametrizedPropertyType);
	}
	
	//method
	@Override
	public boolean belongsToTable() {
		return (parentTable != null);
	}
	
	//method
	@Override
	public String getHeader() {
		return header;
	}
	
	//method
	@Override
	public ParametrizedPropertyType<?> getParametrizedPropertyType() {
		return parametrizedPropertyType;
	}
	
	//method
	public Table getParentTable() {
		
		assertBelongsToTable();
		
		return parentTable;
	}
	
	//method
	@Override
	public DatabaseObjectState getState() {
		return state;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		
		if (isNew()) {
			return true;
		}
		
		return getRefAccessor().currentColumnIsEmptyOnDatabase();
	}
	
	//method
	@Override
	public boolean isLinkedWithActualDatabase() {
		return (belongsToTable() && getParentTable().isLinkedWithActualDatabase());
	}
	
	//method
	@Override
	public void noteClose() {
		state = DatabaseObjectState.CLOSED;
	}
	
	//method
	@Override
	public Column setHeader(final String header) {
		
		mutationPreValidator.assertCanSetHeaderToColumn(this, header);
		mutationExecutor.setHeaderToColumn(this, header);
		
		return this;
	}
	
	//method
	@Override
	public Column setParametrizedPropertyType(
		final ParametrizedPropertyType<?> parametrizedPropertyType
	) {
		
		mutationPreValidator.assertCanSetParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
		mutationExecutor.setParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
		
		return this;
	}

	//method
	@Override
	public ColumnDTO toDTO() {
		return new ColumnDTO(getHeader(), getParametrizedPropertyType().toDTO());
	}
	
	//method
	void assertIsNotBackReferenced() {
		if (isBackReferenced()) {
			throw new InvalidArgumentException(this, "is back referenced");
		}
	}
	
	//method
	boolean belongsToDatabase() {
		return (belongsToTable() && getParentTable().belongsToDatabase());
	}
	
	//method
	Database getParentDatabase() {
		return getParentTable().getParentDatabase();
	}
	
	//method
	IColumnAccessor getRefAccessor() {
		
		gainAccessorIfNeeded();
		
		return accessor;
	}
	
	//method
	IContainer<Column> getRefBackReferencingColumns() {
		
		if (!isAnyReferenceColumn()) {
			return new LinkedList<>();
		}
		
		return getRefBackReferencingColumnsWhenIsReferenceColumn();
	}
	
	//method
	boolean isBackReferenced() {
		
		if (!isAnyReferenceColumn()) {
			return false;
		}
		
		return isBackReferencedWhenIsAnyReferenceColumn();
	}
	
	//method
	void noteEdit() {
		switch (getState()) {
			case NEW:
				break;
			case LOADED:
				setEdited();
				break;
			case EDITED:
				break;
			case DELETED:
				throw new DeletedArgumentException(this);
			case CLOSED:
				throw new ClosedArgumentException(this);
		}
	}
	
	//method
	void setHeaderAttribute(final String header) {
		this.header = header;
	}
	
	//method
	void setLoaded() {
		
		assertIsNew();
		
		state = DatabaseObjectState.LOADED;
	}

	//method
	void setParametrizedPropertyTypeAttribute(final ParametrizedPropertyType<?> parametrizedPropertyType) {
		this.parametrizedPropertyType = parametrizedPropertyType;
	}

	//method
	void setParentTable(final Table parentTable) {
		
		mutationPreValidator.assertCanSetParentTableToColumn(this, parentTable);
		
		this.parentTable = parentTable;
	}
	
	//method
	private void gainAccessor() {
		accessor = getParentTable().getRefAccessor().getAccessorForColumnWithHeader(getHeader());
	}
	
	//method
	private void gainAccessorIfNeeded() {
		if (!hasAccessor()) {
			gainAccessor();
		}
	}
	
	//method
	private IContainer<Column> getRefBackReferencingColumnsWhenIsReferenceColumn() {
		if (belongsToDatabase()) {
			return
			getParentDatabase()
			.getRefTables()
			.toFromMany(t -> t.getRefColumns().getRefSelected(c -> c.referencesBack(this)));
		}
		
		if (belongsToTable()) {
			return getParentTable().getRefColumns().getRefSelected(c -> c.referencesBack(this));
		}
		
		return new LinkedList<>();
	}
	
	//method
	private boolean hasAccessor() {
		return (accessor != null);
	}
	
	//method
	private boolean isBackReferencedWhenIsAnyReferenceColumn() {
		
		if (belongsToDatabase()) {
			return
			getParentDatabase()
			.getRefTables()
			.containsAny(t -> t.getRefColumns().containsAny(c -> c.referencesBack(this)));
		}
		
		if (belongsToTable()) {
			return getParentTable().getRefColumns().containsAny(c -> c.referencesBack(this));
		}
		
		return false;
	}
	
	//method
	private void setEdited() {
		state = DatabaseObjectState.EDITED;
	}
}

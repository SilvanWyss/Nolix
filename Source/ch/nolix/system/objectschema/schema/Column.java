//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.schemadto.ColumnDTO;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedColumn;
import ch.nolix.techapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//class
public final class Column extends DatabaseObject implements IExtendedColumn<Column, ParametrizedPropertyType<?>> {
	
	//constant
	private static final String INITIAL_HEADER = StringCatalogue.DEFAULT_STRING;
	
	//constant
	private static final ParametrizedPropertyType<?> INITIAL_PROPERTY_TYPE =
	new ParametrizedValueType<>(Integer.class);
	
	//static attribute
	private static final ParametrizedPropertyTypeMapper parametrizedPropertyTypeMapper =
	new ParametrizedPropertyTypeMapper();
	
	//static attributes
	private static final ColumnMutationValidator mutationValidator = new ColumnMutationValidator();
	private static final ColumnMutationExecutor mutationExecutor = new ColumnMutationExecutor();
	
	//static attribute
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//static method
	public static Column fromDTO(final IColumnDTO columnDTO, final IContainer<Table> tables) {
		return 
		new Column(
			columnDTO.getHeader(),
			parametrizedPropertyTypeMapper.createParametrizedPropertyTypeFromDTO(
				columnDTO.getParametrizedPropertyType(),
				tables
			)
		);
	}
	
	//attributes
	private String header = INITIAL_HEADER;
	private ParametrizedPropertyType<?> parametrizedPropertyType = INITIAL_PROPERTY_TYPE;
	
	//optional attributes
	private Table parentTable;
	
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
	
	//metod
	@Override
	public void delete() {
		mutationValidator.assertCanDeleteColumn(this);
		mutationExecutor.deleteColumn(this);
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
	@Override
	public Database getParentDatabase() {
		return getParentTable().getParentDatabase();
	}
	
	//method
	@Override
	public Table getParentTable() {
		
		columnHelper.assertBelongsToTable(this);
		
		return parentTable;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		
		if (isNew()) {
			return true;
		}
		
		return getRefRawSchemaAdapter().getRefRawSchemaReader().columnIsEmpty(this);
	}
	
	//method
	@Override
	public Column setHeader(final String header) {
		
		mutationValidator.assertCanSetHeaderToColumn(this, header);
		mutationExecutor.setHeaderToColumn(this, header);
		
		return this;
	}
	
	//method
	@Override
	public Column setParametrizedPropertyType(
		final ParametrizedPropertyType<?> parametrizedPropertyType
	) {
		
		mutationValidator.assertCanSetParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
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
	IContainer<Column> getRefBackReferencingColumns() {
		
		if (!columnHelper.isAReferenceColumn(this)) {
			return new LinkedList<>();
		}
		
		return getRefBackReferencingColumnsWhenIsReferenceColumn();
	}
	
	//method
	RawSchemaAdapter getRefRawSchemaAdapter() {
		return ((Database)columnHelper.getParentDatabase(this)).getRefRealSchemaAdapter();
	}
	
	//method
	boolean isBackReferenced() {
		
		if (!columnHelper.isAReferenceColumn(this)) {
			return false;
		}
		
		return isBackReferencedWhenIsAnyReferenceColumn();
	}
		
	//method
	void setHeaderAttribute(final String header) {
		this.header = header;
	}
	
	//method
	void setParametrizedPropertyTypeAttribute(final ParametrizedPropertyType<?> parametrizedPropertyType) {
		this.parametrizedPropertyType = parametrizedPropertyType;
	}
	
	//method
	void setParametrizedPropertyTypeToDatabase() {
		getRefRawSchemaAdapter()
		.getRefRawSchemaWriter()
		.setColumnParametrizedPropertyType(this, parametrizedPropertyType);
	}
	
	//method
	void setParentTableAttribute(final Table parentTable) {
		this.parentTable = parentTable;
	}
	
	//method
	@Override
	protected void noteCloseDatabaseObject() {}
	
	//method
	private IContainer<Column> getRefBackReferencingColumnsWhenIsReferenceColumn() {
		
		if (columnHelper.belongsToDatabase(this)) {
			return
			columnHelper
			.getParentDatabase(this)
			.getRefTables()
			.toFromMany(t -> t.getRefColumns().getRefSelected(c -> columnHelper.referencesBackGivenColumn(c, this)));
		}
		
		if (belongsToTable()) {
			return getParentTable().getRefColumns().getRefSelected(c -> columnHelper.referencesBackGivenColumn(c, this));
		}
		
		return new LinkedList<>();
	}
	
	//method
	private boolean isBackReferencedWhenIsAnyReferenceColumn() {
		
		if (columnHelper.belongsToDatabase(this)) {
			return
			columnHelper
			.getParentDatabase(this)
			.getRefTables()
			.containsAny(t -> t.getRefColumns().containsAny(c -> columnHelper.referencesBackGivenColumn(c, this)));
		}
		
		if (belongsToTable()) {
			return getParentTable().getRefColumns().containsAny(c -> columnHelper.referencesBackGivenColumn(c, this));
		}
		
		return false;
	}
}

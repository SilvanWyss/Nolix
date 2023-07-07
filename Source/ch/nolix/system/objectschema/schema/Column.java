//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
public final class Column extends SchemaObject implements IColumn {
	
	//constant
	private static final String INITIAL_HEADER = StringCatalogue.DEFAULT_STRING;
	
	//constant
	private static final ParametrizedPropertyType INITIAL_PROPERTY_TYPE =
	new ParametrizedValueType<>(DataType.INTEGER_4BYTE);
	
	//constant
	private static final ParametrizedPropertyTypeMapper PARAMETRIZED_PROPERTY_TYPE_MAPPER =
	new ParametrizedPropertyTypeMapper();
	
	//constant
	private static final ColumnMutationValidator MUTATION_VALIDATOR = new ColumnMutationValidator();
	
	//constant
	private static final ColumnMutationExecutor MUTATION_EXECUTOR = new ColumnMutationExecutor();
	
	//constant
	private static final IColumnHelper COLUMN_HELPER = new ColumnHelper();
	
	//static method
	public static Column fromDto(final IColumnDto columnDto, final IContainer<ITable> tables) {
		return
		new Column(
			columnDto.getId(),
			columnDto.getName(),
			PARAMETRIZED_PROPERTY_TYPE_MAPPER.createParametrizedPropertyTypeFromDto(
				columnDto.getParametrizedPropertyType(),
				tables
			)
		);
	}
	
	//attribute
	private final String id;
	
	//attribute
	private String name = INITIAL_HEADER;
	
	//attribute
	private IParametrizedPropertyType parametrizedPropertyType = INITIAL_PROPERTY_TYPE;
	
	//optional attribute
	private Table parentTable;
	
	//constructor
	public Column(
		final String name,
		final IParametrizedPropertyType parametrizedPropertyType
	) {
		this(GlobalIdCreator.createIdOf10HexadecimalCharacters(), name, parametrizedPropertyType);
	}
	
	//constructor
	private Column(
		final String id,
		final String name,
		final IParametrizedPropertyType parametrizedPropertyType
	) {
		
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id = id;
		setName(name);
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
		MUTATION_VALIDATOR.assertCanDeleteColumn(this);
		MUTATION_EXECUTOR.deleteColumn(this);
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public String getId() {
		return id;
	}
	
	//method
	@Override
	public IParametrizedPropertyType getParametrizedPropertyType() {
		return parametrizedPropertyType;
	}
		
	//method
	@Override
	public Table getParentTable() {
		
		COLUMN_HELPER.assertBelongsToTable(this);
		
		return parentTable;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		
		if (COLUMN_HELPER.isNew(this)) {
			return true;
		}
		
		return internalGetRefRawSchemaAdapter().columnIsEmpty(this);
	}
	
	//method
	@Override
	public boolean isLinkedWithRealDatabase() {
		return (belongsToTable() && getParentTable().isLinkedWithRealDatabase());
	}
	
	//method
	@Override
	public Column setName(final String name) {
		
		MUTATION_VALIDATOR.assertCanSetNameToColumn(this, name);
		MUTATION_EXECUTOR.setHeaderToColumn(this, name);
		
		return this;
	}
	
	//method
	@Override
	public Column setParametrizedPropertyType(
		final IParametrizedPropertyType parametrizedPropertyType
	) {
		
		MUTATION_VALIDATOR.assertCanSetParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
		MUTATION_EXECUTOR.setParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
		
		return this;
	}

	//method
	@Override
	public ColumnDto toDto() {
		return new ColumnDto(getId(), getName(), getParametrizedPropertyType().toDto());
	}
	
	//method
	void assertIsEmpty() {
		if (containsAny()) {
			throw NonEmptyArgumentException.forArgument(this);
		}
	}
	
	//method
	void assertIsNotBackReferenced() {
		if (isBackReferenced()) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is back referenced");
		}
	}
	
	//method
	IContainer<IColumn> getOriBackReferencingColumns() {
		
		if (!COLUMN_HELPER.isAReferenceColumn(this)) {
			return new LinkedList<>();
		}
		
		return getOriBackReferencingColumnsWhenIsReferenceColumn();
	}
	
	//method
	RawSchemaAdapter internalGetRefRawSchemaAdapter() {
		return ((Database)COLUMN_HELPER.getParentDatabase(this)).internalGetRefRawSchemaAdapter();
	}
	
	//method
	boolean isBackReferenced() {
		
		if (!COLUMN_HELPER.isAReferenceColumn(this)) {
			return false;
		}
		
		return isBackReferencedWhenIsAnyReferenceColumn();
	}
		
	//method
	void setNameAttribute(final String header) {
		this.name = header;
	}
	
	//method
	void setParametrizedPropertyTypeAttribute(
		final IParametrizedPropertyType parametrizedPropertyType
	) {
		this.parametrizedPropertyType = parametrizedPropertyType;
	}
	
	//method
	void setParametrizedPropertyTypeToDatabase() {
		internalGetRefRawSchemaAdapter().setColumnParametrizedPropertyType(this, parametrizedPropertyType);
	}
	
	//method
	void setParentTableAttribute(final Table parentTable) {
		this.parentTable = parentTable;
	}
	
	//method
	@Override
	protected void noteClose() {
		//Does nothing.
	}
	
	//method
	private IContainer<IColumn> getOriBackReferencingColumnsWhenIsReferenceColumn() {
		
		if (COLUMN_HELPER.belongsToDatabase(this)) {
			return
			COLUMN_HELPER
			.getParentDatabase(this)
			.getOriTables()
			.toFromGroups(t -> t.getOriColumns().getOriSelected(c -> COLUMN_HELPER.referencesBackGivenColumn(c, this)));
		}
		
		if (belongsToTable()) {
			return getParentTable().getOriColumns().getOriSelected(c -> COLUMN_HELPER.referencesBackGivenColumn(c, this));
		}
		
		return new LinkedList<>();
	}
	
	//method
	private boolean isBackReferencedWhenIsAnyReferenceColumn() {
		
		if (COLUMN_HELPER.belongsToDatabase(this)) {
			return
			COLUMN_HELPER
			.getParentDatabase(this)
			.getOriTables()
			.containsAny(t -> t.getOriColumns().containsAny(c -> COLUMN_HELPER.referencesBackGivenColumn(c, this)));
		}
		
		if (belongsToTable()) {
			return getParentTable().getOriColumns().containsAny(c -> COLUMN_HELPER.referencesBackGivenColumn(c, this));
		}
		
		return false;
	}
}

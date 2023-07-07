//package declaration
package ch.nolix.system.objectschema.schema;

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
	
	//static attribute
	private static final ParametrizedPropertyTypeMapper parametrizedPropertyTypeMapper =
	new ParametrizedPropertyTypeMapper();
	
	//static attribute
	private static final ColumnMutationValidator mutationValidator = new ColumnMutationValidator();
	
	//static attribute
	private static final ColumnMutationExecutor mutationExecutor = new ColumnMutationExecutor();
	
	//static attribute
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//static method
	public static Column fromDto(final IColumnDto columnDto, final IContainer<ITable> tables) {
		return
		new Column(
			columnDto.getId(),
			columnDto.getName(),
			parametrizedPropertyTypeMapper.createParametrizedPropertyTypeFromDto(
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
		mutationValidator.assertCanDeleteColumn(this);
		mutationExecutor.deleteColumn(this);
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
		
		columnHelper.assertBelongsToTable(this);
		
		return parentTable;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		
		if (columnHelper.isNew(this)) {
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
		
		mutationValidator.assertCanSetNameToColumn(this, name);
		mutationExecutor.setHeaderToColumn(this, name);
		
		return this;
	}
	
	//method
	@Override
	public Column setParametrizedPropertyType(
		final IParametrizedPropertyType parametrizedPropertyType
	) {
		
		mutationValidator.assertCanSetParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
		mutationExecutor.setParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
		
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
		
		if (!columnHelper.isAReferenceColumn(this)) {
			return new LinkedList<>();
		}
		
		return getOriBackReferencingColumnsWhenIsReferenceColumn();
	}
	
	//method
	RawSchemaAdapter internalGetRefRawSchemaAdapter() {
		return ((Database)columnHelper.getParentDatabase(this)).internalGetRefRawSchemaAdapter();
	}
	
	//method
	boolean isBackReferenced() {
		
		if (!columnHelper.isAReferenceColumn(this)) {
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
		
		if (columnHelper.belongsToDatabase(this)) {
			return
			columnHelper
			.getParentDatabase(this)
			.getOriTables()
			.toFromGroups(t -> t.getOriColumns().getOriSelected(c -> columnHelper.referencesBackGivenColumn(c, this)));
		}
		
		if (belongsToTable()) {
			return getParentTable().getOriColumns().getOriSelected(c -> columnHelper.referencesBackGivenColumn(c, this));
		}
		
		return new LinkedList<>();
	}
	
	//method
	private boolean isBackReferencedWhenIsAnyReferenceColumn() {
		
		if (columnHelper.belongsToDatabase(this)) {
			return
			columnHelper
			.getParentDatabase(this)
			.getOriTables()
			.containsAny(t -> t.getOriColumns().containsAny(c -> columnHelper.referencesBackGivenColumn(c, this)));
		}
		
		if (belongsToTable()) {
			return getParentTable().getOriColumns().containsAny(c -> columnHelper.referencesBackGivenColumn(c, this));
		}
		
		return false;
	}
}

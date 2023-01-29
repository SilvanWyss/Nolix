//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.system.objectschema.schemadto.ColumnDTO;
import ch.nolix.system.objectschema.schemahelper.ColumnHelper;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemahelperapi.IColumnHelper;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

//class
public final class Column extends SchemaObject implements IColumn<SchemaImplementation> {
	
	//constant
	private static final String INITIAL_HEADER = StringCatalogue.DEFAULT_STRING;
	
	//constant
	private static final ParametrizedPropertyType INITIAL_PROPERTY_TYPE =
	new ParametrizedValueType<>(DataType.INTEGER_4BYTE);
	
	//static attribute
	private static final ParametrizedPropertyTypeMapper parametrizedPropertyTypeMapper =
	new ParametrizedPropertyTypeMapper();
	
	//static attributes
	private static final ColumnMutationValidator mutationValidator = new ColumnMutationValidator();
	private static final ColumnMutationExecutor mutationExecutor = new ColumnMutationExecutor();
	
	//static attribute
	private static final IColumnHelper columnHelper = new ColumnHelper();
	
	//static method
	public static Column fromDTO(final IColumnDTO columnDTO, final IContainer<ITable<SchemaImplementation>> tables) {
		return
		new Column(
			columnDTO.getId(),
			columnDTO.getName(),
			parametrizedPropertyTypeMapper.createParametrizedPropertyTypeFromDTO(
				columnDTO.getParametrizedPropertyType(),
				tables
			)
		);
	}
	
	//attribute
	private final String id;
	
	//attribute
	private String name = INITIAL_HEADER;
	
	//attribute
	private IParametrizedPropertyType<SchemaImplementation> parametrizedPropertyType = INITIAL_PROPERTY_TYPE;
	
	//optional attributes
	private Table parentTable;
	
	//constructor
	public Column(
		final String name,
		final IParametrizedPropertyType<SchemaImplementation> parametrizedPropertyType
	) {
		this(GlobalIdCreator.createIdOf10HexadecimalCharacters(), name, parametrizedPropertyType);
	}
	
	//constructor
	private Column(
		final String id,
		final String name,
		final IParametrizedPropertyType<SchemaImplementation> parametrizedPropertyType
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
	public IParametrizedPropertyType<SchemaImplementation> getParametrizedPropertyType() {
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
		final IParametrizedPropertyType<SchemaImplementation> parametrizedPropertyType
	) {
		
		mutationValidator.assertCanSetParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
		mutationExecutor.setParametrizedPropertyTypeToColumn(this, parametrizedPropertyType);
		
		return this;
	}

	//method
	@Override
	public ColumnDTO toDTO() {
		return new ColumnDTO(getId(), getName(), getParametrizedPropertyType().toDTO());
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
	IContainer<IColumn<SchemaImplementation>> getRefBackReferencingColumns() {
		
		if (!columnHelper.isAReferenceColumn(this)) {
			return new LinkedList<>();
		}
		
		return getRefBackReferencingColumnsWhenIsReferenceColumn();
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
		final IParametrizedPropertyType<SchemaImplementation> parametrizedPropertyType
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
	private IContainer<IColumn<SchemaImplementation>> getRefBackReferencingColumnsWhenIsReferenceColumn() {
		
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

//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDTO;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public abstract class BaseParametrizedBackReferenceType extends ParametrizedPropertyType
implements IBaseParametrizedBackReferenceType<SchemaImplementation> {
	
	//attribute
	private final IColumn<SchemaImplementation> backReferencedColumn;
	
	//constructor
	protected BaseParametrizedBackReferenceType(final IColumn<SchemaImplementation> backReferencedColumn) {
		
		super(DataType.STRING);
		
		assertIsAnyReferenceColumn(backReferencedColumn);
		
		this.backReferencedColumn = backReferencedColumn;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<SchemaImplementation> asBaseParametrizedBackReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<SchemaImplementation> asBaseParametrizedReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<SchemaImplementation, ?> asBaseParametrizedValueType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedValueType");
	}
	
	//method
	@Override
	public IColumn<SchemaImplementation> getBackReferencedColumn() {
		return backReferencedColumn;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable<?> table) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBackColumn(final IColumn<?> column) {
		return (getBackReferencedColumn() == column);
	}
	
	//method
	@Override
	public final IParametrizedPropertyTypeDTO toDTO() {
		return
		new BaseParametrizedBackReferenceTypeDTO(
			getPropertyType(),
			getDataType(),
			getBackReferencedColumn().getId()
		);
	}
	
	//method
	private void assertIsAnyReferenceColumn(IColumn<SchemaImplementation> backReferencedColumn) {
		if (!isAnyReferenceColumn(backReferencedColumn)) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				"back referenced column",
				backReferencedColumn,
				"is not any refence column"
			);
		}
	}
	
	//method
	private boolean isAnyReferenceColumn(IColumn<SchemaImplementation> backReferencedColumn) {
		return
		backReferencedColumn.getParametrizedPropertyType().getPropertyType().getBaseType() ==
		BasePropertyType.BASE_REFERENCE;
	}
}

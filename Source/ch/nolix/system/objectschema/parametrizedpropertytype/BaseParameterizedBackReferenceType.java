//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.BasePropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedBackReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedReferenceType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBaseParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public abstract class BaseParameterizedBackReferenceType extends ParameterizedPropertyType
implements IBaseParameterizedBackReferenceType {
	
	//attribute
	private final IColumn backReferencedColumn;
	
	//constructor
	protected BaseParameterizedBackReferenceType(final IColumn backReferencedColumn) {
		
		super(DataType.STRING);
		
		assertIsAnyReferenceColumn(backReferencedColumn);
		
		this.backReferencedColumn = backReferencedColumn;
	}
	
	//method
	@Override
	public final IBaseParameterizedBackReferenceType asBaseParametrizedBackReferenceType() {
		return this;
	}
	
	//method
	@Override
	public final IBaseParameterizedReferenceType asBaseParametrizedReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParameterizedValueType<?> asBaseParametrizedValueType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedValueType");
	}
	
	//method
	@Override
	public IColumn getBackReferencedColumn() {
		return backReferencedColumn;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable table) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBackColumn(final IColumn column) {
		return (getBackReferencedColumn() == column);
	}
	
	//method
	@Override
	public final IParameterizedPropertyTypeDto toDto() {
		return
		new BaseParametrizedBackReferenceTypeDto(
			getPropertyType(),
			getDataType(),
			getBackReferencedColumn().getId()
		);
	}
	
	//method
	private void assertIsAnyReferenceColumn(IColumn backReferencedColumn) {
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
	private boolean isAnyReferenceColumn(IColumn backReferencedColumn) {
		return
		backReferencedColumn.getParametrizedPropertyType().getPropertyType().getBaseType() ==
		BasePropertyType.BASE_REFERENCE;
	}
}

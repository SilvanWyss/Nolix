//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedMultiValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalBackReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedOptionalValueType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedPropertyType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedReferenceType;
import ch.nolix.system.objectschema.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDto;

//class
public final class ParametrizedPropertyTypeMapper {
	
	//method
	public ParametrizedPropertyType createParametrizedPropertyTypeFromDto(
		final IParametrizedPropertyTypeDto parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		switch (parametrizedPropertyType.getPropertyType()) {
			case VALUE:
				return new ParametrizedValueType<>(parametrizedPropertyType.getDataType());
			case OPTIONAL_VALUE:
				return new ParametrizedOptionalValueType<>(parametrizedPropertyType.getDataType());
			case MULTI_VALUE:
				return new ParametrizedMultiValueType<>(parametrizedPropertyType.getDataType());
			case REFERENCE:
				return
				new ParametrizedReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_REFERENCE:
				return
				new ParametrizedOptionalReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_REFERENCE:
				return
				new ParametrizedMultiReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case BACK_REFERENCE:
				return
				new ParametrizedBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_BACK_REFERENCE:
				return
				new ParametrizedOptionalBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_BACK_REFERENCE:
				return
				new ParametrizedMultiBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			default:
				throw InvalidArgumentException.forArgument(parametrizedPropertyType);
		}
	}
	
	//method
	private Column getStoredBackReferencedColumnFromParametrizedPropertyType(
		final IParametrizedPropertyTypeDto parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedBackReferenceType = (IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyType;
		final var backReferencedColumnId = baseParametrizedBackReferenceType.getBackReferencedColumnId();
		
		return
		(Column)tables.toFromGroups(ITable::getStoredColumns).getStoredFirst(c -> c.hasId(backReferencedColumnId));
	}
	
	//method
	private ITable getStoredReferencedTableFromParametrizedPropertyType(
		final IParametrizedPropertyTypeDto parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedReferenceType = (IBaseParametrizedReferenceTypeDto)parametrizedPropertyType;
		
		return tables.getStoredFirst(t -> t.hasId(baseParametrizedReferenceType.getReferencedTableId()));
	}
}

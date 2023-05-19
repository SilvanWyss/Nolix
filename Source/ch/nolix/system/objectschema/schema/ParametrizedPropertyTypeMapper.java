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
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ParametrizedPropertyTypeMapper {
	
	//method
	public ParametrizedPropertyType createParametrizedPropertyTypeFromDTO(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
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
					getOriReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_REFERENCE:
				return
				new ParametrizedOptionalReferenceType(
					getOriReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_REFERENCE:
				return
				new ParametrizedMultiReferenceType(
					getOriReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case BACK_REFERENCE:
				return
				new ParametrizedBackReferenceType(
					getOriBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_BACK_REFERENCE:
				return
				new ParametrizedOptionalBackReferenceType(
					getOriBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_BACK_REFERENCE:
				return
				new ParametrizedMultiBackReferenceType(
					getOriBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			default:
				throw InvalidArgumentException.forArgument(parametrizedPropertyType);
		}
	}
	
	//method
	private Column getOriBackReferencedColumnFromParametrizedPropertyType(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedBackReferenceType = (IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyType;
		final var backReferencedColumnId = baseParametrizedBackReferenceType.getBackReferencedColumnId();
		
		return
		(Column)tables.toFromGroups(ITable::getOriColumns).getOriFirst(c -> c.hasId(backReferencedColumnId));
	}
	
	//method
	private ITable getOriReferencedTableFromParametrizedPropertyType(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedReferenceType = (IBaseParametrizedReferenceTypeDTO)parametrizedPropertyType;
		
		return tables.getOriFirst(t -> t.hasId(baseParametrizedReferenceType.getOrierencedTableId()));
	}
}

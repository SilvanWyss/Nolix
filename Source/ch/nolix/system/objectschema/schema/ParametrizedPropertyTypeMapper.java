//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
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
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
public final class ParametrizedPropertyTypeMapper {
	
	//method
	public ParametrizedPropertyType<?> createParametrizedPropertyTypeFromDTO(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
		final IContainer<ITable<SchemaImplementation>> tables
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
					getRefReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_REFERENCE:
				return
				new ParametrizedOptionalReferenceType(
					getRefReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_REFERENCE:
				return
				new ParametrizedMultiReferenceType(
					getRefReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case BACK_REFERENCE:
				return
				new ParametrizedBackReferenceType(
					getRefBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_BACK_REFERENCE:
				return
				new ParametrizedOptionalBackReferenceType(
					getRefBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_BACK_REFERENCE:
				return
				new ParametrizedMultiBackReferenceType(
					getRefBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			default:
				throw new InvalidArgumentException(parametrizedPropertyType);
		}
	}
	
	//method
	private Column getRefBackReferencedColumnFromParametrizedPropertyType(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
		final IContainer<ITable<SchemaImplementation>> tables
	) {
		
		final var baseParametrizedBackReferenceType = (IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyType;
		final var backReferencedColumnId = baseParametrizedBackReferenceType.getBackReferencedColumnId();
		
		return
		(Column)tables.toFromMany(ITable::getRefColumns).getRefFirst(c -> c.hasId(backReferencedColumnId));
	}
	
	//method
	private ITable<SchemaImplementation> getRefReferencedTableFromParametrizedPropertyType(
		final IParametrizedPropertyTypeDTO parametrizedPropertyType,
		final IContainer<ITable<SchemaImplementation>> tables
	) {
		
		final var baseParametrizedReferenceType = (IBaseParametrizedReferenceTypeDTO)parametrizedPropertyType;
		
		return tables.getRefFirst(t -> t.hasId(baseParametrizedReferenceType.getReferencedTableId()));
	}
}

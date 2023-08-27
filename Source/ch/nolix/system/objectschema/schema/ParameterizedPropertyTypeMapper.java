//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedBackReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedMultiBackReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedMultiReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedMultiValueType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedOptionalBackReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedOptionalReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedOptionalValueType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedPropertyType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedReferenceType;
import ch.nolix.system.objectschema.parameterizedpropertytype.ParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParameterizedPropertyTypeMapper {
	
	//method
	public ParameterizedPropertyType createParametrizedPropertyTypeFromDto(
		final IParameterizedPropertyTypeDto parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		return
		switch (parametrizedPropertyType.getPropertyType()) {
			case VALUE ->
				new ParameterizedValueType<>(parametrizedPropertyType.getDataType());
			case OPTIONAL_VALUE ->
				new ParameterizedOptionalValueType<>(parametrizedPropertyType.getDataType());
			case MULTI_VALUE ->
				new ParameterizedMultiValueType<>(parametrizedPropertyType.getDataType());
			case REFERENCE ->
				new ParameterizedReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_REFERENCE ->
				new ParameterizedOptionalReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_REFERENCE ->
				new ParameterizedMultiReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case BACK_REFERENCE ->
				new ParameterizedBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_BACK_REFERENCE ->
				new ParameterizedOptionalBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_BACK_REFERENCE ->
				new ParameterizedMultiBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			default ->
				throw InvalidArgumentException.forArgument(parametrizedPropertyType);
		};
	}
	
	//method
	private Column getStoredBackReferencedColumnFromParametrizedPropertyType(
		final IParameterizedPropertyTypeDto parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedBackReferenceType = (IBaseParameterizedBackReferenceTypeDto)parametrizedPropertyType;
		final var backReferencedColumnId = baseParametrizedBackReferenceType.getBackReferencedColumnId();
		
		return
		(Column)tables.toFromGroups(ITable::getStoredColumns).getStoredFirst(c -> c.hasId(backReferencedColumnId));
	}
	
	//method
	private ITable getStoredReferencedTableFromParametrizedPropertyType(
		final IParameterizedPropertyTypeDto parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedReferenceType = (IBaseParameterizedReferenceTypeDto)parametrizedPropertyType;
		
		return tables.getStoredFirst(t -> t.hasId(baseParametrizedReferenceType.getReferencedTableId()));
	}
}

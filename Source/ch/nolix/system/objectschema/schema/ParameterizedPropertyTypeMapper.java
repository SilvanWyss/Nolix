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
		final IParameterizedPropertyTypeDto parameterizedPropertyType,
		final IContainer<ITable> tables
	) {
		return
		switch (parameterizedPropertyType.getPropertyType()) {
			case VALUE ->
				new ParameterizedValueType<>(parameterizedPropertyType.getDataType());
			case OPTIONAL_VALUE ->
				new ParameterizedOptionalValueType<>(parameterizedPropertyType.getDataType());
			case MULTI_VALUE ->
				new ParameterizedMultiValueType<>(parameterizedPropertyType.getDataType());
			case REFERENCE ->
				new ParameterizedReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parameterizedPropertyType, tables)
				);
			case OPTIONAL_REFERENCE ->
				new ParameterizedOptionalReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parameterizedPropertyType, tables)
				);
			case MULTI_REFERENCE ->
				new ParameterizedMultiReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parameterizedPropertyType, tables)
				);
			case BACK_REFERENCE ->
				new ParameterizedBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parameterizedPropertyType, tables)
				);
			case OPTIONAL_BACK_REFERENCE ->
				new ParameterizedOptionalBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parameterizedPropertyType, tables)
				);
			case MULTI_BACK_REFERENCE ->
				new ParameterizedMultiBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parameterizedPropertyType, tables)
				);
			default ->
				throw InvalidArgumentException.forArgument(parameterizedPropertyType);
		};
	}
	
	//method
	private Column getStoredBackReferencedColumnFromParametrizedPropertyType(
		final IParameterizedPropertyTypeDto parameterizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedBackReferenceType = (IBaseParameterizedBackReferenceTypeDto)parameterizedPropertyType;
		final var backReferencedColumnId = baseParametrizedBackReferenceType.getBackReferencedColumnId();
		
		return
		(Column)tables.toFromGroups(ITable::getStoredColumns).getStoredFirst(c -> c.hasId(backReferencedColumnId));
	}
	
	//method
	private ITable getStoredReferencedTableFromParametrizedPropertyType(
		final IParameterizedPropertyTypeDto parameterizedPropertyType,
		final IContainer<ITable> tables
	) {
		
		final var baseParametrizedReferenceType = (IBaseParameterizedReferenceTypeDto)parameterizedPropertyType;
		
		return tables.getStoredFirst(t -> t.hasId(baseParametrizedReferenceType.getReferencedTableId()));
	}
}

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
import ch.nolix.system.objectschema.parametrizedpropertytype.ParameterizedValueType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParametrizedPropertyTypeMapper {
	
	//method
	public ParametrizedPropertyType createParametrizedPropertyTypeFromDto(
		final IParameterizedPropertyTypeDto parametrizedPropertyType,
		final IContainer<ITable> tables
	) {
		return
		switch (parametrizedPropertyType.getPropertyType()) {
			case VALUE ->
				new ParameterizedValueType<>(parametrizedPropertyType.getDataType());
			case OPTIONAL_VALUE ->
				new ParametrizedOptionalValueType<>(parametrizedPropertyType.getDataType());
			case MULTI_VALUE ->
				new ParametrizedMultiValueType<>(parametrizedPropertyType.getDataType());
			case REFERENCE ->
				new ParametrizedReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_REFERENCE ->
				new ParametrizedOptionalReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_REFERENCE ->
				new ParametrizedMultiReferenceType(
					getStoredReferencedTableFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case BACK_REFERENCE ->
				new ParametrizedBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case OPTIONAL_BACK_REFERENCE ->
				new ParametrizedOptionalBackReferenceType(
					getStoredBackReferencedColumnFromParametrizedPropertyType(parametrizedPropertyType, tables)
				);
			case MULTI_BACK_REFERENCE ->
				new ParametrizedMultiBackReferenceType(
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

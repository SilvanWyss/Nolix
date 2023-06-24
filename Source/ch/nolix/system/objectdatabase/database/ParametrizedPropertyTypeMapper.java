//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.parametrizedpropertytype.ParametrizedBackReferenceType;
import ch.nolix.system.objectdatabase.parametrizedpropertytype.ParametrizedMultiReferenceType;
import ch.nolix.system.objectdatabase.parametrizedpropertytype.ParametrizedMultiValueType;
import ch.nolix.system.objectdatabase.parametrizedpropertytype.ParametrizedOptionalReferenceType;
import ch.nolix.system.objectdatabase.parametrizedpropertytype.ParametrizedOptionalValueType;
import ch.nolix.system.objectdatabase.parametrizedpropertytype.ParametrizedReferenceType;
import ch.nolix.system.objectdatabase.parametrizedpropertytype.ParametrizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDto;

//class
final class ParametrizedPropertyTypeMapper {
	
	//method
	public IParametrizedPropertyType
	createParametrizedPropertyTypeFromDTOUsingGivenReferencableTables(
		final IParametrizedPropertyTypeDto parametrizedPropertyTypeDto,
		final IContainer<? extends ITable<IEntity>> referencableTables
	) {
		switch (parametrizedPropertyTypeDto.getPropertyType()) {
			case VALUE:
				
				final var valueType = parametrizedPropertyTypeDto.getDataType().getDataTypeClass();
				
				return new ParametrizedValueType<>(valueType);
			case OPTIONAL_VALUE:
				
				final var valueType2 = parametrizedPropertyTypeDto.getDataType().getDataTypeClass();
				
				return new ParametrizedOptionalValueType<>(valueType2);
			case MULTI_VALUE:
				
				final var valueType3 = parametrizedPropertyTypeDto.getDataType().getDataTypeClass();
				
				return new ParametrizedMultiValueType<>(valueType3);
			case REFERENCE:
				
				final var baseParametrizedReferenceTypeDTO =
				(IBaseParametrizedReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var referencedTable =
				referencableTables.getOriFirst(t -> t.hasId(baseParametrizedReferenceTypeDTO.getReferencedTableId()));
				
				return new ParametrizedReferenceType<>(referencedTable);
			case OPTIONAL_REFERENCE:
				
				final var baseParametrizedReferenceTypeDTO2 =
				(IBaseParametrizedReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var referencedTable2 =
				referencableTables.getOriFirst(t -> t.hasId(baseParametrizedReferenceTypeDTO2.getReferencedTableId()));
				
				return new ParametrizedOptionalReferenceType<>(referencedTable2);
			case MULTI_REFERENCE:
				
				final var baseParametrizedReferenceTypeDTO3 =
				(IBaseParametrizedReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var referencedTable3 =
				referencableTables.getOriFirst(t -> t.hasId(baseParametrizedReferenceTypeDTO3.getReferencedTableId()));
				
				return new ParametrizedMultiReferenceType<>(referencedTable3);
			case BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDTO =
				(IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var backReferencedColumn =
				referencableTables
				.toFromGroups(ITable::getOriColumns)
				.getOriFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDTO.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn);
			case OPTIONAL_BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDTO2 =
				(IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var backReferencedColumn2 =
				referencableTables
				.toFromGroups(ITable::getOriColumns)
				.getOriFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDTO2.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn2);
			case MULTI_BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDTO3 =
				(IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var backReferencedColumn3 =
				referencableTables
				.toFromGroups(ITable::getOriColumns)
				.getOriFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDTO3.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn3);
			default:
				throw InvalidArgumentException.forArgument(parametrizedPropertyTypeDto);
		}
	}
}

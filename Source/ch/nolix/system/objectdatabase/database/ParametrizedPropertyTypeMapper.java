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
	createParametrizedPropertyTypeFromDtoUsingGivenReferencableTables(
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
				
				final var baseParametrizedReferenceTypeDto =
				(IBaseParametrizedReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var referencedTable =
				referencableTables.getStoredFirst(t -> t.hasId(baseParametrizedReferenceTypeDto.getReferencedTableId()));
				
				return new ParametrizedReferenceType<>(referencedTable);
			case OPTIONAL_REFERENCE:
				
				final var baseParametrizedReferenceTypeDto2 =
				(IBaseParametrizedReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var referencedTable2 =
				referencableTables.getStoredFirst(t -> t.hasId(baseParametrizedReferenceTypeDto2.getReferencedTableId()));
				
				return new ParametrizedOptionalReferenceType<>(referencedTable2);
			case MULTI_REFERENCE:
				
				final var baseParametrizedReferenceTypeDto3 =
				(IBaseParametrizedReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var referencedTable3 =
				referencableTables.getStoredFirst(t -> t.hasId(baseParametrizedReferenceTypeDto3.getReferencedTableId()));
				
				return new ParametrizedMultiReferenceType<>(referencedTable3);
			case BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDto =
				(IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var backReferencedColumn =
				referencableTables
				.toFromGroups(ITable::getStoredColumns)
				.getStoredFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDto.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn);
			case OPTIONAL_BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDto2 =
				(IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var backReferencedColumn2 =
				referencableTables
				.toFromGroups(ITable::getStoredColumns)
				.getStoredFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDto2.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn2);
			case MULTI_BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDto3 =
				(IBaseParametrizedBackReferenceTypeDto)parametrizedPropertyTypeDto;
				
				final var backReferencedColumn3 =
				referencableTables
				.toFromGroups(ITable::getStoredColumns)
				.getStoredFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDto3.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn3);
			default:
				throw InvalidArgumentException.forArgument(parametrizedPropertyTypeDto);
		}
	}
}

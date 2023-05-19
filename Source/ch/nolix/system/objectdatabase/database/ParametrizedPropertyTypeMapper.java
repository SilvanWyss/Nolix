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
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedBackReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParametrizedReferenceTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;

//class
final class ParametrizedPropertyTypeMapper {
	
	//method
	public IParametrizedPropertyType
	createParametrizedPropertyTypeFromDTOUsingGivenReferencableTables(
		final IParametrizedPropertyTypeDTO parametrizedPropertyTypeDTO,
		final IContainer<? extends ITable<IEntity>> referencableTables
	) {
		switch (parametrizedPropertyTypeDTO.getPropertyType()) {
			case VALUE:
				
				final var valueType = parametrizedPropertyTypeDTO.getDataType().getDataTypeClass();
				
				return new ParametrizedValueType<>(valueType);
			case OPTIONAL_VALUE:
				
				final var valueType2 = parametrizedPropertyTypeDTO.getDataType().getDataTypeClass();
				
				return new ParametrizedOptionalValueType<>(valueType2);
			case MULTI_VALUE:
				
				final var valueType3 = parametrizedPropertyTypeDTO.getDataType().getDataTypeClass();
				
				return new ParametrizedMultiValueType<>(valueType3);
			case REFERENCE:
				
				final var baseParametrizedReferenceTypeDTO =
				(IBaseParametrizedReferenceTypeDTO)parametrizedPropertyTypeDTO;
				
				final var referencedTable =
				referencableTables.getOriFirst(t -> t.hasId(baseParametrizedReferenceTypeDTO.getOrierencedTableId()));
				
				return new ParametrizedReferenceType<>(referencedTable);
			case OPTIONAL_REFERENCE:
				
				final var baseParametrizedReferenceTypeDTO2 =
				(IBaseParametrizedReferenceTypeDTO)parametrizedPropertyTypeDTO;
				
				final var referencedTable2 =
				referencableTables.getOriFirst(t -> t.hasId(baseParametrizedReferenceTypeDTO2.getOrierencedTableId()));
				
				return new ParametrizedOptionalReferenceType<>(referencedTable2);
			case MULTI_REFERENCE:
				
				final var baseParametrizedReferenceTypeDTO3 =
				(IBaseParametrizedReferenceTypeDTO)parametrizedPropertyTypeDTO;
				
				final var referencedTable3 =
				referencableTables.getOriFirst(t -> t.hasId(baseParametrizedReferenceTypeDTO3.getOrierencedTableId()));
				
				return new ParametrizedMultiReferenceType<>(referencedTable3);
			case BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDTO =
				(IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyTypeDTO;
				
				final var backReferencedColumn =
				referencableTables
				.toFromGroups(ITable::getOriColumns)
				.getOriFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDTO.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn);
			case OPTIONAL_BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDTO2 =
				(IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyTypeDTO;
				
				final var backReferencedColumn2 =
				referencableTables
				.toFromGroups(ITable::getOriColumns)
				.getOriFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDTO2.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn2);
			case MULTI_BACK_REFERENCE:
				
				final var baseParametrizedBackRefenceTypeDTO3 =
				(IBaseParametrizedBackReferenceTypeDTO)parametrizedPropertyTypeDTO;
				
				final var backReferencedColumn3 =
				referencableTables
				.toFromGroups(ITable::getOriColumns)
				.getOriFirst(c -> c.hasId(baseParametrizedBackRefenceTypeDTO3.getBackReferencedColumnId()));
				
				return new ParametrizedBackReferenceType<>(backReferencedColumn3);
			default:
				throw InvalidArgumentException.forArgument(parametrizedPropertyTypeDTO);
		}
	}
}

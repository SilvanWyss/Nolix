//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//class
final class ColumnMapper {
	
	//static attribute
	private final ParametrizedPropertyTypeMapper parametrizedPropertyTypeMapper = new ParametrizedPropertyTypeMapper();
	
	//method
	public IColumn createColumnFromDTOForParentTableUsingGivenReferencableTables(
		final IColumnDto columnDto,
		final Table<IEntity> parentTable,
		final IContainer<? extends ITable<IEntity>> referencableTables
	) {
		return
		Column.withNameAndIdAndParametrizedPropertyTypeAndParentTable(
			columnDto.getName(),
			columnDto.getId(),
			parametrizedPropertyTypeMapper.createParametrizedPropertyTypeFromDTOUsingGivenReferencableTables(
				columnDto.getParametrizedPropertyType(),
				referencableTables
			),
			parentTable
		);
	}
}

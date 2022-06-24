//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

//class
final class ColumnMapper {
	
	//static attribute
	private final ParametrizedPropertyTypeMapper parametrizedPropertyTypeMapper = new ParametrizedPropertyTypeMapper();
	
	//method
	public IColumn<DataImplementation> createColumnFromDTOForParentTableUsingGivenReferencableTables(
		final IColumnDTO columnDTO,
		final Table<IEntity<DataImplementation>> parentTable,
		final IContainer<ITable<DataImplementation, IEntity<DataImplementation>>> referencableTables
	) {
		return
		Column.withNameAndIdAndParametrizedPropertyTypeAndParentTable(
			columnDTO.getName(),
			columnDTO.getId(),
			parametrizedPropertyTypeMapper.createParametrizedPropertyTypeFromDTOUsingGivenReferencableTables(
				columnDTO.getParametrizedPropertyType(),
				referencableTables
			),
			parentTable
		);
	}
}

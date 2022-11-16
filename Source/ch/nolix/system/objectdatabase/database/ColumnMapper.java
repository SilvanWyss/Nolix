//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
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

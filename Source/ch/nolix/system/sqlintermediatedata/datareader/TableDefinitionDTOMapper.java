//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//own imports
import ch.nolix.system.sqlintermediatedata.recorddto.TableDefinitionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ITableDefinitionDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableDefinitionDTOMapper {
	
	//method
	public ITableDefinitionDTO createTableDefinitionDTOFrom(final ITableDTO table) {
		return new TableDefinitionDTO(table.getName(), table.getColumns().to(IColumnDTO::getName));
	}
}

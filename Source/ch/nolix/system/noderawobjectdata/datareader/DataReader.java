//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableDefinition;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataReader;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

//class
public final class DataReader implements IDataReader {
	
	//attribute
	private final InternalDataReader internalDataReader;
	
	//multi-attribute
	private final IContainer<TableDefinition> tableDefinitions;
	
	//constructor
	public DataReader(final BaseNode databaseNode, final IContainer<TableDefinition> tableDefinitions) {
		
		Validator.assertThat(tableDefinitions).thatIsNamed("table definitions").isNotNull();
		Validator.assertThat(tableDefinitions).thatIsNamed("table definitions").isNotNull();
		
		internalDataReader = new InternalDataReader(databaseNode);
		this.tableDefinitions = tableDefinitions;
	}
	
	//method
	@Override
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return internalDataReader.loadAllRecordsFromTable(getTableDefinitionForTableWithName(tableName));
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDataReader.loadRecordFromTableById(getTableDefinitionForTableWithName(tableName), id);
	}
	
	//method
	@Override
	public boolean tableContainsRecordWithGivenValueAtColumn(String tableName, String columnHeader, String value) {
		return
		internalDataReader.tableContainsRecordWithGivenValueAtColumn(
			getTableDefinitionForTableWithName(tableName),
			columnHeader,
			value
		);
	}
	
	//method
	private TableDefinition getTableDefinitionForTableWithName(final String tableName) {
		return tableDefinitions.getRefFirst(td -> td.getName().equals(tableName));
	}
}

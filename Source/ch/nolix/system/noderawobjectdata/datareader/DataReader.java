//package declaration
package ch.nolix.system.noderawobjectdata.datareader;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableDefinition;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;

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
	public LinkedList<Object> loadMultiValueEntriesFromRecord(
		final String tableName,
		final String recordId,
		final String multiFieldColumnName
	) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return internalDataReader.loadRecordFromTableById(getTableDefinitionForTableWithName(tableName), id);
	}
	
	//method
	@Override
	public boolean tableContainsRecordWithGivenValueAtColumn(String tableName, String columnName, String value) {
		return
		internalDataReader.tableContainsRecordWithGivenValueAtColumn(
			getTableDefinitionForTableWithName(tableName),
			columnName,
			value
		);
	}
	
	//method
	private TableDefinition getTableDefinitionForTableWithName(final String tableName) {
		return tableDefinitions.getRefFirst(td -> td.getName().equals(tableName));
	}
}

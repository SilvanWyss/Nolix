//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableDefinition;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class DataWriter implements IDataWriter {
	
	//attributes
	private final InternalDataWriter internalDataWriter;
	
	//multi-attribute
	private final IContainer<TableDefinition> tableDefinitions;
	
	//constructor
	public DataWriter(final BaseNode nodeDatabase, final IContainer<TableDefinition> tableDefinitions) {
		
		Validator.assertThat(tableDefinitions).thatIsNamed("table definitions").isNotNull();
		
		internalDataWriter = new InternalDataWriter(nodeDatabase);
		this.tableDefinitions = tableDefinitions;		
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void deleteEntryFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IRecordDeletionDTO recordDeletion) {
		internalDataWriter.deleteRecordFromTable(tableName, recordDeletion);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return internalDataWriter.hasChanges();
	}
	
	//method
	@Override
	public void insertEntryIntoMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void insertRecordIntoTable(String tableName, IRecordDTO record) {
		internalDataWriter.insertRecordIntoTable(getTableDefinitionForTableWithName(tableName), record);
	}
	
	//method
	@Override
	public void saveChanges() {
		internalDataWriter.saveChanges();
	}
	
	//method
	@Override
	public void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate) {
		internalDataWriter.updateRecordOnTable(getTableDefinitionForTableWithName(tableName), recordUpdate);
	}
	
	//method
	private TableDefinition getTableDefinitionForTableWithName(final String tableName) {
		return tableDefinitions.getRefFirst(td -> td.getName().equals(tableName));
	}
}

//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
final class InternalDataWriter {
	
	//static attribute
	private static final DatabaseUpdater databaseUpdater = new DatabaseUpdater();
	
	//attribute
	private final BaseNode nodeDatabase;
	
	//multi-attribute
	private final LinkedList<IElementTaker<BaseNode>> changeActions = new LinkedList<>();
	
	//constructor
	public InternalDataWriter(final BaseNode nodeDatabase) {
		
		Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();
		
		this.nodeDatabase = nodeDatabase;
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final TableInfo tableInfo,
		final String recordId,
		final String multiValueColumnName
	) {
		addChangeAction(
			d -> databaseUpdater.deleteEntriesFromMultiValue(d, tableInfo, recordId, multiValueColumnName)
		);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final TableInfo tableInfo,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		addChangeAction(
			d -> databaseUpdater.deleteEntryFromMultiValue(d, tableInfo, recordId, multiValueColumnName, entry)
		);
	}
	
	//method
	public void deleteRecordFromTable(final String tableName, final IRecordHeadDTO recordHead) {
		addChangeAction(d -> databaseUpdater.deleteRecordFromTable(d, tableName, recordHead));
	}
	
	//method
	public boolean hasChanges() {
		return changeActions.containsAny();
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final TableInfo tableInfo,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		addChangeAction(
			d -> databaseUpdater.insertEntryIntoMultiValue(d, tableInfo, recordId, multiValueColumnName, entry)
		);
	}
	
	//method
	public void insertRecordIntoTable(final TableInfo tableInfo, final IRecordDTO record) {
		addChangeAction(d -> databaseUpdater.insertRecordIntoTable(d, tableInfo, record));
	}
	
	//method
	public void saveChanges() {
		if (hasChanges()) {
			saveChangesWhenHasChanges();
		}
	}
	
	//method
	public void updateRecordOnTable(final TableInfo tableInfo, IRecordUpdateDTO recordUpdate) {
		addChangeAction(d -> databaseUpdater.updateRecordOnTable(d, tableInfo, recordUpdate));
	}
	
	private void addChangeAction(final IElementTaker<BaseNode> changeAction) {
		addChangeAction(changeAction);
	}
	
	// method
	private BaseNode createNodeDatabaseWithChanges() {
		
		final var newNodeDatabase = nodeDatabase.getCopy();
		for (final var ca : changeActions) {
			ca.run(newNodeDatabase);
		}
		
		return newNodeDatabase;
	}
	
	//method
	private synchronized void saveChangesWhenHasChanges() {
		nodeDatabase.resetAttributes(createNodeDatabaseWithChanges().getRefAttributes());
	}
}

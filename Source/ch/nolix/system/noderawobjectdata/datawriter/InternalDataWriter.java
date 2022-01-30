//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
final class InternalDataWriter {
	
	//static attributes
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
		final IRecordHeadDTO recordHead,
		final String multiValueColumnName
	) {
		changeActions.addAtEnd(
			d -> databaseUpdater.deleteEntriesFromMultiValue(d, tableInfo, recordHead, multiValueColumnName)
		);
	}
	
	//method
	public void deleteRecordFromTable(final String tableName, final IRecordDeletionDTO recordDeletion) {
		changeActions.addAtEnd(d -> databaseUpdater.deleteRecordFromTable(d, tableName, recordDeletion));
	}
	
	//method
	public void insertRecordIntoTable(final TableInfo tableInfo, final IRecordDTO record) {
		changeActions.addAtEnd(d -> databaseUpdater.insertRecordIntoTable(d, tableInfo, record));
	}
	
	//method
	public boolean hasChanges() {
		return changeActions.containsAny();
	}
	
	//method
	public void saveChanges() {
		if (hasChanges()) {
			saveChangesWhenHasChanges();
		}
	}
	
	//method
	public void updateRecordOnTable(final TableInfo tableInfo, IRecordUpdateDTO recordUpdate) {
		changeActions.addAtEnd(d -> databaseUpdater.updateRecordOnTable(d, tableInfo, recordUpdate));
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

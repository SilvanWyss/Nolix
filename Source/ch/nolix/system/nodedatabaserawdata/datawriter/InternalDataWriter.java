//package declaration
package ch.nolix.system.nodedatabaserawdata.datawriter;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class InternalDataWriter {
	
	//static attribute
	private static final DatabaseUpdater databaseUpdater = new DatabaseUpdater();
	
	//attribute
	private int saveCount;
	
	//attribute
	private final BaseNode nodeDatabase;
	
	//multi-attribute
	private final LinkedList<IElementTaker<BaseNode>> changeActions = new LinkedList<>();
	
	//constructor
	public InternalDataWriter(final BaseNode nodeDatabase) {
		
		GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();
		
		this.nodeDatabase = nodeDatabase;
	}
	
	//method
	public void deleteEntriesFromMultiReference(
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiReferenceColumnInfo
	) {		
		addChangeAction(
			d -> databaseUpdater.deleteEntriesFromMultiReference(d, tableInfo, entityId, multiReferenceColumnInfo)
		);
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiValueColumnInfo
	) {		
		addChangeAction(
			d -> databaseUpdater.deleteEntriesFromMultiValue(d, tableInfo, entityId, multiValueColumnInfo)
		);
	}
	
	//method
	public void deleteEntryFromMultiReference(
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiReferenceColumnInfo,
		final String referencedEntityId
	) {
		addChangeAction(
			d -> 
			databaseUpdater.deleteEntryFromMultiReference(
				d,
				tableInfo,
				entityId,
				multiReferenceColumnInfo,
				referencedEntityId
			)
		);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiValueColumnInfo,
		final String entry
	) {
		addChangeAction(
			d -> databaseUpdater.deleteEntryFromMultiValue(d, tableInfo, entityId, multiValueColumnInfo, entry)
		);
	}
	
	//method
	public void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		addChangeAction(d -> databaseUpdater.deleteRecordFromTable(d, tableName, entity));
	}
	
	//method
	public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
		addChangeAction(d -> databaseUpdater.expectGivenSchemaTimestamp(d, schemaTimestamp));
	}
	
	//method
	public int getSaveCount() {
		return saveCount;
	}
	
	//method
	public boolean hasChanges() {
		return changeActions.containsAny();
	}
	
	//method
	public void insertEntryIntoMultiReference(
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiReferenceColumnInfo,
		final String referencedEntityId
	) {
		addChangeAction(
			d ->
			databaseUpdater.insertEntryIntoMultiReference(
				d,
				tableInfo,
				entityId,
				multiReferenceColumnInfo,
				referencedEntityId
			)
		);
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final ITableInfo tableInfo,
		final String entityId,
		final IColumnInfo multiValueColumnInfo,
		final String entry
	) {
		addChangeAction(
			d -> databaseUpdater.insertEntryIntoMultiValue(d, tableInfo, entityId, multiValueColumnInfo, entry)
		);
	}
	
	//method
	public void insertRecordIntoTable(final ITableInfo tableInfo, final IRecordDTO record) {
		addChangeAction(d -> databaseUpdater.insertRecordIntoTable(d, tableInfo, record));
	}
	
	//methods
	public void reset() {
		changeActions.clear();
	}
	
	//method
	public void saveChangesAndReset() {
		try {
			nodeDatabase.resetAttributes(createNodeDatabaseWithChanges().getRefAttributes());
			saveCount++;
		} finally {
			reset();
		}
	}
	
	//method
	public void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		addChangeAction(d -> databaseUpdater.setEntityAsUpdated(d, tableName, entity));
	}
	
	//method
	public void updateRecordOnTable(final ITableInfo tableInfo, IRecordUpdateDTO recordUpdate) {
		addChangeAction(d -> databaseUpdater.updateRecordOnTable(d, tableInfo, recordUpdate));
	}
	
	//method
	private void addChangeAction(final IElementTaker<BaseNode> changeAction) {
		changeActions.addAtEnd(changeAction);
	}
	
	// method
	private BaseNode createNodeDatabaseWithChanges() {
		
		final var newNodeDatabase = nodeDatabase.getCopy();
		for (final var ca : changeActions) {
			ca.run(newNodeDatabase);
		}
		
		return newNodeDatabase;
	}
}

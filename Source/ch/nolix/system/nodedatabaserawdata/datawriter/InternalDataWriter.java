//package declaration
package ch.nolix.system.nodedatabaserawdata.datawriter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

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
		
		Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();
		
		this.nodeDatabase = nodeDatabase;
	}
	
	//method
	public void deleteEntriesFromMultiReference(
		final TableInfo tableInfo,
		final String entityId,
		final String multiReferenceColumnName
	) {
		addChangeAction(
			d -> databaseUpdater.deleteEntriesFromMultiReference(d, tableInfo, entityId, multiReferenceColumnName)
		);
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final TableInfo tableInfo,
		final String entityId,
		final String multiValueColumnName
	) {
		addChangeAction(
			d -> databaseUpdater.deleteEntriesFromMultiValue(d, tableInfo, entityId, multiValueColumnName)
		);
	}
	
	//method
	public void deleteEntryFromMultiReference(
		final TableInfo tableInfo,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		addChangeAction(
			d -> 
			databaseUpdater.deleteEntryFromMultiReference(
				d,
				tableInfo,
				entityId,
				multiReferenceColumnName,
				referencedEntityId
			)
		);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final TableInfo tableInfo,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		addChangeAction(
			d -> databaseUpdater.deleteEntryFromMultiValue(d, tableInfo, entityId, multiValueColumnName, entry)
		);
	}
	
	//method
	public void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		addChangeAction(d -> databaseUpdater.deleteRecordFromTable(d, tableName, entity));
	}
	
	//method
	public void expectGivenSchemaTimestamp(Time schemaTimestamp) {
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
		final TableInfo tableInfo,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		addChangeAction(
			d ->
			databaseUpdater.insertEntryIntoMultiReference(
				d,
				tableInfo,
				entityId,
				multiReferenceColumnName,
				referencedEntityId
			)
		);
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final TableInfo tableInfo,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		addChangeAction(
			d -> databaseUpdater.insertEntryIntoMultiValue(d, tableInfo, entityId, multiValueColumnName, entry)
		);
	}
	
	//method
	public void insertRecordIntoTable(final TableInfo tableInfo, final IRecordDTO record) {
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
	public void updateRecordOnTable(final TableInfo tableInfo, IRecordUpdateDTO recordUpdate) {
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

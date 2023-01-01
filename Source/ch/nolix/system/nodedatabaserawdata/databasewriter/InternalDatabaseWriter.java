//package declaration
package ch.nolix.system.nodedatabaserawdata.databasewriter;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class InternalDatabaseWriter {
	
	//static attribute
	private static final DatabaseUpdater databaseUpdater = new DatabaseUpdater();
	
	//attribute
	private int saveCount;
	
	//attribute
	private final IMutableNode<?> nodeDatabase;
	
	//multi-attribute
	private final LinkedList<IElementTaker<IMutableNode<?>>> changeActions = new LinkedList<>();
	
	//constructor
	public InternalDatabaseWriter(final IMutableNode<?> nodeDatabase) {
		
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
	public void deleteEntityFromTable(final String tableName, final IEntityHeadDTO entity) {
		addChangeAction(d -> databaseUpdater.deleteEntityFromTable(d, tableName, entity));
	}
	
	//method
	public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
		addChangeAction(d -> databaseUpdater.expectGivenSchemaTimestamp(d, schemaTimestamp));
	}
	
	//method
	public void expectTableContainsEntity(final String tableName, final String entityId) {
		addChangeAction(d -> databaseUpdater.expectTableContainsEntity(d, tableName, entityId));
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
	public void insertEntityIntoTable(final ITableInfo tableInfo, final INewEntityDTO newEntity) {
		addChangeAction(d -> databaseUpdater.insertEntityIntoTable(d, tableInfo, newEntity));
	}
	
	//methods
	public void reset() {
		changeActions.clear();
	}
	
	//method
	public void saveChangesAndReset() {
		try {
			nodeDatabase.setChildNodes(createNodeDatabaseWithChanges().getRefChildNodes());
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
	public void updateEntityOnTable(final ITableInfo tableInfo, IEntityUpdateDTO entityUpdate) {
		addChangeAction(d -> databaseUpdater.updateEntityOnTable(d, tableInfo, entityUpdate));
	}
	
	//method
	private void addChangeAction(final IElementTaker<IMutableNode<?>> changeAction) {
		changeActions.addAtEnd(changeAction);
	}
	
	// method
	private IMutableNode<?> createNodeDatabaseWithChanges() {
		
		final var newNodeDatabase = MutableNode.fromNode(nodeDatabase);
		for (final var ca : changeActions) {
			ca.run(newNodeDatabase);
		}
		
		return newNodeDatabase;
	}
}

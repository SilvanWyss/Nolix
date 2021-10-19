//package declaration
package ch.nolix.system.nodeintermediatedata.datawriter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.system.nodeintermediatedata.tabledefinition.TableDefinition;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordUpdateDTO;

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
	public void deleteRecordFromTable(final String tableName, final IRecordDeletionDTO recordDeletion) {
		changeActions.addAtEnd(d -> databaseUpdater.deleteRecordFromTable(d, tableName, recordDeletion));
	}
	
	//method
	public void insertRecordIntoTable(final TableDefinition tableDefinition, final IRecordDTO record) {
		changeActions.addAtEnd(d -> databaseUpdater.insertRecordIntoTable(d, tableDefinition, record));
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
	public void updateRecordOnTable(final TableDefinition tableDefinition, IRecordUpdateDTO recordUpdate) {
		changeActions.addAtEnd(d -> databaseUpdater.updateRecordOnTable(d, tableDefinition, recordUpdate));
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

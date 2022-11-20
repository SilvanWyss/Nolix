//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public abstract class BaseReference<E extends IEntity<DataImplementation>> extends Property
implements IBaseReference<DataImplementation, E> {
	
	//attribute
	private final String referencedTableName;
	
	//optional attribute
	private Table<E> referencedTable;
	
	//constructor
	protected BaseReference(final String referencedTableName) {
		
		GlobalValidator.assertThat(referencedTableName).thatIsNamed("referenced table name").isNotBlank();
		
		this.referencedTableName = referencedTableName;
	}
	
	//method
	@Override
	public final ITable<DataImplementation, E> getReferencedTable() {
		
		extractReferencedTableIfNotExtracted();
		
		return referencedTable;
	}
	
	//method
	@Override
	public final String getReferencedTableName() {
		return referencedTableName;
	}
	
	//method
	@Override
	public final boolean referencesBack(final IEntity<?> entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBackProperty(final IProperty<?> property) {
		return false;
	}
	
	//method
	private boolean extractedReferencedTable() {
		return (referencedTable != null);
	}
	
	//method
	private void extractReferencedTable() {
		referencedTable = loadReferencedTable();
	}
	
	//method
	private void extractReferencedTableIfNotExtracted() {
		if (!extractedReferencedTable()) {
			extractReferencedTable();
		}
	}
	
	//method
	@SuppressWarnings("unchecked")
	private Table<E> loadReferencedTable() {
		return
		(Table<E>)getParentEntity().getRefParentTable().getParentDatabase().getRefTableByName(getReferencedTableName());
	}
}

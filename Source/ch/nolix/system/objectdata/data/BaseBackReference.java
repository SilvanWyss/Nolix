//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public abstract class BaseBackReference<E extends IEntity<DataImplementation>> extends Property
implements IBaseBackReference<DataImplementation, E> {
	
	//attribute
	private final String backReferencedTableName;
	
	//attribute
	private final String backReferencedPropertyName;
	
	//optional attribute
	private Table<E> backReferencedTable;
	
	//constructor
	protected BaseBackReference(final String backReferencedTableName, final String backReferencedPropertyName) {
		
		GlobalValidator.assertThat(backReferencedTableName).thatIsNamed("back referenced table name").isNotBlank();
		
		GlobalValidator
		.assertThat(backReferencedPropertyName)
		.thatIsNamed("back referenced property name")
		.isNotBlank();
		
		this.backReferencedTableName = backReferencedTableName;
		this.backReferencedPropertyName = backReferencedPropertyName;
	}
	
	//method
	@Override
	public final String getBackReferencedPropertyName() {
		return backReferencedPropertyName;
	}
	
	//method
	@Override
	public final String getBackReferencedTableName() {
		return backReferencedTableName;
	}

	//method
	@Override
	public final ITable<DataImplementation, E> getRefBackReferencedTable() {
		
		extractBackReferencedTableIfNotExtracted();
		
		return backReferencedTable;
	}
	
	//method
	@Override
	public final boolean references(final IEntity<?> entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesUninsertedEntity() {
		return false;
	}
	
	//method
	private boolean extractedBackReferencedTable() {
		return (backReferencedTable != null);
	}
	
	//method
	private void extractBackReferencedTable() {
		backReferencedTable = loadBackReferencedTable();
	}
	
	//method
	private void extractBackReferencedTableIfNotExtracted() {
		if (!extractedBackReferencedTable()) {
			extractBackReferencedTable();
		}
	}
	
	//method
	@SuppressWarnings("unchecked")
	private Table<E> loadBackReferencedTable() {
		return
		(Table<E>)
		getParentEntity().getRefParentTable().getParentDatabase().getRefTableByName(getBackReferencedTableName());
	}
}

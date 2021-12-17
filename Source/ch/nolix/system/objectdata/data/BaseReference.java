//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.objectdataapi.dataapi.IBaseReference;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;

//class
public abstract class BaseReference<E extends IEntity<DataImplementation>> extends Property
implements IBaseReference<DataImplementation, E> {
	
	//attribute
	private final String referencedTableName;
	
	//optional attribute
	private ITable<DataImplementation, E> referencedTable;
	
	//constructor
	public BaseReference(final String referencedTableName) {
		
		Validator.assertThat(referencedTableName).thatIsNamed("referenced table name").isNotBlank();
		
		this.referencedTableName = referencedTableName;
	}
		
	//method
	@Override
	public final boolean canReference(final IEntity<DataImplementation> entity) {
		return
		entity != null
		&& getReferencedTable().getEntityClass() == entity.getClass();
	}
		
	//method
	@Override
	public final boolean canReferenceBack(final IEntity<DataImplementation> entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean canReferenceBackEntityOfType(final Class<IEntity<DataImplementation>> type) {
		return false;
	}
	
	//method
	@Override
	public final boolean canReferenceEntityOfType(final Class<IEntity<DataImplementation>> type) {
		return (getReferencedTable().getEntityClass() == type);
	}
	
	//method
	@Override
	public final ITable<DataImplementation, E> getReferencedTable() {
		
		extractReferencedTableIfNotExtracted();
		
		return referencedTable;
	}
	
	//method
	@Override
	public final boolean referencesBack(final IEntity<DataImplementation> entity) {
		return false;
	}
	
	//method
	private boolean extractedReferencedTable() {
		return (referencedTable != null);
	}
	
	//method
	private void extractReferencedTable() {
		referencedTable = findReferencedTable();
	}
	
	//method
	private void extractReferencedTableIfNotExtracted() {
		if (!extractedReferencedTable()) {
			extractReferencedTable();
		}
	}
	
	//method
	@SuppressWarnings("unchecked")
	private ITable<DataImplementation, E> findReferencedTable() {
		return
		(ITable<DataImplementation, E>)
		getParentEntity().getParentTable().getParentDatabase().getRefTableByName(referencedTableName);
	}
}

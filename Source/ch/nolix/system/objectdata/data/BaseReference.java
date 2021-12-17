//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.common.errorcontrol.validator.Validator;
//own imports
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
	public BaseReference(final String name, final String referencedTableName) {
		
		super(name);
		
		Validator.assertThat(referencedTableName).thatIsNamed("referenced table name").isNotBlank();
		
		this.referencedTableName = referencedTableName;
	}
	
	//method
	@Override
	public boolean canReferenceBack(final IEntity<DataImplementation> entity) {
		return false;
	}
	
	//method
	@Override
	public boolean canReferenceBackEntityOfType(final Class<IEntity<DataImplementation>> type) {
		return false;
	}
	
	//method
	@Override
	public final ITable<DataImplementation, E> getReferencedTable() {
		
		extractReferencedTableIfNotExtracted();
		
		return referencedTable;
	}
	
	//method
	@Override
	public boolean referencesBack(final IEntity<DataImplementation> entity) {
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

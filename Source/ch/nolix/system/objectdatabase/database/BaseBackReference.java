//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.core.container.immutablelist.ImmutableList;
//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public abstract class BaseBackReference<E extends IEntity> extends Property
implements IBaseBackReference<E> {
	
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
	public final ITable<E> getBackReferencedTable() {
		
		extractBackReferencedTableIfNotExtracted();
		
		return backReferencedTable;
	}
	
	//method
	@Override
	public final String getBackReferencedTableName() {
		return backReferencedTableName;
	}
	
	//method
	@Override
	public final IContainer<IProperty> getRefBackReferencingProperties() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public final boolean referencesEntity(final IEntity entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBackProperty(final IProperty property) {
		return
		property != null
		&& belongsToEntity()
		&& property.belongsToEntity()
		&& getBackReferencedTableName().equals(property.getRefParentEntity().getParentTableName())
		&& getBackReferencedPropertyName().equals(property.getName())
		&& referencesBackEntityWithId(property.getRefParentEntity().getId());
	}
	
	//method
	@Override
	public final boolean referencesUninsertedEntity() {
		return false;
	}
	
	//method declaration
	protected abstract boolean referencesBackEntityWithId(String id);
	
	//method
	@Override
	final void internalUpdateProbableBackReferencesWhenIsNew() {
		//Does nothing.
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
		getRefParentEntity().getRefParentTable().getRefParentDatabase().getRefTableByName(getBackReferencedTableName());
	}
}

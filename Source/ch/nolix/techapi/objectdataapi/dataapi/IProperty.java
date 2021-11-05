//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IProperty<P extends IProperty<P>> {
	
	//method declaration
	boolean belongsToEntity();
	
	//method declaration
	boolean canReference(IEntity<?, ?> entity);
	
	//method declaration
	boolean canReferenceBack(IEntity<?, ?> entity);
	
	//method declaration
	boolean canReferenceBackEntityOfType(Class<IEntity<?, ?>> entityType);
	
	//method declaration
	boolean canReferenceEntityOfType(Class<IEntity<?, ?>> entityType);
	
	//method declaration
	IEntity<?, ?> getParentEntity();
	
	//method declaration
	PropertyType getType();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
	
	//method declaration
	boolean references(IEntity<?, ?> entity);
	
	//method declaration
	boolean referencesBack(IEntity<?, ?> entity);
}

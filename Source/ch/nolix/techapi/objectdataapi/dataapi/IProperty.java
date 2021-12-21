//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IProperty<IMPL> extends Named {
	
	//method declaration
	boolean belongsToEntity();
	
	//method declaration
	boolean canReferenceBackEntityOfType(Class<IEntity<IMPL>> type);
	
	//method declaration
	boolean canReferenceEntityOfType(Class<IEntity<IMPL>> type);
	
	//method declaration
	IEntity<IMPL> getParentEntity();
	
	//method declaration
	PropertyType getType();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
	
	//method declaration
	boolean references(IEntity<IMPL> entity);
	
	//method declaration
	boolean referencesBack(IEntity<IMPL> entity);
}

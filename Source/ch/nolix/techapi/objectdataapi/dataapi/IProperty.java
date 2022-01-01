//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.requestapi.EmptinessRequestable;
import ch.nolix.common.requestapi.MandatoryRequestable;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;

//interface
public interface IProperty<IMPL> extends EmptinessRequestable, MandatoryRequestable, Named {
	
	//method declaration
	boolean belongsToEntity();
	
	//method declaration
	IEntity<IMPL> getParentEntity();
	
	//method declaration
	PropertyType getType();
	
	//method declaration
	boolean isLinkedWithRealDatabase();
	
	//method declaration
	boolean references(IEntity<?> entity);
	
	//method declaration
	boolean referencesBack(IEntity<?> entity);
	
	//method declaration
	boolean referencesUninsertedEntity();
}

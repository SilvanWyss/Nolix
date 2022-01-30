//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.requestapi.EmptinessRequestable;
import ch.nolix.core.requestapi.MandatoryRequestable;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

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

//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.IdentifiedByString;
import ch.nolix.common.attributeapi.mandatoryattributeapi.ShortDescripted;
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IEntity<
	E extends IEntity<E, P>,
	P extends IProperty<P>>
extends Deletable, IDatabaseObject, IdentifiedByString, ShortDescripted {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	ITable<?, ?, ?> getParentTable();
	
	//method declaration
	IContainer<P> getRefProperties();
    
    //method declaration
    boolean isBackReferenced();
	
    //method declaration
    boolean isReferenced();
}

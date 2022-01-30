//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.attributeapi.mandatoryattributeapi.IdentifiedByString;
import ch.nolix.core.attributeapi.mandatoryattributeapi.ShortDescripted;
import ch.nolix.core.container.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IEntity<IMPL> extends Deletable, IDatabaseObject, IdentifiedByString, ShortDescripted {
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getParentTable();
    
	//method declaration
	String getParentTableName();
	
	//method declaration
	String getSaveStamp();
	
	//method declaration
	boolean hasSaveStamp();
	
    //method declaration
    boolean isReferencedInPersistedData();
    
	//method declaration
	boolean knowsParentTable();
    
	//method declaration
	IContainer<IProperty<IMPL>> technicalGetRefProperties();
}

//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.IdentifiedByString;
import ch.nolix.common.attributeapi.mandatoryattributeapi.ShortDescripted;
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IEntity<IMPL> extends Deletable, IDatabaseObject, IdentifiedByString, ShortDescripted {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	ITable<IMPL> getParentTable();
	
	//method declaration
	IContainer<IProperty<IMPL>> getRefProperties();
    
	//method declaration
	String getSaveStamp();
	
	//method declaration
	boolean hasSaveStamp();
	
    //method declaration
    boolean isBackReferenced();
	
    //method declaration
    boolean isReferencedInPersistedData();
}

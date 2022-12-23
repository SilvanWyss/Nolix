//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IdentifiedByString;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.ShortDescripted;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IEntity<IMPL> extends Deletable, IDatabaseObject, IdentifiedByString, ShortDescripted {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	String getParentTableName();
	
	//method declaration
	IDatabase<IMPL> getRefParentDatabase();
	
	//method declaration
	ITable<IMPL, IEntity<IMPL>> getRefParentTable();
	
	//method declaration
	String getSaveStamp();
	
	//method declaration
	boolean hasSaveStamp();
	
    //method declaration
    boolean isReferencedInPersistedData();
    
	//method declaration
	IContainer<? extends IProperty<IMPL>> technicalGetRefProperties();
}

//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Identified;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.ShortDescripted;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IEntity extends Deletable, IDatabaseObject, Identified, ShortDescripted {
	
	//method declaration
	boolean belongsToTable();
	
	//method declaration
	String getParentTableName();
	
	//method declaration
	IDatabase getRefParentDatabase();
	
	//method declaration
	ITable<IEntity> getRefParentTable();
	
	//method declaration
	String getSaveStamp();
	
	//method declaration
	boolean hasSaveStamp();
	
	//method declaration
	boolean isReferencedInPersistedData();
	
	//method declaration
	IContainer<? extends IProperty> technicalGetRefProperties();
}

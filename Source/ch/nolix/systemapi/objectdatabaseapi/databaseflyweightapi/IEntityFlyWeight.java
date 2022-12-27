//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;

//interface
public interface IEntityFlyWeight {
	
	//method declaration
	void noteInsert();
	
	//method declaration
	void setInsertAction(IAction insertAction);
}

//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationuniversalapi.IChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IDatabaseEngineAdapter<IMPL> extends IChangeSaver {
	
	//method declaration
	IDatabaseEngine<IMPL> getRefDatabaseEngine();
}

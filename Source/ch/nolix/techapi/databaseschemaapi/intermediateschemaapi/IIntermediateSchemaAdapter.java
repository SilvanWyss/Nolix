//package declaration
package ch.nolix.techapi.databaseschemaapi.intermediateschemaapi;

//interface
public interface IIntermediateSchemaAdapter {
	
	//method declaration
	IIntermediateSchemaReader getRefIntermediateSchemaReader();
	
	//method declaration
	IIntermediateSchemaWriter getRefIntermediateSchemaWriter();
}

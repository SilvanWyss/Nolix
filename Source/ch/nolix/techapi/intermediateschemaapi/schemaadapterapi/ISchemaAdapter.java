//package declaration
package ch.nolix.techapi.intermediateschemaapi.schemaadapterapi;

//interface
public interface ISchemaAdapter {
	
	//method declaration
	ISchemaReader getRefIntermediateSchemaReader();
	
	//method declaration
	ISchemaWriter getRefIntermediateSchemaWriter();
}

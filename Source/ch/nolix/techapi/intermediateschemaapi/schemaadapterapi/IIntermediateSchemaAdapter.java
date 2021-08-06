//package declaration
package ch.nolix.techapi.intermediateschemaapi.schemaadapterapi;

//interface
public interface IIntermediateSchemaAdapter {
	
	//method declaration
	IIntermediateSchemaReader getRefIntermediateSchemaReader();
	
	//method declaration
	IIntermediateSchemaWriter getRefIntermediateSchemaWriter();
}

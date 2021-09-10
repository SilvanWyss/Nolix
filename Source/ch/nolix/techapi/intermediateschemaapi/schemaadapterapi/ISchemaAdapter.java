//package declaration
package ch.nolix.techapi.intermediateschemaapi.schemaadapterapi;

//interface
public interface ISchemaAdapter {
	
	//method declaration
	ISchemaReader getRefSchemaReader();
	
	//method declaration
	ISchemaWriter getRefSchemaWriter();
}

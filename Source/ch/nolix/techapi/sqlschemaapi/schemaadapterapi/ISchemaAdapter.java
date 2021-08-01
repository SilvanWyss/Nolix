//package declaration
package ch.nolix.techapi.sqlschemaapi.schemaadapterapi;

//interface
public interface ISchemaAdapter {
	
	//method declaration
	ISchemaReader getRefSchemaReader();
	
	//method declaration
	ISchemaWriter getRefSchemaWriter();
}

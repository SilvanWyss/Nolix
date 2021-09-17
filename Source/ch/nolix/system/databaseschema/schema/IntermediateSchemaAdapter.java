//package declaration
package ch.nolix.system.databaseschema.schema;

import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class IntermediateSchemaAdapter {
	
	//attribute
	private final IntermediateSchemaReader intermediateSchemaReader;
	private final IntermediateSchemaWriter intermediateSchemaWriter;
	
	//constructor
	public IntermediateSchemaAdapter(final ISchemaAdapter internalIIntermediateSchemaAdapter) {
		
		intermediateSchemaReader =
		new IntermediateSchemaReader(internalIIntermediateSchemaAdapter);
		
		intermediateSchemaWriter =
		new IntermediateSchemaWriter(internalIIntermediateSchemaAdapter);
	}
	
	//method
	public IntermediateSchemaReader getRefIntermediateSchemaReader() {
		return intermediateSchemaReader;
	}
	
	//method
	public IntermediateSchemaWriter getRefIntermediateSchemaWriter() {
		return intermediateSchemaWriter;
	}
}

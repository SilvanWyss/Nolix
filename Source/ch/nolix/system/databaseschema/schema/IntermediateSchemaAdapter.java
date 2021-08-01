//package declaration
package ch.nolix.system.databaseschema.schema;

//own imports
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaAdapter;

//class
final class IntermediateSchemaAdapter {
	
	//attribute
	private final IntermediateSchemaReader intermediateSchemaReader;
	private final IntermediateSchemaWriter intermediateSchemaWriter;
	
	//constructor
	public IntermediateSchemaAdapter(final IIntermediateSchemaAdapter internalIIntermediateSchemaAdapter) {
		
		intermediateSchemaReader =
		new IntermediateSchemaReader(internalIIntermediateSchemaAdapter.getRefIntermediateSchemaReader());
		
		intermediateSchemaWriter =
		new IntermediateSchemaWriter(internalIIntermediateSchemaAdapter.getRefIntermediateSchemaWriter());
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

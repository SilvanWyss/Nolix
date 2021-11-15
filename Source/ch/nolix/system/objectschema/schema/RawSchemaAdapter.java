//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class RawSchemaAdapter {
	
	//attribute
	private final RawSchemaReader rawSchemaReader;
	private final RawSchemaWriter rawSchemaWriter;
	
	//constructor
	public RawSchemaAdapter(final ISchemaAdapter internalRawSchemaAdapter) {
		rawSchemaReader = new RawSchemaReader(internalRawSchemaAdapter);
		rawSchemaWriter = new RawSchemaWriter(internalRawSchemaAdapter);
	}
	
	//method
	public RawSchemaReader getRefIntermediateSchemaReader() {
		return rawSchemaReader;
	}
	
	//method
	public RawSchemaWriter getRefIntermediateSchemaWriter() {
		return rawSchemaWriter;
	}
}

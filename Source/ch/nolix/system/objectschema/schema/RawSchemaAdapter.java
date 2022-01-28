//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

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
	public RawSchemaReader getRefRawSchemaReader() {
		return rawSchemaReader;
	}
	
	//method
	public RawSchemaWriter getRefRawSchemaWriter() {
		return rawSchemaWriter;
	}
}

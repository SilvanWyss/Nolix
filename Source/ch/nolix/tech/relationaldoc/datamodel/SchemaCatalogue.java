//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.system.objectdatabase.schema.Schema;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class SchemaCatalogue {
	
	//constant
	public static final ISchema RELATIONAL_DOC_SCHMEA =
	Schema.withEntityType(AbstractableObject.class, AbstractableField.class);
	
	//constructor
	private SchemaCatalogue() {}
}

//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.system.objectdatabase.schema.Schema;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class SchemaCatalogue {
	
	//constant
	public static final ISchema RELATIONAL_DOC_SCHEMA =
	Schema.withEntityType(
		AbstractableField.class,
		AbstractableObject.class,
		AbstractReferenceContent.class,
		ConcreteReferenceContent.class,
		AbstractValueContent.class,
		ConcreteValueContent.class
	);
	
	//constructor
	private SchemaCatalogue() {}
}

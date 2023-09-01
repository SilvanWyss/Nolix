//pakckage declaration
package ch.nolix.techtest.relationaldoctest.datamodeltest;

//own imports
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.dataadapter.NodeDataAdapter;
import ch.nolix.tech.relationaldoc.datamodel.AbstractableObject;
import ch.nolix.tech.relationaldoc.datamodel.SchemaCatalogue;

//class
public final class AbstractableObjectOnDatabaseTest extends Test {
	
	//method
	@TestCase
	public void testCase_getStoredBaseTypes_whenDoesNotHaveBaseTypes() {
		
		//setup database
		final var database = new MutableNode();
		
		//setup databaseAdapter
		final var databaseAdapter =
		NodeDataAdapter
		.forNodeDatabase(database)
		.withName("test_database")
		.usingSchema(SchemaCatalogue.RELATIONAL_DOC_SCHMEA);
		
		//setup testUnit
		final var testUnit = new AbstractableObject();
		databaseAdapter.insert(testUnit);
		
		//execution
		final var result = testUnit.getStoredBaseTypes();
		
		//verification
		expect(result).isEmpty();
	}
}

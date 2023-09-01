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
		
		//setup part 1: Create database.
		final var database = new MutableNode();
		
		//setup part 2: Create databaseAdapter.
		final var databaseAdapter =
		NodeDataAdapter
		.forNodeDatabase(database)
		.withName("test_database")
		.andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);
		
		//setup part 3: Create testUnit.
		final var testUnit = new AbstractableObject();
		databaseAdapter.insert(testUnit);
		
		//execution
		final var result = testUnit.getStoredBaseTypes();
		
		//verification
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_getStoredBaseTypes_whenHasBaseTypeWithBaseType() {
		
		//setup part 1: Create database.
		final var database = new MutableNode();
		
		//setup part 2: Create databaseAdapter.
		final var databaseAdapter =
		NodeDataAdapter
		.forNodeDatabase(database)
		.withName("test_database")
		.andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);
		
		//setup part 3: Create level1Type.
		final var level1Oject = new AbstractableObject();
		databaseAdapter.insert(level1Oject);
		level1Oject.setName("level_1_object");
		level1Oject.setAsAbstract();
		
		//setup part 4: Create level2Type.
		final var level2Oject = new AbstractableObject();
		databaseAdapter.insert(level2Oject);
		level2Oject.setName("level_2_object");
		level2Oject.setAsAbstract();
		level2Oject.addBaseType(level1Oject);
		
		//setup part 3: Create testUnit.
		final var testUnit = new AbstractableObject();
		databaseAdapter.insert(testUnit);
		testUnit.setName("test_unit");
		testUnit.addBaseType(level2Oject);
		
		//execution
		final var result = testUnit.getStoredBaseTypes();
		
		//verification
		expect(result).containsExactly(level1Oject, level2Oject);
	}
}

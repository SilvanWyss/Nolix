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
	public void testCase_getStoredBaseTypes_whenHasSeveralBaseTypes() {
		
		//setup part 1: Create database.
		final var database = new MutableNode();
		
		//setup part 2: Create databaseAdapter.
		final var databaseAdapter =
		NodeDataAdapter
		.forNodeDatabase(database)
		.withName("test_database")
		.andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);
		
		//setup part 3: Create baseType1.
		final var baseType1 = new AbstractableObject();
		databaseAdapter.insert(baseType1);
		baseType1.setName("base_type_1");
		baseType1.setAsAbstract();
		
		//setup part 4: Create baseType2.
		final var baseType2 = new AbstractableObject();
		databaseAdapter.insert(baseType2);
		baseType2.setName("base_type_2");
		baseType2.setAsAbstract();
		
		//setup part 3: Create testUnit.
		final var testUnit = new AbstractableObject();
		databaseAdapter.insert(testUnit);
		testUnit.setName("test_unit");
		testUnit.addBaseType(baseType1);
		testUnit.addBaseType(baseType2);
		
		//execution
		final var result = testUnit.getStoredBaseTypes();
		
		//verification
		expect(result).containsExactly(baseType1, baseType2);
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
	
	//method
	@TestCase
	public void testCase_getStoredSubTypes_whenDoesNotHaveSubTypes() {
		
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
		final var result = testUnit.getStoredDirectSubTypes();
		
		//verification
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_getStoredSubTypes_whenHasSeveralSubTypes() {
		
		//setup part 1: Create database.
		final var database = new MutableNode();
		
		//setup part 2: Create databaseAdapter.
		final var databaseAdapter =
		NodeDataAdapter
		.forNodeDatabase(database)
		.withName("test_database")
		.andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);
		
		//setup part 3: Create subType1.
		final var subType1 = new AbstractableObject();
		databaseAdapter.insert(subType1);
		subType1.setName("sub_type_1");
		
		//setup part 4: Create subType2.
		final var subType2 = new AbstractableObject();
		databaseAdapter.insert(subType2);
		subType2.setName("sub_type_2");
		
		//setup part 3: Create testUnit.
		final var testUnit = new AbstractableObject();
		databaseAdapter.insert(testUnit);
		testUnit.setName("test_unit");
		testUnit.setAsAbstract();
		subType1.addBaseType(testUnit);
		subType2.addBaseType(testUnit);
		
		//execution
		final var result = testUnit.getStoredSubTypes();
		
		//verification
		expect(result).containsExactly(subType1, subType2);
	}
}

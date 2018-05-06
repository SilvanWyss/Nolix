//package declaration
package ch.nolix.coreTutorial.databaseAdapter;

//own imports
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.ReferenceProperty;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationDatabaseConnector.SpecificationDatabaseConnector;
import ch.nolix.core.specificationDatabaseSchemaConnector.SpecificationDatabaseSchemaConnector;

//class
public final class ReferencePropertyTutorial {

	//main method
	public static void main(String[] args) {
		
		//Creates the cat database.
		final var catDatabase = new StandardSpecification();
		
		//Creates a database schema adapter for the cat database.
		final var catDatabaseSchemaAdapter = new DatabaseSchemaAdapter(
			new SpecificationDatabaseSchemaConnector(catDatabase)
		);
		
		//Applies the schema to the cat database.
		catDatabaseSchemaAdapter
		.initialize()
		.addSchema(new CatDatabaseSchema())
		.saveChanges();
		
		//Created a database adapter for the cat database.
		final var catDatabaseAdapter = new DatabaseAdapter(
			new SpecificationDatabaseConnector(catDatabase),
			new CatDatabaseSchema()
		);
		
		//Fills up some data into the cat database.
			var jon = new Person();
			jon.Name.setValue("Jon");
			catDatabaseAdapter.getRefEntitySet(Person.class).addEntity(jon);
			catDatabaseAdapter.saveChanges();
			
			var garfield = new Cat();
			garfield.Name.setValue("Garfield");
			
			jon =
			catDatabaseAdapter
			.getRefEntitySet(Person.class)
			.getRefEntities()
			.getRefFirst(e -> e.Name.getValue().equals("Jon"));
			
			garfield.Person.set(jon);
			catDatabaseAdapter.getRefEntitySet(Cat.class).addEntity(garfield);
			
			catDatabaseAdapter.saveChanges();
			
		//Changes some data of the cat database.
			garfield =
			catDatabaseAdapter
			.getRefEntitySet(Cat.class)
			.getRefEntities()
			.getRefFirst(c -> c.Name.getValue().equals("Garfield"));
			
			final var garfieldOwner = garfield.Person.getEntity();
			garfieldOwner.Name.setValue("Jon Arbuckle");
			
			catDatabaseAdapter.saveChanges();
			
		//Prints the cat database out to the console.
		System.out.println(catDatabase.toFormatedString());
	}
	
	//private constructor
	private ReferencePropertyTutorial() {}
	
	//inner class
	private static final class CatDatabaseSchema extends Schema {
		
		//constructor
		public CatDatabaseSchema() {
			super(
				Person.class,
				Cat.class
			);
		}
	}

	//inner class
	private static final class Person extends Entity {
		
		//attribute
		public final Property<String> Name = new Property<String>();
	}
	
	//inner class
	private static final class Cat extends Entity {
		
		//attributes
		public final Property<String> Name = new Property<String>();
		public final ReferenceProperty<Person> Person = new ReferenceProperty<Person>();
	}
}

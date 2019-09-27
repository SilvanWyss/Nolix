//package declaration
package ch.nolix.systemTutorial.databaseAdapterTutorial;

import ch.nolix.common.node.Node;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.Property;
import ch.nolix.system.databaseAdapter.Reference;
import ch.nolix.system.databaseAdapter.Schema;
import ch.nolix.system.fileNodeDatabaseAdapter.FileNodeDatabaseAdapter;
import ch.nolix.system.fileNodeDatabaseSchemaAdapter.FileNodeDatabaseSchemaAdapter;

//class
public final class ReferencePropertyTutorial {

	//main method
	public static void main(String[] args) {
		
		//Creates the cat database.
		final var catDatabase = new Node();
		
		//Creates a database schema adapter for the cat database.
		final var catDatabaseSchemaAdapter = new FileNodeDatabaseSchemaAdapter(catDatabase);
		
		//Applies the schema to the cat database.
		catDatabaseSchemaAdapter
		.addSchema(new CatDatabaseSchema())
		.saveChanges();
		
		//Created a database adapter for the cat database.
		final var catDatabaseAdapter = new FileNodeDatabaseAdapter(
			catDatabase,
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
		public final Property<String> Name = new Property<>();
	}
	
	//inner class
	private static final class Cat extends Entity {
		
		//attributes
		public final Property<String> Name = new Property<>();
		public final Reference<Person> Person = new Reference<>();
	}
}

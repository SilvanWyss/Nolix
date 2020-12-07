package ch.nolix.systemtutorial.databaseadaptertutorial;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.system.databaseadapter.Schema;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.Value;
import ch.nolix.system.filenodedatabaseschemaadapter.FileNodeDatabaseSchemaAdapter;
import ch.nolix.system.nodedatabaseadapter.NodeDatabaseAdapter;
import ch.nolix.system.entity.Reference;

public final class ReferencePropertyTutorial {
	
	public static void main(String[] args) {
		
		//Creates the cat database.
		final var catDatabase = new Node();
		
		//Creates a database schema adapter for the cat database.
		final var catDatabaseSchemaAdapter = new FileNodeDatabaseSchemaAdapter(catDatabase);
		
		//Applies the schema to the cat database.
		catDatabaseSchemaAdapter
		//.addSchema(new CatDatabaseSchema()) //TODO: Create IDatabaseSchema.
		.saveChanges();
		
		//Created a database adapter for the cat database.
		final var catDatabaseAdapter = new NodeDatabaseAdapter(
			catDatabase,
			new CatDatabaseSchema()
		);
		
		//Fills up some data into the cat database.
			var jon = new Person();
			jon.name.setValue("Jon");
			catDatabaseAdapter.getRefEntitySet(Person.class).addEntity(jon);
			catDatabaseAdapter.saveChanges();
			
			var garfield = new Cat();
			garfield.name.setValue("Garfield");
			
			jon =
			catDatabaseAdapter
			.getRefEntitySet(Person.class)
			.getRefEntities()
			.getRefFirst(e -> e.name.getValue().equals("Jon"));
			
			garfield.person.set(jon);
			catDatabaseAdapter.getRefEntitySet(Cat.class).addEntity(garfield);
			
			catDatabaseAdapter.saveChanges();
			
		//Changes some data of the cat database.
			garfield =
			catDatabaseAdapter
			.getRefEntitySet(Cat.class)
			.getRefEntities()
			.getRefFirst(c -> c.name.getValue().equals("Garfield"));
			
			final var garfieldOwner = garfield.person.getRefEntity();
			garfieldOwner.name.setValue("Jon Arbuckle");
			
			catDatabaseAdapter.saveChanges();
			
		//Prints the cat database out to the console.
		System.out.println(catDatabase.toFormatedString());
	}
	
	private static final class CatDatabaseSchema extends Schema {
		
		//constructor
		public CatDatabaseSchema() {
			super(
				Person.class,
				Cat.class
			);
		}
	}
	
	private static final class Person extends Entity {
		
		//attribute
		public final Value<String> name = new Value<>();
	}
	
	private static final class Cat extends Entity {
		
		//attributes
		public final Value<String> name = new Value<>();
		public final Reference<Person> person = new Reference<>();
	}
	
	private ReferencePropertyTutorial() {}
}

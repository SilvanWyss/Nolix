package ch.nolix.systemTutorial.databaseAdapterTutorial;

import ch.nolix.common.node.Node;
import ch.nolix.system.databaseAdapter.Schema;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.Value;
import ch.nolix.system.fileNodeDatabaseSchemaAdapter.FileNodeDatabaseSchemaAdapter;
import ch.nolix.system.nodeDatabaseAdapter.NodeDatabaseAdapter;

public final class DatabaseAdapterTutorial {
	
	public static void main(String[] args) {
		
		//Creates petDatabase.
		final var petDatabase = new Node();
		
		//Creates a DatabaseSchemaAdapter for the petDatabase.
		final var petDatabaseSchemaAdapter = new FileNodeDatabaseSchemaAdapter(petDatabase);
		
		//Applies a PetDatabaseSchema to the petDatabase.
		petDatabaseSchemaAdapter
		//.addSchema(new PetDatabaseSchema()) //TODO: Create IDatabaseSchema.
		.saveChanges();
		
		//Creates a DatabaseAdapter for the petDatabase.
		final var petDatabaseAdapter = new NodeDatabaseAdapter(
			petDatabase,
			new PetDatabaseSchema()
		);
		
		//Fills up initial data into the petDatabase.
			final var garfield = new Cat();
			garfield.name.setValue("Garfield");
			garfield.weightInGram.setValue(20000);
			
			final var wallace = new Cat();
			wallace.name.setValue("Wallace");
			wallace.weightInGram.setValue(4500);
			
			final var plasticChicken = new PetToy();
			plasticChicken.name.setValue("Rubber chicken");
			plasticChicken.material.setValue("Plastic");
			
			final var plasticBone = new PetToy();
			plasticBone.name.setValue("Rubber bone");
			plasticBone.material.setValue("Plastic");
			
			petDatabaseAdapter
			.addEntity(garfield, wallace, plasticChicken, plasticBone)
			.saveChanges();
			
		//Prints the pet database out to the console.
		System.out.println(petDatabase.toFormatedString());
	}
	
	private static final class PetDatabaseSchema extends Schema {
		public PetDatabaseSchema() {
			super(Cat.class, PetToy.class);
		}
	}
	
	private static final class Cat extends Entity {
		public final Value<String> name = new Value<>();
		public final Value<Integer> weightInGram = new Value<>();
	}
	
	private static final class PetToy extends Entity {
		public final Value<String> name = new Value<>();
		public final Value<String> material = new Value<>();
	}
	
	private DatabaseAdapterTutorial() {}
}

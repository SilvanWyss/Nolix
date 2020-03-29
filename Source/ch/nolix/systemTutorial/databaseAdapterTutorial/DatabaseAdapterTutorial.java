package ch.nolix.systemTutorial.databaseAdapterTutorial;

//own imports
import ch.nolix.common.node.Node;
import ch.nolix.system.databaseAdapter.Schema;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.ValueProperty;
import ch.nolix.system.fileNodeDatabaseAdapter.FileNodeDatabaseAdapter;
import ch.nolix.system.fileNodeDatabaseSchemaAdapter.FileNodeDatabaseSchemaAdapter;

public final class DatabaseAdapterTutorial {
	
	public static void main(String[] args) {
		
		//Creates petDatabase.
		final var petDatabase = new Node();
		
		//Creates a DatabaseSchemaAdapter for the petDatabase.
		final var petDatabaseSchemaAdapter = new FileNodeDatabaseSchemaAdapter(petDatabase);
		
		//Applies a PetDatabaseSchema to the petDatabase.
		petDatabaseSchemaAdapter
		.addSchema(new PetDatabaseSchema())
		.saveChanges();
		
		//Creates a DatabaseAdapter for the petDatabase.
		final var petDatabaseAdapter = new FileNodeDatabaseAdapter(
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
		public final ValueProperty<String> name = new ValueProperty<>();
		public final ValueProperty<Integer> weightInGram = new ValueProperty<>();
	}
	
	private static final class PetToy extends Entity {
		public final ValueProperty<String> name = new ValueProperty<>();
		public final ValueProperty<String> material = new ValueProperty<>();
	}
	
	private DatabaseAdapterTutorial() {}
}

//package declaration
package ch.nolix.coreTutorial.databaseAdapterTutorial;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNodeDatabaseAdapter.DocumentNodeDatabaseAdapter;
import ch.nolix.core.documentNodeDatabaseSchemaAdapter.DocumentNodeDatabaseSchemaAdapter;

//class
public final class DatabaseAdapterTutorial {

	//main method
	public static void main(String[] args) {
		
		//Creates the pet database.
		final var petDatabase = new DocumentNode();
		
		//Creates a database schema adapter for the pet database.
		final var petDatabaseSchemaAdapter =
		new DocumentNodeDatabaseSchemaAdapter(petDatabase);
		
		//Applies the schema to the pet database.
		petDatabaseSchemaAdapter
		.initialize()
		.addSchema(new PetDatabaseSchema())
		.saveChanges();
			
		//Creates a database adapter for the pet database.
		final var petDatabaseAdapter = new DocumentNodeDatabaseAdapter(
			petDatabase,
			new PetDatabaseSchema()
		);
		
		//Fills up some data into the pet database.
			final var garfield = new Cat();
			garfield.Name.setValue("Garfield");
			garfield.WeightInGram.setValue(20000);
			
			final var wallace = new Cat();
			wallace.Name.setValue("Wallace");
			wallace.WeightInGram.setValue(4500);
			
			petDatabaseAdapter
			.getRefEntitySet(Cat.class)
			.addEntity(
				garfield,
				wallace
			);
			
			final var plasticChicken = new PetToy();
			plasticChicken.Name.setValue("Rubber chicken");
			plasticChicken.Material.setValue("Plastic");
			
			final var plasticBone = new PetToy();
			plasticBone.Name.setValue("Rubber bone");
			plasticBone.Material.setValue("Plastic");
			
			petDatabaseAdapter
			.getRefEntitySet(PetToy.class)
			.addEntity(
				plasticChicken,
				plasticBone
			);
			
			petDatabaseAdapter.saveChanges();
		
		//Changes some data of the pet database.
			petDatabaseAdapter
			.getRefEntitySet(Cat.class)
			.getRefEntities()
			.getRefFirst(c -> c.Name.getValue().equals("Garfield"))
			.WeightInGram
			.setValue(21000);
			
			petDatabaseAdapter.saveChanges();
			
		//Prints the pet database out to the console.
		System.out.println(petDatabase.toFormatedString());
	}
	
	//inner class
	private static final class PetDatabaseSchema extends Schema {
		
		//constructor
		public PetDatabaseSchema() {
			super(
				Cat.class,
				PetToy.class
			);
		}
	}
	
	//private constructor
	private DatabaseAdapterTutorial() {}
	
	//inner class
	private static final class Cat extends Entity {
		
		//attributes
		public final Property<String> Name = new Property<String>();
		public final Property<Integer> WeightInGram = new Property<>();
	}
	
	//inner class
	private static final class PetToy extends Entity {
		
		//attributes
		public final Property<String> Name = new Property<String>();
		public final Property<String> Material = new Property<String>();
	}
}

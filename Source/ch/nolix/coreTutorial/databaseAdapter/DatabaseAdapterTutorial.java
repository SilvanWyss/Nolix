//package declaration
package ch.nolix.coreTutorial.databaseAdapter;

//own imports
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.Property;
import ch.nolix.core.databaseAdapter.Schema;
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationDatabaseConnector.SpecificationDatabaseConnector;
import ch.nolix.core.specificationDatabaseSchemaConnector.SpecificationDatabaseSchemaConnector;

//class
public final class DatabaseAdapterTutorial {

	//main method
	public static void main(String[] args) {
		
		//Creates pet database.
		final Specification petDatabase = new StandardSpecification();
		
		//Applies the schema to the pet database.
			final var petDatabaseSchemaAdapter = new DatabaseSchemaAdapter(
				new SpecificationDatabaseSchemaConnector(petDatabase)
			);
			
			petDatabaseSchemaAdapter
			.initialize()
			.addSchema(new PetDatabaseSchema())
			.saveChanges();
		
		//Fills up some data into the pet database.
			final var petDatabaseAdapter = new DatabaseAdapter(
				new SpecificationDatabaseConnector(petDatabase),
				new PetDatabaseSchema()
			);
				
			final var garfield = new Cat();
			garfield.Name.setValue("Garfield");
			garfield.WeightInGram.setValue(20000);
			
			final var wallace = new Cat();
			wallace.Name.setValue("Wallace");
			wallace.WeightInGram.setValue(4500);
			
			petDatabaseAdapter
			.getRefEntitySet(Cat.class)
			.addEntity(garfield, wallace);
			
			final var plasticChicken = new PetToy();
			plasticChicken.Name.setValue("Rubber chicken");
			plasticChicken.Material.setValue("Plastic");
			
			final var plasticBone = new PetToy();
			plasticBone.Name.setValue("Rubber bone");
			plasticBone.Material.setValue("Plastic");
			
			petDatabaseAdapter
			.getRefEntitySet(PetToy.class)
			.addEntity(plasticChicken, plasticBone);
			
			petDatabaseAdapter.saveChanges();
		
		//Changes some data from the pet database.
			final var petDatabaseAdapter2 = new DatabaseAdapter(
				new SpecificationDatabaseConnector(petDatabase),
				new PetDatabaseSchema()
			);
			
			petDatabaseAdapter2
			.getRefEntitySet(Cat.class)
			.getRefEntities()
			.getRefFirst(c -> c.Name.getValue().equals("Garfield"))
			.WeightInGram
			.setValue(21000);
			
			petDatabaseAdapter2.saveChanges();
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
	
	//private constructor
	private DatabaseAdapterTutorial() {}
}

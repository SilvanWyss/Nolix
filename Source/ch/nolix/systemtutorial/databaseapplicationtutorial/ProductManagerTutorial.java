package ch.nolix.systemtutorial.databaseapplicationtutorial;

import ch.nolix.common.environment.localcomputer.ShellProvider;
import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.system.client.base.Server;
import ch.nolix.system.database.databaseadapter.DatabaseAdapter;
import ch.nolix.system.database.databaseadapter.IDatabaseAdapterCreator;
import ch.nolix.system.database.databaseadapter.Schema;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.Reference;
import ch.nolix.system.database.entity.Value;
import ch.nolix.system.database.nodedatabaseadapter.NodeDatabaseAdapterCreator;
import ch.nolix.system.databaseapplication.main.DatabaseApplication;

public final class ProductManagerTutorial {
	
	public static void main(String[] args) {
		
		//Defines a database file.
		final var databaseFileName = "product_manager_database.spec";
		
		//Creates a NodeDatabaseAdapterCreator
		final var nodeDatabaseAdapterCreator = new NodeDatabaseAdapterCreator(databaseFileName);
		
		//Creates a ProductManager.
		final var productManager = new ProductManager(nodeDatabaseAdapterCreator);
		
		//Creates a NetServer.
		final var netServer = new Server();
		
		//Adds the ProductManager as default Application to the NetServer.
		netServer.addDefaultApplication(productManager);
		
		//Starts a web browser that will connect to the default application of the NetServer.
		ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have clients connected any more.
		Sequencer
		.waitForSeconds(10)
		.andThen()
		.asSoonAsNoMore(netServer::hasClientConnected)
		.runInBackground(netServer::close);
	}
	
	private static final class ProductManager extends DatabaseApplication {
		
		public static final String NAME = "Product Manager";
				
		public ProductManager(final IDatabaseAdapterCreator databaseAdapterCreator) {
			super(NAME, databaseAdapterCreator, new ProductManagerSchema());
		}
		
		@Override
		protected void createInitialData(final DatabaseAdapter databaseAdapter) {
			
			final var imageCategory = new Category("Image");
			final var bookCategory = new Category("Book");
			
			databaseAdapter.addEntity(
				imageCategory,
				bookCategory,
				new UniqueProduct("Guernica", imageCategory),
				new UniqueProduct("Mona Lisa", imageCategory),
				new UniqueProduct("Soft Watch", imageCategory),
				new UniqueProduct("The Starry Night", imageCategory),
				new UniqueProduct("Book of Durrow", bookCategory),
				new UniqueProduct("Book of Kells", bookCategory)
			)
			.saveChanges();
			
			//Simulates a long-lasting initial data creation.
			Sequencer.waitForSeconds(10);
		}
	}
	
	private static final class ProductManagerSchema extends Schema {
		public ProductManagerSchema() {
			super(UniqueProduct.class, Category.class);
		}
	}
	
	private static final class UniqueProduct extends Entity {
		
		private final Value<String> name = new Value<>();
		private final Reference<Category> category = new Reference<>();
		
		@SuppressWarnings("unused")
		public UniqueProduct() {
			extractProperties();
		}
		
		public UniqueProduct(final String name, final Category category) {
			
			extractProperties();
			
			this.name.setValue(name);
			this.category.set(category);
		}
		
		@Override
		public String getShortDescription() {
			return name.getValue();
		}
	}
	
	private static final class Category extends Entity {
		
		private final Value<String> name = new Value<>();
		
		@SuppressWarnings("unused")
		public Category() {
			extractProperties();
		}
		
		public Category(final String name) {
			
			extractProperties();
			
			this.name.setValue(name);
		}
		
		@Override
		public String getShortDescription() {
			return name.getValue();
		}
	}
	
	private ProductManagerTutorial() {}
}

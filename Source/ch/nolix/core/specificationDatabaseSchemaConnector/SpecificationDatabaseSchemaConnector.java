//package declaration
package ch.nolix.core.specificationDatabaseSchemaConnector;

import ch.nolix.core.constants.MultiPascalCaseNameCatalogue;
//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.databaseSchemaAdapter.EntitySet;
import ch.nolix.core.databaseSchemaAdapter.IDatabaseSchemaConnector;
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.validator2.Validator;

//class
public final class SpecificationDatabaseSchemaConnector
implements IDatabaseSchemaConnector<IFunction> {
	
	//attribute
	private final Specification databaseSpecification;
	
	//constructor
	public SpecificationDatabaseSchemaConnector(final Specification databaseSpecification) {
		
		Validator
		.suppose(databaseSpecification)
		.thatIsNamed("database specification")
		.isNotNull();
		
		this.databaseSpecification = databaseSpecification;
	}

	//mehtod
	public IFunction createCommandForAdd(
		final EntityType<Entity> entityType
	) {
		return
		() -> {
		
			final var entitySetSpecification = new StandardSpecification("EntitySet");
			
			entitySetSpecification.addAttribute(
				new StandardSpecification(
					PascalCaseNameCatalogue.NAME,
					entityType.getName()
				)
			);
			
			for (final var c : entityType.getColumns()) {
				entitySetSpecification.addAttribute(c.getSpecification());
			}
			
			entitySetSpecification
			.addAttribute(new StandardSpecification(MultiPascalCaseNameCatalogue.ENTITIES));
			
			databaseSpecification.addAttribute(entitySetSpecification);
		};
	}

	//method
	public IFunction createCommandForDelete(final EntitySet entitySet) {
		return createCommandForDelete(entitySet.getName());
	}
	
	//method
	public IFunction createCommandForDelete(final String name) {
		return
		() -> 
		databaseSpecification
		.removeFirstAttribute(a -> a.hasHeader("EntitySet") && new EntitySetConnector(a).hasName(name));
	}

	//method
	public IFunction createCommandForRename(String name) {
		
		//TODO
		return null;
	}
	
	//method
	public EntitySetConnector getEntitySetConnector(final EntitySet entitySet) {
		return getEntitySetConnector(entitySet.getName());
	}
	
	//method
	public EntitySetConnector getEntitySetConnector(final String name) {
		return getEntitySetConnectors().getRefFirst(esc -> esc.hasName(name));
	}
	
	//method
	public List<EntitySetConnector> getEntitySetConnectors() {
		return
		databaseSpecification
		.getRefAttributes(a -> a.hasHeader("EntitySet"))
		.to(a -> new EntitySetConnector(a));
	}
	
	//method
	public void run(final Iterable<IFunction> commands) {
		commands.forEach(c -> c.run());
	}
}

//package declaration
package ch.nolix.core.specificationDatabaseConnector;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.IDatabaseConnector;
import ch.nolix.core.databaseAdapter.IEntitySetConnector;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntitySet;
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.core.specification.Specification;
import ch.nolix.primitive.validator2.Validator;

//class
public final class SpecificationDatabaseConnector
implements IDatabaseConnector<IFunction>{
	
	//attribute
	private final Specification databaseSpecification;
	private final List<EntitySetConnector<Entity>> entitySetConnectors = new List<EntitySetConnector<Entity>>();
	
	//constructor
	public SpecificationDatabaseConnector(final Specification databaseSpecification) {
		
		Validator
		.suppose(databaseSpecification)
		.thatIsNamed("database specification")
		.isNotNull();
		
		this.databaseSpecification = databaseSpecification;
		
		databaseSpecification
		.getRefAttributes(a -> a.hasHeader("EntitySet"))
		.forEach(a -> entitySetConnectors.addAtEnd(new EntitySetConnector<>(a)));
	}

	//method
	@SuppressWarnings("unchecked")
	public <E extends Entity> IEntitySetConnector<E, IFunction> getEntitySetConnector(
		final EntitySet<E> entitySet
	) {
		return
		(IEntitySetConnector<E, IFunction>)
		entitySetConnectors.getRefFirst(esc -> esc.hasSameNameAs(entitySet));
	}
	
	//method
	public String getName() {
		return
		databaseSpecification
		.getRefFirstAttribute(PascalCaseNameCatalogue.NAME)
		.getOneAttributeAsString();
	}

	//method
	public void run(IContainer<IFunction> commands) {
		commands.forEach(c -> c.run());
	}
}

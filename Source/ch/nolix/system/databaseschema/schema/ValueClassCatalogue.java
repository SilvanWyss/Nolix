//package declaration
package ch.nolix.system.databaseschema.schema;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.document.node.Node;

//class
public final class ValueClassCatalogue {
	
	//constant
	public static final IContainer<Class<?>> VALUE_CLASSES =
	ReadContainer.withElements(BigDecimal.class, Boolean.class, Double.class, Integer.class, Node.class, String.class);
	
	//constructor
	private ValueClassCatalogue() {}
}

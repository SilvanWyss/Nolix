//package declaration
package ch.nolix.system.objectschema.schema;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.document.node.Node;

//class
public final class ValueClassCatalogue {
	
	//constant
	public static final IContainer<Class<?>> VALUE_CLASSES =
	ReadContainer.withElements(BigDecimal.class, Boolean.class, Double.class, Integer.class, Node.class, String.class);
	
	//constructor
	private ValueClassCatalogue() {}
}

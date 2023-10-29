//package declaration
package ch.nolix.system.objectschema.schema;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public final class ValueClassCatalogue {

  //constant
  public static final IContainer<Class<?>> VALUE_CLASSES = ReadContainer.forElement(BigDecimal.class, Boolean.class,
    Double.class, Integer.class, Node.class, String.class);

  //constructor
  private ValueClassCatalogue() {
  }
}

//package declaration
package ch.nolix.core.template;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.document.data.BinaryObject;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.valuecreator.SpecificValueCreator;

//class
public final class ValueFromNodeCreatorCatalogue {
	
	//constant
	public static final SpecificValueCreator<BaseNode, BigDecimal> BIG_DECIMAL_CREATOR =
	new SpecificValueCreator<>(BigDecimal.class, s -> new BigDecimal(s.getHeader()));
	
	//constant
	public static final SpecificValueCreator<BaseNode, BinaryObject> BINARY_OBJECT_CREATOR =
	new SpecificValueCreator<>(BinaryObject.class, s -> BinaryObject.fromString(s.getHeader()));
	
	//constant
	public static final SpecificValueCreator<BaseNode, Boolean> BOOLEAN_CREATOR =
	new SpecificValueCreator<>(Boolean.class, BaseNode::toBoolean);
	
	//constant
	public static final SpecificValueCreator<BaseNode, Integer> INTEGER_CREATOR =
	new SpecificValueCreator<>(Integer.class, BaseNode::toInt);
	
	//constant
	public static final SpecificValueCreator<BaseNode, String> STRING_CREATOR =
	new SpecificValueCreator<>(String.class, BaseNode::getHeader);
	
	//constructor
	private ValueFromNodeCreatorCatalogue() {}
}

//package declaration
package ch.nolix.common.templates;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.data.BinaryObject;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.valueCreator.SpecificValueCreator;

//class
public final class FromNodeSpecificValueCreatorCatalogue {
	
	//constant
	public static final SpecificValueCreator<BaseNode, BigDecimal> BIG_DECIMAL_CREATOR =
	new SpecificValueCreator<>(BigDecimal.class, s -> new BigDecimal(s.getHeader()));
	
	//constant
	public static final SpecificValueCreator<BaseNode, BinaryObject> BINARY_OBJECT_CREATOR =
	new SpecificValueCreator<>(BinaryObject.class, s -> BinaryObject.fromString(s.getHeader()));
	
	//constant
	public static final SpecificValueCreator<BaseNode, Boolean> BOOLEAN_CREATOR =
	new SpecificValueCreator<>(Boolean.class, s -> s.toBoolean());
	
	//constant
	public static final SpecificValueCreator<BaseNode, Integer> INTEGER_CREATOR =
	new SpecificValueCreator<>(Integer.class, s -> s.toInt());
	
	//constant
	public static final SpecificValueCreator<BaseNode, String> STRING_CREATOR =
	new SpecificValueCreator<>(String.class, s -> s.getHeader());
	
	//visibility-reducing constructor
	private FromNodeSpecificValueCreatorCatalogue() {}
}

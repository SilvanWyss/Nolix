//package declaration
package ch.nolix.common.valueCreator;

//Java import
import java.math.BigDecimal;

//own import
import ch.nolix.common.commonTypeHelpers.StringHelper;
import ch.nolix.common.data.BinaryObject;

//class
public final class SpecificValueCreatorCatalogue {
	
	//constant
	public static final SpecificValueCreator<BigDecimal> BIG_DECIMAL_CREATOR =
	new SpecificValueCreator<>(BigDecimal.class, s -> new BigDecimal(s), s -> new BigDecimal(s.getHeader()));
	
	//constant
	public static final SpecificValueCreator<BinaryObject> BINARY_OBJECT_CREATOR =
	new SpecificValueCreator<>(
		BinaryObject.class,
		s -> BinaryObject.fromString(s),
		s -> BinaryObject.fromString(s.getHeader())
	);
	
	//constant
	public static final SpecificValueCreator<Boolean> BOOLEAN_CREATOR =
	new SpecificValueCreator<>(Boolean.class, s -> StringHelper.toBoolean(s), s -> s.toBoolean());
	
	//constant
	public static final SpecificValueCreator<Integer> INTEGER_CREATOR =
	new SpecificValueCreator<>(Integer.class, s -> Integer.valueOf(s), s -> s.toInt());
	
	//constant
	public static final SpecificValueCreator<String> STRING_CREATOR =
	new SpecificValueCreator<>(String.class, s -> s, s -> s.getHeader());
	
	//private constructor
	private SpecificValueCreatorCatalogue() {}
}

//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Indexed;
import ch.nolix.common.attributeapi.mandatoryattributeapi.Prefixed;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
final class State<S extends Enum<S>> implements Indexed, Prefixed {
	
	//static method
	public static <S2 extends Enum<S2>> State<S2> withPrefixAndIndexAndEnumValue(
		final String prefix,
		final int index,
		final S2 enumValue
	) {
		return new State<>(prefix, index, enumValue);
	}
	
	//attributes
	private final String prefix;
	private final int index;
	private final S enumValue;
	
	//constructor
	private State(final String prefix, final int index, final S enumValue) {
		
		Validator.assertThat(prefix).thatIsNamed(LowerCaseCatalogue.PREFIX).isNotBlank();
		Validator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isNotNegative();
		Validator.assertThat(enumValue).thatIsNamed("enum value").isNotNull();
		
		this.prefix = prefix;
		this.index = index;
		this.enumValue = enumValue;
	}
	
	//method
	public S getEnumValue() {
		return enumValue;
	}
	
	//method
	@Override
	public int getIndex() {
		return index;
	}
	
	//method
	@Override
	public String getPrefix() {
		return prefix;
	}
	
	//method
	public boolean hasEnumValue(final S enumValue) {
		return (getEnumValue() == enumValue);
	}
}

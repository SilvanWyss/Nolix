//package declaration
package ch.nolix.system.formatelement;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Indexed;
import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Prefixed;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

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
		
		GlobalValidator.assertThat(prefix).thatIsNamed(LowerCaseCatalogue.PREFIX).isNotBlank();
		GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isNotNegative();
		GlobalValidator.assertThat(enumValue).thatIsNamed("enum value").isNotNull();
		
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

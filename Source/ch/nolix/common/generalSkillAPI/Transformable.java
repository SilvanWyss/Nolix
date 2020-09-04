//package declaration
package ch.nolix.common.generalSkillAPI;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

//interface
/**
 * A {@link Transformable} can generate an {@link Object} that is a transformation of the {@link Transformable}.
 * 
 * @author Silvan Wyss
 * @month 2020-09
 * @lines 60
 * @param <T> The type of the {@link Object}s a {@link Transformable} can be transformed to.
 */
public interface Transformable<T extends Transformable<T>> extends IFluentObject<T> {
	
	//method
	/**
	 * @param <O>
	 * @param transformatorClass
	 * @return a new {@link Object} from the current {@link Transformable}
	 * with the help of an instance of the given transformatorClass.
	 * @throws ArgumentIsNullException if the given transformatorClass is null.
	 */
	public default <O, TC extends  IElementTakerElementGetter<T, O>> O to(final Class<TC> transformatorClass) {
		
		Validator.assertThat(transformatorClass).thatIsNamed("transformator class").isNotNull();
		
		try {
			return transformatorClass.getConstructor().newInstance().getOutput(asConcrete());
		}
		catch (
			final
			InstantiationException
			| IllegalAccessException
			| InvocationTargetException
			| NoSuchMethodException
			exception
		) {
			throw new WrapperException(exception);
		}
	}
	
	//method
	/**
	 * @param <O>
	 * @param transformator
	 * @return a new {@link Object} from the current {@link Transformable} with the help of the given transformator.
	 * @throws ArgumentIsNullException if the given transformator is null.
	 */
	public default <O> O to(final IElementTakerElementGetter<T, O> transformator) {
		
		Validator.assertThat(transformator).thatIsNamed(VariableNameCatalogue.TRANSFORMATOR).isNotNull();
		
		return transformator.getOutput(asConcrete());
	}
}

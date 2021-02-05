//package declaration
package ch.nolix.common.generalskillapi;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.exception.WrapperException;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;

//interface
/**
 * A {@link Transformable} can generate an {@link Object} that is a transformation of the {@link Transformable}.
 * 
 * @author Silvan Wyss
 * @month 2020-09
 * @lines 60
 * @param <T> is the type of the {@link Object}s a {@link Transformable} can be transformed to.
 */
public interface Transformable<T extends Transformable<T>> extends IFluentObject<T> {
	
	//method
	/**
	 * @param transformatorClass
	 * @param <O> is the type of the elements the instances of the given transformatorClass take.
	 * @param <TC> is the type of the elements the instances of the given transformatorClass return.
	 * @return a new {@link Object} from the current {@link Transformable}
	 * with the help of an instance of the given transformatorClass.
	 * @throws ArgumentIsNullException if the given transformatorClass is null.
	 */
	default <O, TC extends  IElementTakerElementGetter<T, O>> O to(final Class<TC> transformatorClass) {
		
		Validator.assertThat(transformatorClass).thatIsNamed("transformator class").isNotNull();
		
		try {
			return transformatorClass.getConstructor().newInstance().getOutput(asConcrete());
		} catch (
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
	 * @param transformator
	 * @param <O> is the type of the elements the given transformator returns.
	 * @return a new {@link Object} from the current {@link Transformable} with the help of the given transformator.
	 * @throws ArgumentIsNullException if the given transformator is null.
	 */
	default <O> O to(final IElementTakerElementGetter<T, O> transformator) {
		
		Validator.assertThat(transformator).thatIsNamed(VariableNameCatalogue.TRANSFORMATOR).isNotNull();
		
		return transformator.getOutput(asConcrete());
	}
}

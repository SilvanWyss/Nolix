//package declaration
package ch.nolix.common.augmentation;

//Java import
import java.lang.reflect.InvocationTargetException;

import ch.nolix.common.errorcontrol.exception.WrapperException;

//Interface
public interface IAugmentable<A extends IAugmentable<A>>  {
	
	//method
	 default <AN extends Augmentation<A>> AN get(final Class<AN> augmentationType) {
		try {
			return augmentationType.getConstructor(getClass()).newInstance(this);
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
}

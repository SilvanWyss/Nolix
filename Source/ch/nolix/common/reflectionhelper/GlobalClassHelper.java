//package declaration
package ch.nolix.common.reflectionhelper;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.exception.WrapperException;

//class
public final class GlobalClassHelper {
	
	//static method
	public static IContainer<Object> getPublicStaticFieldValuesOfClass(final Class<?> pClass) {
		
		final var publicStaticFields = new LinkedList<>();
		
		//Iterates the fields of the given Class.
		for (final var f : pClass.getDeclaredFields()) {
			
			//Handles the case that the current field is static.
			if (FieldHelper.isStatic(f) && FieldHelper.isPublic(f)) {
				try {
					publicStaticFields.addAtEnd(f.get(null));
				} catch (final IllegalAccessException illegalAccessException) {
					throw new WrapperException(illegalAccessException);
				}
			}
		}
		
		return publicStaticFields;
	}
	
	//visibility-reducing constructor
	private GlobalClassHelper() {}
}

//package declaration
package ch.nolix.element.color;

//Java import
import java.lang.reflect.Field;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.reflectionHelper.FieldHelper;

//class
final class ColorsExtractor {
	
	//multi-attribute
	private final IContainer<Color> colors = extractAndGetColors();
	
	//method
	public IContainer<Color> getColors() {
		return colors;
	}
	
	//method
	private boolean declaresColor(final Field field) {
		return FieldHelper.isStaticAndStoresValueOfGivenType(field, Color.class);
	}
	
	//method
	private IContainer<Color> extractAndGetColors() {
		
		final LinkedList<Color> lColors = new LinkedList<>();
		
		for (final var f : Color.class.getDeclaredFields()) {
			if (declaresColor(f)) {
				final Color color = FieldHelper.getValueFromStaticField(f);
				lColors.addAtEnd(color);
			}
		}
		
		return lColors;
	}
}

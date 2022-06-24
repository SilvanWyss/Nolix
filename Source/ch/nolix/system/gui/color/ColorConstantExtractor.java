//package declaration
package ch.nolix.system.gui.color;

//Java imports
import java.lang.reflect.Field;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.reflection.GlobalFieldHelper;
import ch.nolix.coreapi.containerapi.IContainer;

//class
final class ColorConstantExtractor {
	
	//multi-attribute
	private final IContainer<Color> colors = extractAndGetColors();
	
	//method
	public IContainer<Color> getColors() {
		return colors;
	}
	
	//method
	private boolean declaresColor(final Field field) {
		return GlobalFieldHelper.isStaticAndStoresValueOfGivenType(field, Color.class);
	}
	
	//method
	private IContainer<Color> extractAndGetColors() {
		
		final LinkedList<Color> lColors = new LinkedList<>();
		
		for (final var f : Color.class.getDeclaredFields()) {
			if (declaresColor(f)) {
				final Color color = GlobalFieldHelper.getValueFromStaticField(f);
				lColors.addAtEnd(color);
			}
		}
		
		return lColors;
	}
}

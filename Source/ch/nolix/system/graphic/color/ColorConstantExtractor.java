//package declaration
package ch.nolix.system.graphic.color;

//Java imports
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalFieldHelper;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

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

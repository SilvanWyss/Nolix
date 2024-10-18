package ch.nolix.system.graphic.color;

import java.lang.reflect.Field;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

final class ColorConstantExtractor {

  private final IContainer<Color> colors = extractAndGetColors();

  public IContainer<Color> getColors() {
    return colors;
  }

  private boolean declaresColor(final Field field) {
    return GlobalReflectionTool.isStaticAndStoresValueOfGivenType(field, Color.class);
  }

  private IContainer<Color> extractAndGetColors() {

    final LinkedList<Color> lColors = LinkedList.createEmpty();

    for (final var f : Color.class.getDeclaredFields()) {
      if (declaresColor(f)) {
        final Color color = GlobalReflectionTool.getValueFromStaticField(f);
        lColors.addAtEnd(color);
      }
    }

    return lColors;
  }
}

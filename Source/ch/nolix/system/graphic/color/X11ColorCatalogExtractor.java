package ch.nolix.system.graphic.color;

import java.lang.reflect.Field;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.datastructure.pair.Pair;
import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.datastructure.pair.IPair;
import ch.nolix.systemapi.graphic.color.IColor;

/**
 * @author Silvan Wyss
 */
public final class X11ColorCatalogExtractor {
  private static final String STRING_CONSTANT_POSTFIX = "_STRING";

  public IContainer<IPair<String, IColor>> getColorConstantsFromClass(final Class<?> paramClass) {
    final LinkedList<IPair<String, IColor>> x11Colors = LinkedList.createEmpty();
    final var colorStringFields = getColorNameConstantFields(paramClass);
    final var colorFields = getColorFields(paramClass);

    for (final var f : colorStringFields) {
      final var colorStringFieldName = f.getName();
      final var colorField = colorFields.removeAndGetStoredFirst(cf -> colorStringFieldName.startsWith(cf.getName()));

      x11Colors.addAtEnd(
        new Pair<>(ReflectionTool.getValueOfStaticField(f),
          ReflectionTool.getValueOfStaticField(colorField)));
    }

    return x11Colors;
  }

  private boolean declaresColor(final Field field) {
    return //
    ReflectionTool.isStatic(field)
    && ReflectionTool.canStoreValueOfTypeOrSuperType(field, Color.class);
  }

  private boolean declaresColorName(final Field field) {
    return (ReflectionTool.isStatic(field) && field.getName().endsWith(STRING_CONSTANT_POSTFIX));
  }

  private ILinkedList<Field> getColorFields(final Class<?> paramClass) {
    final ILinkedList<Field> colorFields = LinkedList.createEmpty();

    for (final var f : paramClass.getDeclaredFields()) {
      if (declaresColor(f)) {
        colorFields.addAtEnd(f);
      }
    }

    return colorFields;
  }

  private ILinkedList<Field> getColorNameConstantFields(final Class<?> paramClass) {
    final ILinkedList<Field> colorNameConstantFields = LinkedList.createEmpty();

    for (final var f : paramClass.getDeclaredFields()) {
      if (declaresColorName(f)) {
        colorNameConstantFields.addAtEnd(f);
      }
    }

    return colorNameConstantFields;
  }
}

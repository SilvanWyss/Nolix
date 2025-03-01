package ch.nolix.system.graphic.color;

import java.lang.reflect.Field;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.structurecontrol.reflectiontool.ReflectionTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.pairapi.IPair;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;

public final class X11ColorCatalogExtractor {

  private static final String STRING_CONSTANT_POSTFIX = "_STRING";

  public IContainer<IPair<String, IColor>> getColorConstantsFromClass(final Class<?> paramClass) {

    final LinkedList<IPair<String, IColor>> x11Colors = LinkedList.createEmpty();
    final var colorStringFields = getColorNameConstantFields(paramClass);
    final var colorFields = getColorFields(paramClass);

    for (final var csf : colorStringFields) {

      final var colorStringFieldName = csf.getName();
      final var colorField = colorFields.removeAndGetStoredFirst(cf -> colorStringFieldName.startsWith(cf.getName()));

      x11Colors.addAtEnd(
        new Pair<>(ReflectionTool.getValueFromStaticField(csf),
          ReflectionTool.getValueFromStaticField(colorField)));
    }

    return x11Colors;
  }

  private boolean declaresColor(final Field field) {
    return ReflectionTool.isStaticAndStoresValueOfGivenType(field, IColor.class);
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

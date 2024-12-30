package ch.nolix.system.graphic.color;

import java.lang.reflect.Field;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

public final class ColorNameConstantExtractor {

  private static final String STRING_CONSTANT_POSTFIX = "_STRING";

  private final IContainer<Pair<String, Color>> colorNames = extractAndGetColorNames();

  public IContainer<Pair<String, Color>> getWebColorsAndNames() {
    return colorNames;
  }

  private boolean declaresColor(final Field field) {
    return GlobalReflectionTool.isStaticAndStoresValueOfGivenType(field, Color.class);
  }

  private boolean declaresColorName(final Field field) {
    return (GlobalReflectionTool.isStatic(field) && field.getName().endsWith(STRING_CONSTANT_POSTFIX));
  }

  private LinkedList<Pair<String, Color>> extractAndGetColorNames() {

    final LinkedList<Pair<String, Color>> lColorNames = LinkedList.createEmpty();

    final var colorStringFields = getColorNameConnstantFields();
    final var colorFields = getColorFields();

    for (final var csf : colorStringFields) {

      final var colorStringFieldName = csf.getName();

      final var colorField = colorFields.removeAndGetStoredFirst(cf -> colorStringFieldName.startsWith(cf.getName()));

      lColorNames.addAtEnd(
        new Pair<>(GlobalReflectionTool.getValueFromStaticField(csf),
          GlobalReflectionTool.getValueFromStaticField(colorField)));
    }

    return lColorNames;
  }

  private ILinkedList<Field> getColorFields() {

    final ILinkedList<Field> colorFields = LinkedList.createEmpty();

    for (final var f : X11ColorCatalogue.class.getDeclaredFields()) {
      if (declaresColor(f)) {
        colorFields.addAtEnd(f);
      }
    }

    return colorFields;
  }

  private ILinkedList<Field> getColorNameConnstantFields() {

    final ILinkedList<Field> colorNameConstantFields = LinkedList.createEmpty();

    for (final var f : X11ColorCatalogue.class.getDeclaredFields()) {
      if (declaresColorName(f)) {
        colorNameConstantFields.addAtEnd(f);
      }
    }

    return colorNameConstantFields;
  }
}

package ch.nolix.systemapi.guiapi.guiproperty;

import java.awt.Cursor;
import java.util.Locale;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 * @version 2016-06-01
 */
public enum CursorIcon {
  ARROW,
  CROSS,
  EDIT,
  HAND,
  MOVE,
  WAIT;

  /**
   * @param specification
   * @return a new {@link CursorIcon} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link CursorIcon}.
   */
  public static CursorIcon fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader().toUpperCase(Locale.ENGLISH));
  }

  /**
   * @return a CSS value representation of the current {@link CursorIcon}.
   */
  public String toCssValue() {
    return switch (this) {
      case ARROW ->
        "default";
      case CROSS ->
        "crosshair";
      case EDIT ->
        "text";
      case HAND ->
        "pointer";
      case MOVE ->
        "move";
      case WAIT ->
        "wait";
      default ->
        throw new IllegalArgumentException("The current CursorIcon does not represent a CSS value.");
    };
  }

  /**
   * @return a {@link Cursor} representation of the current {@link CursorIcon}.
   */
  public Cursor toCursor() {

    //Enumerates the current cursor icon.
    return switch (this) {
      case ARROW ->
        new Cursor(Cursor.DEFAULT_CURSOR);
      case CROSS ->
        new Cursor(Cursor.CROSSHAIR_CURSOR);
      case EDIT ->
        new Cursor(Cursor.TEXT_CURSOR);
      case HAND ->
        new Cursor(Cursor.HAND_CURSOR);
      case MOVE ->
        new Cursor(Cursor.MOVE_CURSOR);
      case WAIT ->
        new Cursor(Cursor.WAIT_CURSOR);
      default ->
        new Cursor(Cursor.DEFAULT_CURSOR);
    };
  }
}

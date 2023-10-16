//package declaration
package ch.nolix.systemapi.guiapi.canvasapi;

//own imports
import ch.nolix.systemapi.structureapi.IAbsoluteOrRelativeInt;

//interface
public interface Dimensionable<D extends Dimensionable<D>> {

  //method declaration
  IAbsoluteOrRelativeInt getMaxHeight();

  //method declaration
  IAbsoluteOrRelativeInt getMaxWidth();

  //method declaration
  IAbsoluteOrRelativeInt getMinHeight();

  //method declaration
  IAbsoluteOrRelativeInt getMinWidth();

  //method declaration
  boolean hasMaxHeight();

  //method declaration
  boolean hasMaxWidth();

  //method declaration
  boolean hasMinHeight();

  //method declaration
  boolean hasMinWidth();

  //method declaration
  void removeMaxHeight();

  //method declaration
  void removeMaxWidth();

  //method declaration
  void removeMinHeight();

  //method declaration
  void removeMinWidth();

  //method declaration
  D setMaxHeight(int maxHeight);

  //method declaration
  D setMaxHeightInPercentOfViewAreaHeight(double maxHeightInPercentOfViewAreaHeight);

  //method declaration
  D setMaxWidth(int maxWidth);

  //method declaration
  D setMaxWidthInPercentOfViewAreaWidth(double maxWidthInPercentOfViewAreaWidth);

  //method declaration
  D setMinHeight(int minHeight);

  //method declaration
  D setMinHeightInPercentOfViewAreaHeight(double minHeightInPercentOfViewAreaHeight);

  //method declaration
  D setMinWidth(int minWidth);

  //method declaration
  D setMinWidthInPercentOfViewAreaWidth(double minWidthInPercentOfViewAreaWidth);
}

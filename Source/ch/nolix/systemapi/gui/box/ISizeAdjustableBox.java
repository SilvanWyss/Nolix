package ch.nolix.systemapi.gui.box;

import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;

public interface ISizeAdjustableBox<D extends ISizeAdjustableBox<D>> {

  IAbsoluteOrRelativeInt getMaxHeight();

  IAbsoluteOrRelativeInt getMaxWidth();

  IAbsoluteOrRelativeInt getMinHeight();

  IAbsoluteOrRelativeInt getMinWidth();

  boolean hasMaxHeight();

  boolean hasMaxWidth();

  boolean hasMinHeight();

  boolean hasMinWidth();

  void removeMaxHeight();

  void removeMaxWidth();

  void removeMinHeight();

  void removeMinWidth();

  D setMaxHeight(int maxHeight);

  D setMaxHeightInPercentOfViewAreaHeight(double maxHeightInPercentOfViewAreaHeight);

  D setMaxWidth(int maxWidth);

  D setMaxWidthInPercentOfViewAreaWidth(double maxWidthInPercentOfViewAreaWidth);

  D setMinHeight(int minHeight);

  D setMinHeightInPercentOfViewAreaHeight(double minHeightInPercentOfViewAreaHeight);

  D setMinWidth(int minWidth);

  D setMinWidthInPercentOfViewAreaWidth(double minWidthInPercentOfViewAreaWidth);
}

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.base;

import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;

/**
 * @author Silvan Wyss
 * @param <B> the type of a {@link SizeAdjustableRectangle}.
 */
public interface SizeAdjustableRectangle<B extends SizeAdjustableRectangle<B>> {
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

  B setMaxHeight(int maxHeight);

  B setMaxHeightInPercentOfViewAreaHeight(double maxHeightInPercentOfViewAreaHeight);

  B setMaxWidth(int maxWidth);

  B setMaxWidthInPercentOfViewAreaWidth(double maxWidthInPercentOfViewAreaWidth);

  B setMinHeight(int minHeight);

  B setMinHeightInPercentOfViewAreaHeight(double minHeightInPercentOfViewAreaHeight);

  B setMinWidth(int minWidth);

  B setMinWidthInPercentOfViewAreaWidth(double minWidthInPercentOfViewAreaWidth);
}

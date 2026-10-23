/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.base;

/**
 * @author Silvan Wyss
 * @param <B> the type of a {@link SizeAdjustableRectangle}.
 */
public interface SizeAdjustableRectangle<B extends SizeAdjustableRectangle<B>> {
  /**
   * @return the max height of the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the current {@link SizeAdjustableRectangle} does
   *                          not have a max height
   */
  IAbsoluteOrRelativeInt getMaxHeight();

  /**
   * @return the max width of the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the current {@link SizeAdjustableRectangle} does
   *                          not have a max width
   */
  IAbsoluteOrRelativeInt getMaxWidth();

  /**
   * @return the min height of the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the current {@link SizeAdjustableRectangle} does
   *                          not have a min height
   */
  IAbsoluteOrRelativeInt getMinHeight();

  /**
   * @return the min width of the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the current {@link SizeAdjustableRectangle} does
   *                          not have a min width
   */
  IAbsoluteOrRelativeInt getMinWidth();

  /**
   * @return true if the current {@link SizeAdjustableRectangle} has a max height,
   *         false otherwise
   */
  boolean hasMaxHeight();

  /**
   * @return true if the current {@link SizeAdjustableRectangle} has a max width,
   *         false otherwise
   */
  boolean hasMaxWidth();

  /**
   * @return true if the current {@link SizeAdjustableRectangle} has a min height,
   *         false otherwise
   */
  boolean hasMinHeight();

  /**
   * @return true if the current {@link SizeAdjustableRectangle} has a min width,
   *         false otherwise
   */
  boolean hasMinWidth();

  /**
   * Removes the max height of the current {@link SizeAdjustableRectangle}.
   */
  void removeMaxHeight();

  /**
   * Removes the max width of the current {@link SizeAdjustableRectangle}.
   */
  void removeMaxWidth();

  /**
   * Removes the min height of the current {@link SizeAdjustableRectangle}.
   */
  void removeMinHeight();

  /**
   * Removes the min width of the current {@link SizeAdjustableRectangle}.
   */
  void removeMinWidth();

  /**
   * Sets the max height of the current {@link SizeAdjustableRectangle} to the
   * given maxHeight.
   * 
   * @param maxHeight
   * @return the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the given maxHeight is not positive
   */
  B setMaxHeight(int maxHeight);

  /**
   * Sets the max height of the current {@link SizeAdjustableRectangle} to the
   * given maxHeightInPercentOfViewAreaHeight of the height of the view area.
   * 
   * @param maxHeightInPercentOfViewAreaHeight
   * @return the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the given maxHeightInPercentOfViewAreaHeight is
   *                          not positive
   */
  B setMaxHeightInPercentOfViewAreaHeight(double maxHeightInPercentOfViewAreaHeight);

  /**
   * Sets the max width of the current {@link SizeAdjustableRectangle} to the
   * given maxWidth.
   * 
   * @param maxWidth
   * @return the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the given maxWidth is not positive
   */
  B setMaxWidth(int maxWidth);

  /**
   * Sets the max width of the current {@link SizeAdjustableRectangle} to the
   * given maxWidthInPercentOfViewAreaWidth of the width of the view area.
   * 
   * @param maxWidthInPercentOfViewAreaWidth
   * @return the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the given maxWidthInPercentOfViewAreaWidth is not
   *                          positive
   */
  B setMaxWidthInPercentOfViewAreaWidth(double maxWidthInPercentOfViewAreaWidth);

  /**
   * Sets the min height of the current {@link SizeAdjustableRectangle} to the
   * given minHeight.
   * 
   * @param minHeight
   * @return the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the given minHeight is not positive
   */
  B setMinHeight(int minHeight);

  /**
   * Sets the min height of the current {@link SizeAdjustableRectangle} to the
   * given minHeightInPercentOfViewAreaHeight of the height of the view area.
   * 
   * @param minHeightInPercentOfViewAreaHeight
   * @return the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the given minHeightInPercentOfViewAreaHeight is
   *                          not positive
   */
  B setMinHeightInPercentOfViewAreaHeight(double minHeightInPercentOfViewAreaHeight);

  /**
   * Sets the min width of the current {@link SizeAdjustableRectangle} to the
   * given minWidth.
   * 
   * @param minWidth
   * @return the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the given minWidth is not positive
   */
  B setMinWidth(int minWidth);

  /**
   * Sets the min width of the current {@link SizeAdjustableRectangle} to the
   * given minWidthInPercentOfViewAreaWidth of the width of the view area.
   * 
   * @param minWidthInPercentOfViewAreaWidth
   * @return the current {@link SizeAdjustableRectangle}
   * @throws RuntimeException if the given minWidthInPercentOfViewAreaWidth is not
   *                          positive
   */
  B setMinWidthInPercentOfViewAreaWidth(double minWidthInPercentOfViewAreaWidth);
}

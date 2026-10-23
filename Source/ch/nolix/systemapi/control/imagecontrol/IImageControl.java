/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.imagecontrol;

import java.util.function.Consumer;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.systemapi.graphic.image.IMutableImage;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IImageControl extends Clearable, Control<IImageControl, IImageControlStyle> {
  /**
   * @return the alternate text of the current {@link IImageControl}
   */
  String getAlternateText();

  /**
   * @return the image of the current {@link IImageControl}
   */
  IMutableImage<?> getStoredImage();

  /**
   * @return true if the current {@link IImageControl} has a left mouse button
   *         press action, false otherwise
   */
  boolean hasLeftMouseButtonPressAction();

  /**
   * @return true if the current {@link IImageControl} has a left mouse button
   *         release action, false otherwise
   */
  boolean hasLeftMouseButtonReleaseAction();

  /**
   * Removes the left mouse button press action of the current
   * {@link IImageControl}.
   */
  void removeLeftMouseButtonPressAction();

  /**
   * Removes the left mouse button release action of the current
   * {@link IImageControl}.
   */
  void removeLeftMouseButtonReleaseAction();

  /**
   * Sets the given alternateText to the current {@link IImageControl}.
   * 
   * @param alternateText
   * @return the current {@link IImageControl}
   */
  IImageControl setAlternateText(String alternateText);

  /**
   * Sets the given image to the current {@link IImageControl}.
   * 
   * @param image
   * @return the current {@link IImageControl}
   */
  IImageControl setImage(Image image);

  /**
   * Sets the given leftMouseButtonPressAction to the current
   * {@link IImageControl}.
   * 
   * @param leftMouseButtonPressAction
   * @return the current {@link IImageControl}
   */
  IImageControl setLeftMouseButtonPressAction(Runnable leftMouseButtonPressAction);

  /**
   * Sets the given leftMouseButtonPressAction to the current
   * {@link IImageControl}.
   * 
   * @param leftMouseButtonPressAction
   * @return the current {@link IImageControl}
   */
  IImageControl setLeftMouseButtonPressAction(Consumer<IImageControl> leftMouseButtonPressAction);

  /**
   * Sets the given leftMouseButtonReleaseAction to the current
   * {@link IImageControl}.
   * 
   * @param leftMouseButtonReleaseAction
   * @return the current {@link IImageControl}
   */
  IImageControl setLeftMouseButtonRelaseAction(Runnable leftMouseButtonReleaseAction);

  /**
   * Sets the given leftMouseButtonReleaseAction to the current
   * {@link IImageControl}.
   * 
   * @param leftMouseButtonReleaseAction
   * @return the current {@link IImageControl}
   */
  IImageControl setLeftMouseButtonRelaseAction(Consumer<IImageControl> leftMouseButtonReleaseAction);
}

/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.imagecontrol;

import java.util.function.Consumer;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.graphic.image.IMutableImage;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface IImageControl extends Clearable, Control<IImageControl, IImageControlStyle> {
  String getAlternateText();

  IMutableImage<?> getStoredImage();

  boolean hasLeftMouseButtonPressAction();

  boolean hasLeftMouseButtonReleaseAction();

  void removeLeftMouseButtonPressAction();

  void removeLeftMouseButtonReleaseAction();

  IImageControl setAlternateText(String alternateText);

  IImageControl setImage(Image image);

  IImageControl setLeftMouseButtonPressAction(Runnable leftMouseButtonPressAction);

  IImageControl setLeftMouseButtonPressAction(Consumer<IImageControl> leftMouseButtonPressAction);

  IImageControl setLeftMouseButtonRelaseAction(Runnable leftMouseButtonReleaseAction);

  IImageControl setLeftMouseButtonRelaseAction(Consumer<IImageControl> leftMouseButtonReleaseAction);
}

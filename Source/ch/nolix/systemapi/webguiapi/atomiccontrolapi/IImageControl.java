package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IImageControl extends Clearable, IControl<IImageControl, IImageControlStyle> {

  IMutableImage<?> getStoredImage();

  boolean hasLeftMouseButtonPressAction();

  boolean hasLeftMouseButtonReleaseAction();

  void removeLeftMouseButtonPressAction();

  void removeLeftMouseButtonReleaseAction();

  IImageControl setImage(IImage image);

  IImageControl setLeftMouseButtonPressAction(Runnable leftMouseButtonPressAction);

  IImageControl setLeftMouseButtonPressAction(Consumer<IImageControl> leftMouseButtonPressAction);

  IImageControl setLeftMouseButtonRelaseAction(Runnable leftMouseButtonReleaseAction);

  IImageControl setLeftMouseButtonRelaseAction(Consumer<IImageControl> leftMouseButtonReleaseAction);
}

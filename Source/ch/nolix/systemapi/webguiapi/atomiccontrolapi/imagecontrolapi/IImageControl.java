package ch.nolix.systemapi.webguiapi.atomiccontrolapi.imagecontrolapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.state.statemutation.Clearable;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IImageControl extends Clearable, IControl<IImageControl, IImageControlStyle> {

  String getAlternateText();

  IMutableImage<?> getStoredImage();

  boolean hasLeftMouseButtonPressAction();

  boolean hasLeftMouseButtonReleaseAction();

  void removeLeftMouseButtonPressAction();

  void removeLeftMouseButtonReleaseAction();

  IImageControl setAlternateText(String alternateText);

  IImageControl setImage(IImage image);

  IImageControl setLeftMouseButtonPressAction(Runnable leftMouseButtonPressAction);

  IImageControl setLeftMouseButtonPressAction(Consumer<IImageControl> leftMouseButtonPressAction);

  IImageControl setLeftMouseButtonRelaseAction(Runnable leftMouseButtonReleaseAction);

  IImageControl setLeftMouseButtonRelaseAction(Consumer<IImageControl> leftMouseButtonReleaseAction);
}

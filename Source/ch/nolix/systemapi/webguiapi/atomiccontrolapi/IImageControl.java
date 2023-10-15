//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.functionapi.mutationapi.Clearable;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.IMutableImage;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IImageControl extends Clearable, IControl<IImageControl, IImageControlStyle> {

  // method declaration
  IMutableImage<?> getStoredImage();

  // method declaration
  boolean hasLeftMouseButtonPressAction();

  // method declaration
  boolean hasLeftMouseButtonReleaseAction();

  // method declaration
  void removeLeftMouseButtonPressAction();

  // method declaration
  void removeLeftMouseButtonReleaseAction();

  // method declaration
  IImageControl setImage(IImage image);

  // method declaration
  IImageControl setLeftMouseButtonPressAction(Runnable leftMouseButtonPressAction);

  // method declaration
  IImageControl setLeftMouseButtonPressAction(Consumer<IImageControl> leftMouseButtonPressAction);

  // method declaration
  IImageControl setLeftMouseButtonRelaseAction(Runnable leftMouseButtonReleaseAction);

  // method declaration
  IImageControl setLeftMouseButtonRelaseAction(Consumer<IImageControl> leftMouseButtonReleaseAction);
}

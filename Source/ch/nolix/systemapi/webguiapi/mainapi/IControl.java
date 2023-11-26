//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//Java imports
import java.util.Optional;
import java.util.function.Consumer;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programstructureapi.linkingapi.Linkable;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlGetter;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;
import ch.nolix.systemapi.guiapi.canvasapi.Dimensionable;
import ch.nolix.systemapi.guiapi.guiproperty.CursorIcon;
import ch.nolix.systemapi.guiapi.presenceapi.PresenceSettable;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;

//interface
public interface IControl<C extends IControl<C, CS>, CS extends IControlStyle<CS>>
extends
Dimensionable<C>,
IHtmlGetter,
IStylableElement<C>,
IUserInputCell<C>,
Linkable,
PresenceSettable<C> {

  //method declaration
  boolean belongsToControl();

  //method declaration
  boolean belongsToGui();

  //method declaration
  boolean belongsToLayer();

  //method declaration
  C editStyle(Consumer<CS> styleEditor);

  //method declaration
  IContainer<ICssRule> getCssRules();

  //method declaration
  CursorIcon getCursorIcon();

  //method declaration
  String getInternalId();

  //method declaration
  Optional<String> getOptionalJavaScriptUserInputFunction();

  //method declaration
  IControl<?, ?> getStoredChildControlOrNullByInternalId(String internalId);

  //method declaration
  IContainer<IControl<?, ?>> getStoredChildControls();

  //method declaration
  IControl<?, ?> getStoredParentControl();

  //method declaration
  IWebGui<?> getStoredParentGui();

  //method declaration
  ILayer<?> getStoredParentLayer();

  //method declaration
  CS getStoredStyle();

  //method declaration
  boolean hasInternalId(String internalId);

  //method declaration
  void registerHtmlElementEventsAt(ILinkedList<IHtmlElementEvent> list);

  //method declaration
  void runHtmlEvent(String htmlEvent);

  //method declaration
  C setCursorIcon(CursorIcon cursorIcon);

  //method declaration
  void technicalSetParentControl(IControl<?, ?> parentControl);

  //method declaration
  void technicalSetParentLayer(ILayer<?> parentLayer);
}

package ch.nolix.systemapi.webguiapi.mainapi;

import java.util.Optional;
import java.util.function.Consumer;

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

public interface IControl<C extends IControl<C, CS>, CS extends IControlStyle<CS>>
extends
Dimensionable<C>,
IHtmlGetter,
IStylableElement<C>,
IUserInputCell<C>,
Linkable,
PresenceSettable<C> {

  boolean belongsToControl();

  boolean belongsToGui();

  boolean belongsToLayer();

  C editStyle(Consumer<CS> styleEditor);

  IContainer<ICssRule> getCssRules();

  CursorIcon getCursorIcon();

  String getInternalId();

  Optional<String> getOptionalJavaScriptUserInputFunction();

  Optional<IControl<?, ?>> getOptionalStoredChildControlByInternalId(String internalId);

  IContainer<IControl<?, ?>> getStoredChildControls();

  IControl<?, ?> getStoredParentControl();

  IWebGui<?> getStoredParentGui();

  ILayer<?> getStoredParentLayer();

  CS getStoredStyle();

  boolean hasInternalId(String internalId);

  void internalSetParentControl(IControl<?, ?> parentControl);

  void internalSetParentLayer(ILayer<?> parentLayer);

  void registerHtmlElementEventsAt(ILinkedList<IHtmlElementEvent> list);

  void runHtmlEvent(String htmlEvent);

  C setCursorIcon(CursorIcon cursorIcon);
}

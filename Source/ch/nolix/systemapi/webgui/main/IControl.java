package ch.nolix.systemapi.webgui.main;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.objectstructure.linking.Linkable;
import ch.nolix.coreapi.web.css.ICssRule;
import ch.nolix.coreapi.web.html.IHtmlGetter;
import ch.nolix.systemapi.element.style.IStylableElement;
import ch.nolix.systemapi.gui.canvas.Dimensionable;
import ch.nolix.systemapi.gui.guiproperty.CursorIcon;
import ch.nolix.systemapi.gui.presence.PresenceSettable;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;

public interface IControl<C extends IControl<C, S>, S extends IControlStyle<S>>
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

  C editStyle(Consumer<S> styleEditor);

  IContainer<ICssRule> getCssRules();

  CursorIcon getCursorIcon();

  String getInternalId();

  Optional<String> getOptionalJavaScriptUserInputFunction();

  Optional<IControl<?, ?>> getOptionalStoredChildControlByInternalId(String internalId);

  IContainer<IControl<?, ?>> getStoredChildControls();

  IControl<?, ?> getStoredParentControl();

  IWebGui<?> getStoredParentGui();

  ILayer<?> getStoredParentLayer();

  S getStoredStyle();

  boolean hasInternalId(String internalId);

  void internalSetParentControl(IControl<?, ?> parentControl);

  void internalSetParentLayer(ILayer<?> parentLayer);

  void registerHtmlElementEventsAt(ILinkedList<IHtmlElementEvent> list);

  void runHtmlEvent(String htmlEvent);

  C setCursorIcon(CursorIcon cursorIcon);
}

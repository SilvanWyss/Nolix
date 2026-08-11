/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.tabcontainer;

import java.util.function.Consumer;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.control.button.Button;
import ch.nolix.system.control.button.ButtonStyle;
import ch.nolix.system.control.container.AbstractContainer;
import ch.nolix.system.control.horizontalstack.HorizontalStack;
import ch.nolix.system.control.singlecontainer.SingleContainer;
import ch.nolix.system.control.verticalstack.VerticalStack;
import ch.nolix.system.element.valueproperty.MultiValueProperty;
import ch.nolix.system.element.valueproperty.ValueProperty;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.system.webgui.main.ControlParent;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemapi.control.button.IButtonStyle;
import ch.nolix.systemapi.control.tabcontainer.ITabContainer;
import ch.nolix.systemapi.control.tabcontainer.ITabContainerStyle;
import ch.nolix.systemapi.control.tabcontainer.ITabContainerTab;
import ch.nolix.systemapi.control.verticalstack.IVerticalStack;
import ch.nolix.systemapi.gui.guiproperty.HorizontalContentAlignment;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class TabContainer // NOSONAR: A TabContainer has many dependencies.
extends AbstractContainer<ITabContainer, ITabContainerStyle> implements ITabContainer {
  private static final String TAB_HEADER = PascalCaseVariableNameCatalog.TAB;

  private static final String MENU_BUTTON_STYLE_HEADER = "MenuButtonStyle";

  private static final TabContainerHtmlBuilder TAB_CONTAINER_HTML_BUILDER = new TabContainerHtmlBuilder();

  private static final TabContainerCssBuilder TAB_CONTAINER_CSS_BUILDER = new TabContainerCssBuilder();

  /**
   * Registers the {@link TabContainer} class at the {@link ControlFactory}.
   */
  static {
    ControlFactory.registerControlClass(TabContainer.class);
  }

  private final VerticalStack rootVerticalStack = new VerticalStack();

  private final HorizontalStack menuHorizontalStack = new HorizontalStack();

  private final SingleContainer canvasSingleContainer = new SingleContainer();

  private final MultiValueProperty<ITabContainerTab> memberTabs = //
  MultiValueProperty.forElementsOfSameTypeWithNameAndAdderAndValueMapper(
    TAB_HEADER,
    this::addTab,
    TabContainerTab::fromSpecification);

  private final ValueProperty<IButtonStyle> menuButtonStyle = //
  ValueProperty.forElementWithNameAndDefaultValueAndSetterAndValueMapper(
    MENU_BUTTON_STYLE_HEADER,
    new ButtonStyle(),
    this::setMenuButtonStyle,
    ButtonStyle::fromSpecification);

  /**
   * Creates a new {@link TabContainer}.
   */
  public TabContainer() {
    final var controlParent = ControlParent.forControl(this); // NOSONAR: The TabContainer will be fully constructed on time.

    rootVerticalStack.internalSetControlParent(controlParent);
    rootVerticalStack.addControls(menuHorizontalStack, canvasSingleContainer);
    rootVerticalStack.setContentAlignment(HorizontalContentAlignment.LEFT);
    menuHorizontalStack.getStoredStyle().setChildControlMarginForState(ControlState.BASE, 10);

    // A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    getStoredMenuButtonStyle()
      .forStateSetBackgroundColor(ControlState.BASE, X11ColorCatalog.LIGHT_GREY)
      .forStateSetBackgroundColor(ControlState.HOVER, X11ColorCatalog.DARK_GREY)
      .forStateSetBackgroundColor(ControlState.FOCUS, X11ColorCatalog.DARK_GREY);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainer addTab(final ITabContainerTab tab) {
    tab.internalsetParentTabContainer(this);

    if (isEmpty()) {
      tab.select();
    } else if // NOSONAR: This else-if-case is an optimal implementation.
    (tab.isSelected()) {
      for (final var t : getStoredTabs()) {
        if (t.isSelected()) {
          t.unselect();
          break;
        }
      }
    }

    memberTabs.addValue(tab);

    final var menuButton = new Button().setLeftMouseButtonPressAction(tab::select);

    menuButton.removeMinWidth();
    menuButton.getStoredStyle().removeCustomPaddings();
    menuHorizontalStack.addControl(menuButton);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainer addTabs(final ExtendedIterable<ITabContainerTab> tabs) {
    tabs.forEach(this::addTab);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainer addTabs(final ITabContainerTab... tabs) {
    ExtendedIterableView.forArray(tabs).forEach(this::addTab);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    memberTabs.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsSelectedTab() {
    return getStoredTabs().containsMatching(ITabContainerTab::isSelected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredChildControls() {
    return getStoredTabs().to(ITabContainerTab::getStoredRootControl);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredStructureControls() {
    return ImmutableList.withElement(internalGetStoredRootVerticalStack());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainerTab getStoredFirstTabByHeader(final String header) {
    return getStoredTabs().getStoredFirst(t -> t.hasHeader(header));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IButtonStyle getStoredMenuButtonStyle() {
    return menuButtonStyle.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainerTab getStoredSelectedTab() {
    return getStoredTabs().getStoredFirst(ITabContainerTab::isSelected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<ITabContainerTab> getStoredTabs() {
    return memberTabs.getStoredValues();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTabCount() {
    return getStoredTabs().getCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IVerticalStack internalGetStoredRootVerticalStack() {
    return rootVerticalStack;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return getStoredTabs().isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainer onMenuButtonStyle(final Consumer<IButtonStyle> menuButtonStyleEditor) {
    menuButtonStyleEditor.accept(getStoredMenuButtonStyle());

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectFirstTab() {
    getStoredFirstTab().select();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void selectFirstTabByHeader(final String header) {
    final var tab = getStoredFirstTabByHeader(header);

    tab.select();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ITabContainerStyle createStyle() {
    return new TabContainerStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<ITabContainer, ITabContainerStyle> getCssBuilder() {
    return TAB_CONTAINER_CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<ITabContainer> getHtmlBuilder() {
    canvasSingleContainer.clear();

    final var menuButtons = menuHorizontalStack.getStoredChildControls();
    var tabIndex = 1;

    for (final var t : getStoredTabs()) {
      final var headerButton = (IButton) menuButtons.getStoredAtOneBasedIndex(tabIndex);

      headerButton.setText(t.getHeader());
      headerButton.getStoredStyle().resetFromSpecification(getStoredMenuButtonStyle().getSpecification());

      if (t.isSelected() && t.containsAny()) {
        canvasSingleContainer.setControl(t.getStoredRootControl());
      }

      tabIndex++;
    }

    return TAB_CONTAINER_HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetContainer() {
    // Does nothing.
  }

  /**
   * @return the first tab of the current {@link TabContainer}
   * @throws RuntimeException if the current {@link TabContainer} is empty
   */
  private ITabContainerTab getStoredFirstTab() {
    return getStoredTabs().getStoredFirstNonNull();
  }

  /**
   * Sets the given menuButtonStyle to the current {@link TabContainer}.
   * 
   * @param menuButtonStyle
   * @throws RuntimeException if the given menuButtonStyle is null
   */
  private void setMenuButtonStyle(final IButtonStyle menuButtonStyle) {
    this.menuButtonStyle.setValue(menuButtonStyle);
  }
}

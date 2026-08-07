/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.tabcontainer;

import java.util.Optional;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.element.propertieselement.AbstractPropertiesElement;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.property.value.Value;
import ch.nolix.system.webgui.controltool.ControlTool;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.system.webgui.main.ControlParent;
import ch.nolix.systemapi.control.tabcontainer.ITabContainer;
import ch.nolix.systemapi.control.tabcontainer.ITabContainerTab;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class TabContainerTab extends AbstractPropertiesElement implements ITabContainerTab {
  public static final String DEFAULT_HEADER = PascalCaseVariableNameCatalog.HEADER;

  public static final boolean DEFAULT_SELECTION_FLAG = false;

  private static final String HEADER_HEADER = PascalCaseVariableNameCatalog.HEADER;

  private static final String SELECTION_FLAG_HEADER = "Selected";

  private static final String ROOT_CONTROL_HEADER = "RootControl";

  private static final ControlTool CONTROL_TOOL = new ControlTool();

  private ITabContainer optionalParentTabContainer;

  private final Value<String> header = //
  Value.forStringWithNameAndDefaultValueAndSetter(HEADER_HEADER, DEFAULT_HEADER, this::setHeader);

  private final Value<Boolean> selectionFlag = //
  Value.forBooleanWithNameAndDefaultValueAndSetter(
    SELECTION_FLAG_HEADER,
    DEFAULT_SELECTION_FLAG,
    this::setSelectionFlag);

  private final OptionalValue<Control<?, ?>> memberRootControl = //
  OptionalValue.withNameAndSetterAndValueMapperAndSpecificationMapper(
    ROOT_CONTROL_HEADER,
    this::setRootControl,
    ControlFactory::createControlFromSpecification,
    Control::getSpecification);

  public TabContainerTab() {
    optionalParentTabContainer = null;
  }

  public static TabContainerTab fromSpecification(final Node<?> specification) {
    final var tab = new TabContainerTab();
    tab.resetFromSpecification(specification);

    return tab;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean belongsToTabContainer() {
    return optionalParentTabContainer != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    memberRootControl.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeader() {
    return header.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Control<?, ?>> getOptionalStoredControlByInternalId(final String internalId) {
    if (isEmpty()) {
      return Optional.empty();
    }

    final var rootControl = getStoredRootControl();

    if (rootControl.hasInternalId(internalId)) {
      return Optional.of(rootControl);
    }

    return rootControl.getOptionalStoredChildControlByInternalId(internalId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredControls() {
    if (isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return CONTROL_TOOL.getListWithControlAndChildControlsRecursively(getStoredRootControl());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainer getStoredParentTabContainer() {
    assertBelongsToTabContainer();

    return optionalParentTabContainer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Control<?, ?> getStoredRootControl() {
    return memberRootControl.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void internalsetParentTabContainer(final ITabContainer tabContainer) {
    Validator.assertThat(tabContainer).thatIsNamed("tab container").isNotNull();

    assertDoesNotBelongToTabContainer();

    optionalParentTabContainer = tabContainer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return memberRootControl.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSelected() {
    return selectionFlag.getStoredValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    setHeader(DEFAULT_HEADER);
    unselect();
    clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void select() {
    if (!isSelected()) {
      selectWhenIsNotSelected();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainerTab setHeader(final String header) {
    Validator.assertThat(header).thatIsNamed(LowerCaseVariableNameCatalog.HEADER).isNotBlank();

    this.header.setValue(header);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITabContainerTab setRootControl(final Control<?, ?> rootControl) {
    if (belongsToTabContainer()) {
      final var controlParent = ControlParent.forControl(getStoredParentTabContainer());

      rootControl.internalSetControlParent(controlParent);
    }

    memberRootControl.setValue(rootControl);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unselect() {
    if (isSelected()) {
      unselectWhenIsSelected();
    }
  }

  /**
   * @throws RuntimeException if the current {@link TabContainerTab} does not
   *                          belong to a {@link ITabContainer}.
   */
  private void assertBelongsToTabContainer() {
    if (!belongsToTabContainer()) {
      throw ArgumentDoesNotBelongToParentException.forArgument(this);
    }
  }

  /**
   * @throws RuntimeException if the current {@link TabContainerTab} belongs to a
   *                          {@link ITabContainer}.
   */
  private void assertDoesNotBelongToTabContainer() {
    if (belongsToTabContainer()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentTabContainer());
    }
  }

  /**
   * Selects the current {@link TabContainerTab} for the case that the current
   * {@link TabContainerTab} is not selected.
   */
  private void selectWhenIsNotSelected() {
    if (belongsToTabContainer()) {
      getStoredParentTabContainer().getStoredTabs().forEach(ITabContainerTab::unselect);
    }

    selectionFlag.setValue(true);
  }

  /**
   * Selects the current {@link TabContainerTab} if the given selectionFlag is
   * true, un-selects the current {@link TabContainerTab} otherwise.
   * 
   * @param selectionFlag
   */
  private void setSelectionFlag(final boolean selectionFlag) {
    if (selectionFlag) {
      select();
    } else {
      unselect();
    }
  }

  /**
   * Un-selects the current {@link TabContainerTab} for the case that the current
   * {@link TabContainerTab} is selected.
   */
  private void unselectWhenIsSelected() {
    selectionFlag.setValue(false);
  }
}

package ch.nolix.system.webgui.basecontroltool;

import java.util.Locale;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controltool.ControlCssValueTool;
import ch.nolix.system.webgui.cssmapper.CssPropertyMapper;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.cssmapperapi.ICssPropertyMapper;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public abstract class ControlCssBuilder<C extends IControl<C, S>, S extends IControlStyle<S>>
implements IControlCssBuilder<C, S> {

  private static final ICssPropertyMapper CSS_PROPERTY_MAPPER = new CssPropertyMapper();

  private static final ControlCssValueTool CONTROL_CSS_VALUE_TOOL = new ControlCssValueTool();

  @Override
  public final IContainer<ICssRule> createCssRulesForControl(final C control) {

    final ILinkedList<ICssRule> cssRules = LinkedList.createEmpty();
    fillUpAllStateCssRulesIntoList(cssRules, control);
    fillUpBaseCssRulesIntoList(cssRules, control);
    fillUpHoverCssRulesIntoList(cssRules, control);
    fillUpFocusCssRulesIntoList(cssRules, control);

    return cssRules;
  }

  protected abstract void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    C control,
    ILinkedList<? super ICssRule> list);

  protected abstract void fillUpAdditionalCssRulesForControlAndStateIntoList(
    C control,
    ControlState state,
    ILinkedList<? super ICssRule> list);

  protected abstract void fillUpCssPropertiesForControlAndAllStatesIntoList(
    C control,
    ILinkedList<ICssProperty> list);

  protected abstract void fillUpCssPropertiesForControlAndStateIntoList(
    C control,
    ControlState state,
    ILinkedList<ICssProperty> list);

  private void fillUpAllStateCssRulesIntoList(final ILinkedList<ICssRule> list, final C control) {
    final var allStateSelectorPrefix = getCssSelectorForControlAndAllStates(control);
    final ILinkedList<ICssRule> allStateCssRules = LinkedList.createEmpty();
    fillUpCssRulesForControlAndAllStatesIntoList(control, allStateCssRules);
    for (final var r : allStateCssRules) {
      list.addAtEnd(r.withPrefixedSelector(allStateSelectorPrefix + StringCatalogue.SPACE));
    }
  }

  private void fillUpBaseCssRulesIntoList(final ILinkedList<ICssRule> list, final C control) {
    final var baseSelectorPrefix = getCssSelectorForControlAndState(control, ControlState.BASE);
    final ILinkedList<ICssRule> baseCssRules = LinkedList.createEmpty();
    fillUpCssRulesForControlAndStateIntoList(control, ControlState.BASE, baseCssRules);
    for (final var r : baseCssRules) {
      list.addAtEnd(r.withPrefixedSelector(baseSelectorPrefix + StringCatalogue.SPACE));
    }
  }

  private String getCssSelectorForControlAndAllStates(final C control) {
    return ("#" + control.getInternalId());
  }

  private String getCssSelectorForControlAndState(final C control, final ControlState state) {
    return switch (state) {
      case BASE ->
        "#" + control.getInternalId();
      case FOCUS ->
        "#" + control.getInternalId() + ":focus";
      case HOVER ->
        "#" + control.getInternalId() + ":hover";
      default ->
        throw InvalidArgumentException.forArgument(state);
    };
  }

  private void fillUpCssRulesForControlAndAllStatesIntoList(
    final C control,
    final ILinkedList<ICssRule> list) {

    list.addAtEnd(getCssRuleForControlAndAllStates(control));

    fillUpAdditionalCssRulesForControlAndAllStatesIntoList(control, list);
  }

  private void fillUpCssRulesForControlAndStateIntoList(
    final C control,
    final ControlState state,
    final ILinkedList<ICssRule> list) {

    list.addAtEnd(getCssRuleForControlAndState(control, state));

    fillUpAdditionalCssRulesForControlAndStateIntoList(control, state, list);
  }

  private void fillUpFocusCssRulesIntoList(final ILinkedList<ICssRule> list, final C control) {
    final var focusSelectorPrefix = getCssSelectorForControlAndState(control, ControlState.FOCUS);
    final ILinkedList<ICssRule> focusCssRules = LinkedList.createEmpty();
    fillUpCssRulesForControlAndStateIntoList(control, ControlState.FOCUS, focusCssRules);
    for (final var r : focusCssRules) {
      list.addAtEnd(r.withPrefixedSelector(focusSelectorPrefix + StringCatalogue.SPACE));
    }
  }

  private void fillUpHoverCssRulesIntoList(final ILinkedList<ICssRule> list, final C control) {
    final var hoverSelectorPrefix = getCssSelectorForControlAndState(control, ControlState.HOVER);
    final ILinkedList<ICssRule> hoverCssRules = LinkedList.createEmpty();
    fillUpCssRulesForControlAndStateIntoList(control, ControlState.HOVER, hoverCssRules);
    for (final var r : hoverCssRules) {
      list.addAtEnd(r.withPrefixedSelector(hoverSelectorPrefix + StringCatalogue.SPACE));
    }
  }

  private void fillUpMandatoryCssPropertiesForControlAndStateIntoList(
    final C control,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {

    S style = control.getStoredStyle();

    list.addAtEnd(
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_STYLE,
        "solid"),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_LEFT_WIDTH,
        String.valueOf(style.getLeftBorderThicknessWhenHasState(state)) + "px"),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_LEFT_COLOR,
        CONTROL_CSS_VALUE_TOOL.getCssValueFromColor(style.getLeftBorderColorWhenHasState(state))),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.PADDING_LEFT,
        String.valueOf(style.getLeftPaddingWhenHasState(state)) + "px"),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_RIGHT_WIDTH,
        String.valueOf(style.getRightBorderThicknessWhenHasState(state)) + "px"),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_RIGHT_COLOR,
        CONTROL_CSS_VALUE_TOOL.getCssValueFromColor(style.getRightBorderColorWhenHasState(state))),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.PADDING_RIGHT,
        String.valueOf(style.getRightPaddingWhenHasState(state)) + "px"),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_TOP_WIDTH,
        String.valueOf(style.getTopBorderThicknessWhenHasState(state)) + "px"),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_TOP_COLOR,
        CONTROL_CSS_VALUE_TOOL.getCssValueFromColor(style.getTopBorderColorWhenHasState(state))),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.PADDING_TOP,
        String.valueOf(style.getTopPaddingWhenHasState(state)) + "px"),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_BOTTOM_WIDTH,
        String.valueOf(style.getBottomBorderThicknessWhenHasState(state)) + "px"),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.BORDER_BOTTOM_COLOR,
        CONTROL_CSS_VALUE_TOOL.getCssValueFromColor(style.getBottomBorderColorWhenHasState(state))),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.PADDING_BOTTOM,
        String.valueOf(style.getBottomPaddingWhenHasState(state)) + "px"));

    list.addAtEnd(style.getBackgroundWhenHasState(state).toCssProperties());
  }

  private void fillUpOptionalCssPropertiesForControlAndStateIntoList(
    final C control,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {

    S style = control.getStoredStyle();

    if (style.definesWidthForState(state)) {
      list.addAtEnd(
        CssProperty.withNameAndValue(
          CssPropertyNameCatalogue.WIDTH,
          CONTROL_CSS_VALUE_TOOL.getCssValueFromRelativeOrAbsoluteInt(
            style.getWidthForState(state),
            "vw")));
    }

    if (style.definesHeightForState(state)) {
      list.addAtEnd(
        CssProperty.withNameAndValue(
          CssPropertyNameCatalogue.HEIGHT,
          CONTROL_CSS_VALUE_TOOL.getCssValueFromRelativeOrAbsoluteInt(
            style.getHeightForState(state),
            "vh")));
    }
  }

  private IContainer<ICssProperty> getCssPropertiesForControlAndAllStates(final C control) {

    final ILinkedList<ICssProperty> cssPropertiesForBaseState = LinkedList.createEmpty();

    onOwnFillUpCssPropertiesForControlAndAllStatesIntoList(control, cssPropertiesForBaseState);

    return cssPropertiesForBaseState;
  }

  private IContainer<ICssProperty> getCssPropertiesForControlAndState(final C control, final ControlState state) {

    final ILinkedList<ICssProperty> cssProperties = LinkedList.createEmpty();

    onOwnFillUpCssPropertiesForControlAndStateIntoList(control, state, cssProperties);

    return cssProperties;
  }

  private final ICssRule getCssRuleForControlAndAllStates(final C control) {
    return CssRule.withSelectorAndProperties(
      StringCatalogue.EMPTY_STRING,
      getCssPropertiesForControlAndAllStates(control));
  }

  private final ICssRule getCssRuleForControlAndState(final C control, final ControlState state) {
    return CssRule.withSelectorAndProperties(
      StringCatalogue.EMPTY_STRING,
      getCssPropertiesForControlAndState(control, state));
  }

  private ICssProperty getFontWeightCssPropertyForControlAndState(final C control, final ControlState state) {

    final S style = control.getStoredStyle();
    final var boldTextFlag = style.getBoldTextFlagWhenHasState(state);

    if (!boldTextFlag) {
      return CssProperty.withNameAndValue("font-weight", "normal");
    }

    return CssProperty.withNameAndValue("font-weight", "bold");
  }

  private void onOwnFillUpCssPropertiesForControlAndAllStatesIntoList(
    final C control,
    final ILinkedList<ICssProperty> list) {

    switch (control.getPresence()) {
      case VISIBLE:
        //Does nothing. Since presence is configured for all states, the Control will
        //be visible per default.
        break;
      case INVISIBLE:
        list.addAtEnd(CssProperty.withNameAndValue("visibility", "hidden"));
        break;
      case COLLAPSED:
        list.addAtEnd(CssProperty.withNameAndValue("display", "none"));
    }

    if (control.hasMinWidth()) {
      list.addAtEnd(
        CssProperty.withNameAndValue(
          CssPropertyNameCatalogue.MIN_WIDTH,
          CONTROL_CSS_VALUE_TOOL.getCssValueFromRelativeOrAbsoluteInt(
            control.getMinWidth(),
            "vw")));
    }

    if (control.hasMinHeight()) {
      list.addAtEnd(
        CssProperty.withNameAndValue(
          CssPropertyNameCatalogue.MIN_HEIGHT,
          CONTROL_CSS_VALUE_TOOL.getCssValueFromRelativeOrAbsoluteInt(
            control.getMinHeight(),
            "vh")));
    }

    if (control.hasMaxWidth()) {
      list.addAtEnd(
        CssProperty.withNameAndValue(
          CssPropertyNameCatalogue.MAX_WIDTH,
          CONTROL_CSS_VALUE_TOOL.getCssValueFromRelativeOrAbsoluteInt(
            control.getMaxWidth(),
            "vw")));
    }

    if (control.hasMaxHeight()) {
      list.addAtEnd(
        CssProperty.withNameAndValue(
          CssPropertyNameCatalogue.MAX_HEIGHT,
          CONTROL_CSS_VALUE_TOOL.getCssValueFromRelativeOrAbsoluteInt(
            control.getMaxHeight(),
            "vh")));
    }

    list.addAtEnd(
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.CURSOR,
        control.getCursorIcon().toCssValue()));

    fillUpCssPropertiesForControlAndAllStatesIntoList(control, list);
  }

  private void onOwnFillUpCssPropertiesForControlAndStateIntoList(
    final C control,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {

    final S style = control.getStoredStyle();

    final var opacity = style.getOpacityWhenHasState(state);
    if (opacity < 1.0) {
      list.addAtEnd(
        CssProperty.withNameAndValue(
          CssPropertyNameCatalogue.OPACITY,
          opacity));
    }

    list.addAtEnd(
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.COLOR,
        CONTROL_CSS_VALUE_TOOL.getCssValueFromColor(style.getTextColorWhenHasState(state))),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.FONT_FAMILY,
        style.getFontWhenHasState(state).getCode().toLowerCase(Locale.ENGLISH)),
      CssProperty.withNameAndValue(
        CssPropertyNameCatalogue.FONT_SIZE,
        String.valueOf(style.getTextSizeWhenHasState(state)) + "px"),
      getFontWeightCssPropertyForControlAndState(control, state));

    if (style.definesTextLineDecorationForState(state)) {
      list.addAtEnd(CSS_PROPERTY_MAPPER.mapLineDecorationToCssProperty(style.getTextLineDecorationWhenHasState(state)));
    }

    fillUpOptionalCssPropertiesForControlAndStateIntoList(control, state, list);

    fillUpMandatoryCssPropertiesForControlAndStateIntoList(control, state, list);

    fillUpCssPropertiesForControlAndStateIntoList(control, state, list);
  }
}

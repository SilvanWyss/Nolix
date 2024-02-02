//package declaration
package ch.nolix.system.webgui.basecontroltool;

//Java imports
import java.util.Locale;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.webapi.cssapi.CssPropertyNameCatalogue;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.controltool.ControlCssValueTool;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlCssBuilder<C extends IControl<C, CS>, CS extends IControlStyle<CS>>
implements IControlCssBuilder<C, CS> {

  //constant
  private static final ControlCssValueTool CONTROL_CSS_VALUE_TOOL = new ControlCssValueTool();

  //TODO: Refactor this method.
  //method
  @Override
  public final IContainer<ICssRule> createCssRulesForControl(final C control) {

    final var selectorPrefixedCssRules = new LinkedList<ICssRule>();

    final var allStateSelectorPrefix = getCssSelectorForControlAndAllStates(control);
    final var allStateCssRules = new LinkedList<ICssRule>();
    fillUpCssRulesForControlAndAllStatesIntoList(control, allStateCssRules);
    for (final var r : allStateCssRules) {
      selectorPrefixedCssRules.addAtEnd(r.withPrefixedSelector(allStateSelectorPrefix + StringCatalogue.SPACE));
    }

    final var baseSelectorPrefix = getCssSelectorForControlAndState(control, ControlState.BASE);
    final var baseCssRules = new LinkedList<ICssRule>();
    fillUpCssRulesForControlAndStateIntoList(control, ControlState.BASE, baseCssRules);
    for (final var r : baseCssRules) {
      selectorPrefixedCssRules.addAtEnd(r.withPrefixedSelector(baseSelectorPrefix + StringCatalogue.SPACE));
    }

    final var hoverSelectorPrefix = getCssSelectorForControlAndState(control, ControlState.HOVER);
    final var hoverCssRules = new LinkedList<ICssRule>();
    fillUpCssRulesForControlAndStateIntoList(control, ControlState.HOVER, hoverCssRules);
    for (final var r : hoverCssRules) {
      selectorPrefixedCssRules.addAtEnd(r.withPrefixedSelector(hoverSelectorPrefix + StringCatalogue.SPACE));
    }

    final var focusSelectorPrefix = getCssSelectorForControlAndState(control, ControlState.FOCUS);
    final var focusCssRules = new LinkedList<ICssRule>();
    fillUpCssRulesForControlAndStateIntoList(control, ControlState.FOCUS, focusCssRules);
    for (final var r : focusCssRules) {
      selectorPrefixedCssRules.addAtEnd(r.withPrefixedSelector(focusSelectorPrefix + StringCatalogue.SPACE));
    }

    return selectorPrefixedCssRules;
  }

  //method declaration
  protected abstract void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    C control,
    LinkedList<? super ICssRule> list);

  //method declaration
  protected abstract void fillUpAdditionalCssRulesForControlAndStateIntoList(
    C control,
    ControlState state,
    LinkedList<? super ICssRule> list);

  //method declaration
  protected abstract void fillUpCssPropertiesForControlAndAllStatesIntoList(
    C control,
    LinkedList<CssProperty> list);

  //method
  private String getCssSelectorForControlAndAllStates(final C control) {
    return ("#" + control.getInternalId());
  }

  //method
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

  //method declaration
  protected abstract void fillUpCssPropertiesForControlAndStateIntoList(
    C control,
    ControlState state,
    LinkedList<ICssProperty> list);

  //method
  private void fillUpCssRulesForControlAndAllStatesIntoList(
    final C control,
    final LinkedList<ICssRule> list) {

    list.addAtEnd(getCssRuleForControlAndAllStates(control));

    fillUpAdditionalCssRulesForControlAndAllStatesIntoList(control, list);
  }

  //method
  private void fillUpCssRulesForControlAndStateIntoList(
    final C control,
    final ControlState state,
    final LinkedList<ICssRule> list) {

    list.addAtEnd(getCssRuleForControlAndState(control, state));

    fillUpAdditionalCssRulesForControlAndStateIntoList(control, state, list);
  }

  //method
  private void fillUpMandatoryCssPropertiesForControlAndStateIntoList(
    final C control,
    final ControlState state,
    final LinkedList<ICssProperty> list) {

    var style = control.getStoredStyle();

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

  //method
  private void fillUpOptionalCssPropertiesForControlAndStateIntoList(
    final C control,
    final ControlState state,
    final LinkedList<ICssProperty> list) {

    var style = control.getStoredStyle();

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

  //method
  private IContainer<CssProperty> getCssPropertiesForControlAndAllStates(final C control) {

    final var cssPropertiesForBaseState = new LinkedList<CssProperty>();

    onOwnFillUpCssPropertiesForControlAndAllStatesIntoList(control, cssPropertiesForBaseState);

    return cssPropertiesForBaseState;
  }

  //method
  private IContainer<ICssProperty> getCssPropertiesForControlAndState(final C control, final ControlState state) {

    final var cssProperties = new LinkedList<ICssProperty>();

    onOwnFillUpCssPropertiesForControlAndStateIntoList(control, state, cssProperties);

    return cssProperties;
  }

  //method
  private final ICssRule getCssRuleForControlAndAllStates(final C control) {
    return CssRule.withSelectorAndProperties(
      StringCatalogue.EMPTY_STRING,
      getCssPropertiesForControlAndAllStates(control));
  }

  //method
  private final ICssRule getCssRuleForControlAndState(final C control, final ControlState state) {
    return CssRule.withSelectorAndProperties(
      StringCatalogue.EMPTY_STRING,
      getCssPropertiesForControlAndState(control, state));
  }

  //method
  private ICssProperty getFontWeightCssPropertyForControlAndState(final C control, final ControlState state) {

    final var style = control.getStoredStyle();
    final var boldTextFlag = style.getBoldTextFlagWhenHasState(state);

    if (!boldTextFlag) {
      return CssProperty.withNameAndValue("font-weight", "normal");
    }

    return CssProperty.withNameAndValue("font-weight", "bold");
  }

  //method
  private void onOwnFillUpCssPropertiesForControlAndAllStatesIntoList(
    final C control,
    final LinkedList<CssProperty> list) {

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

  //method
  private void onOwnFillUpCssPropertiesForControlAndStateIntoList(
    final C control,
    final ControlState state,
    final LinkedList<ICssProperty> list) {

    final var style = control.getStoredStyle();

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

    fillUpOptionalCssPropertiesForControlAndStateIntoList(control, state, list);

    fillUpMandatoryCssPropertiesForControlAndStateIntoList(control, state, list);

    fillUpCssPropertiesForControlAndStateIntoList(control, state, list);
  }
}

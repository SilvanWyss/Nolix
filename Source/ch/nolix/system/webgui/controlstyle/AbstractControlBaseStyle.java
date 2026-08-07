/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.controlstyle;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.system.element.multistateconfiguration.AbstractMultiStateConfiguration;
import ch.nolix.system.element.multistateconfiguration.CascadingProperty;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.gui.font.Font;
import ch.nolix.systemapi.gui.font.LineDecoration;
import ch.nolix.systemapi.webgui.controlstyle.ControlBaseStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractControlBaseStyle}.
 */
abstract class AbstractControlBaseStyle<C extends ControlBaseStyle<C>>
extends AbstractMultiStateConfiguration<C, ControlState>
implements ControlBaseStyle<C> {
  public static final double DEFAULT_OPACITY = 1.0;

  public static final Font DEFAULT_FONT = Font.ARIAL;

  public static final boolean DEFAULT_BOLD_TEXT_FLAG = false;

  public static final int DEAULT_TEXT_SIZE = 20;

  public static final LineDecoration DEFAULT_TEXT_LINE_DECORATION = LineDecoration.UNDERLINE;

  public static final Color DEFAULT_TEXT_COLOR = X11ColorCatalog.BLACK;

  private final NonCascadingProperty<ControlState, Double> memberOpacity = //
  NonCascadingProperty.withNameAndStateClassAndValueMapperAndSpecificationMapperAndSetterAndDefaultValue(
    ControlHeadStyleAttributeHeaderCatalog.OPACITY_HEADER,
    ControlState.class,
    s -> OpacityHelper.getOpacityFromString(s.getSingleChildNodeHeader()),
    ImmutableNode::withChildNode,
    this::forStateSetOpacity,
    DEFAULT_OPACITY);

  private final CascadingProperty<ControlState, Font> memberFont = //
  CascadingProperty.withNameAndStateClassAndValueMapperAndSpecificationMapperAndDefaultValue(
    ControlHeadStyleAttributeHeaderCatalog.FONT_HEADER,
    ControlState.class,
    Font::fromSpecification,
    ImmutableNode::fromEnum,
    DEFAULT_FONT);

  private final CascadingProperty<ControlState, Boolean> memberBoldTextFlag = //
  CascadingProperty
    .forBooleanWithNameAndStateClassAndDefaultValue(
      ControlHeadStyleAttributeHeaderCatalog.BOLD_TEXT_FLAG_HEADER,
      ControlState.class,
      DEFAULT_BOLD_TEXT_FLAG);

  private final CascadingProperty<ControlState, Integer> memberTextSize = //
  CascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      ControlHeadStyleAttributeHeaderCatalog.TEXT_SIZE_HEADER,
      ControlState.class,
      this::forStateSetTextSize,
      DEAULT_TEXT_SIZE);

  private final CascadingProperty<ControlState, LineDecoration> memberTextLineDecoration = //
  CascadingProperty.withNameAndStateClassAndValueMapperAndSpecificationMapperAndDefaultValue(
    ControlHeadStyleAttributeHeaderCatalog.TEXT_LINE_DECORATION_HEADER,
    ControlState.class,
    LineDecoration::fromSpecification,
    ImmutableNode::fromEnum,
    DEFAULT_TEXT_LINE_DECORATION);

  private final CascadingProperty<ControlState, IColor> memberTextColor = //
  CascadingProperty.withNameAndStateClassAndValueMapperAndSpecificationMapperAndDefaultValue(
    ControlHeadStyleAttributeHeaderCatalog.TEXT_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_TEXT_COLOR);

  protected AbstractControlBaseStyle() {
    super(ControlState.BASE);

    memberTextLineDecoration.setUndefined();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean definesTextLineDecorationForState(final ControlState state) {
    return memberTextLineDecoration.hasValueForState(state);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean getBoldTextFlagWhenHasState(final ControlState state) {
    return memberBoldTextFlag.getValueWhenHasState(state);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Font getFontWhenHasState(final ControlState state) {
    return memberFont.getValueWhenHasState(state);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final double getOpacityWhenHasState(final ControlState state) {
    return memberOpacity.getValueWhenHasState(state);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IColor getTextColorWhenHasState(final ControlState state) {
    return memberTextColor.getValueWhenHasState(state);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LineDecoration getTextLineDecorationWhenHasState(ControlState state) {
    return memberTextLineDecoration.getValueWhenHasState(state);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getTextSizeWhenHasState(final ControlState state) {
    return memberTextSize.getValueWhenHasState(state);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeCustomBoldTextFlags() {
    memberBoldTextFlag.setUndefined();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeCustomFonts() {
    memberFont.setUndefined();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeCustomOpacities() {
    memberOpacity.setUndefined();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeCustomTextColors() {
    memberTextColor.setUndefined();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeCustomTextLineDecorations() {
    memberTextLineDecoration.setUndefined();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeCustomTextSizes() {
    memberTextSize.setUndefined();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C forStateSetBoldTextFlag(final ControlState state, final boolean boldTextFlag) {
    memberBoldTextFlag.setValueForState(state, boldTextFlag);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C forStateSetFont(final ControlState state, final Font font) {
    memberFont.setValueForState(state, font);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C forStateSetOpacity(final ControlState state, final double opacity) {
    Validator.assertThat(opacity).thatIsNamed(LowerCaseVariableNameCatalog.OPACITY).isBetween(0.0, 1.0);

    memberOpacity.setValueForState(state, opacity);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C forStateSetTextColor(final ControlState state, final IColor textColor) {
    memberTextColor.setValueForState(state, textColor);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public C forStateSetTextLineDecoration(final ControlState state, final LineDecoration textLineDecoration) {
    memberTextLineDecoration.setValueForState(state, textLineDecoration);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final C forStateSetTextSize(final ControlState state, final int textSize) {
    Validator.assertThat(textSize).thatIsNamed(LowerCaseVariableNameCatalog.TEXT_SIZE).isPositive();

    memberTextSize.setValueForState(state, textSize);

    return asConcrete();
  }
}

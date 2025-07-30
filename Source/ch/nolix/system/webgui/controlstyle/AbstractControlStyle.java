package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatom.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.multistateconfiguration.ForwardingProperty;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.element.relativevalue.AbsoluteOrRelativeInt;
import ch.nolix.system.element.relativevalue.AbsoluteOrRelativeIntValidator;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.gui.background.Background;
import ch.nolix.systemapi.element.multistateconfiguration.IMultiStateConfiguration;
import ch.nolix.systemapi.element.relativevalue.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.graphic.color.IColor;
import ch.nolix.systemapi.graphic.color.IColorGradient;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.graphic.image.ImageApplication;
import ch.nolix.systemapi.gui.background.IBackground;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public abstract class AbstractControlStyle< //NOSONAR: A ControlStyle has many methods per se.
S extends IControlStyle<S> & IMultiStateConfiguration<S, ControlState>>
extends AbstractControlHeadStyle<S>
implements IControlStyle<S> {

  public static final int DEFAULT_CORNER_RADIUS = 0;

  public static final int DEFAULT_BORDER_THICKNESS = 0;

  public static final IColor DEFAULT_BORDER_COLOR = X11ColorCatalog.BLACK;

  public static final IBackground DEFAULT_BACKGROUND = Background.TRANSPARENT_BACKGROUND;

  public static final int DEFAULT_PADDING = 0;

  private static final String WIDTH_HEADER = PascalCaseVariableCatalog.WIDTH;

  private static final String HEIGHT_HEADER = PascalCaseVariableCatalog.HEIGHT;

  private static final String CORNER_RADIUS_HEADER = "CornerRadius";

  private static final String LEFT_BORDER_THICKNESS_HEADER = "LeftBorderThickness";

  private static final String RIGHT_BORDER_THICKNESS_HEADER = "RightBorderThickness";

  private static final String TOP_BORDER_THICKNESS_HEADER = "TopBorderThickness";

  private static final String BOTTOM_BORDER_THICKNESS_HEADER = "BottomBorderThickness";

  private static final String LEFT_BORDER_COLOR_HEADER = "LeftBorderColor";

  private static final String RIGHT_BORDER_COLOR_HEADER = "RightBorderColor";

  private static final String TOP_BORDER_COLOR_HEADER = "TopBorderColor";

  private static final String BOTTOM_BORDER_COLOR_HEADER = "BottomBorderColor";

  private static final String BACKGROUND_HEADER = "Background";

  private static final String LEFT_PADDING_HEADER = "LeftPadding";

  private static final String RIGHT_PADDING_HEADER = "RightPadding";

  private static final String TOP_PADDING_HEADER = "TopPadding";

  private static final String BOTTOM_PADDING_HEADER = "BottomPadding";

  private static final String BORDER_COLOR_HEADER = "BorderColor";

  private static final String BORDER_THICKNESS_HEADER = "BorderThickness";

  private static final String PADDING_HEADER = "Padding";

  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> width = //
  new NonCascadingProperty //
  <ControlState, IAbsoluteOrRelativeInt>( //NOSONAR: Gradle fails on diamond operators in this case.
    WIDTH_HEADER,
    ControlState.class,
    AbsoluteOrRelativeInt::fromSpecification,
    IAbsoluteOrRelativeInt::getSpecification,
    this::setWidthForState,
    AbsoluteOrRelativeInt.withIntValue(100));

  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> height = //
  new NonCascadingProperty //
  <ControlState, IAbsoluteOrRelativeInt>( //NOSONAR: Gradle fails on diamond operator in this case.
    HEIGHT_HEADER,
    ControlState.class,
    AbsoluteOrRelativeInt::fromSpecification,
    IAbsoluteOrRelativeInt::getSpecification,
    this::setHeightForState,
    AbsoluteOrRelativeInt.withIntValue(100));

  private final NonCascadingProperty<ControlState, Integer> cornerRadius = //
  NonCascadingProperty.forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
    CORNER_RADIUS_HEADER,
    ControlState.class,
    this::setCornerRadiusForState,
    DEFAULT_CORNER_RADIUS);

  private final NonCascadingProperty<ControlState, Integer> leftBorderThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      LEFT_BORDER_THICKNESS_HEADER,
      ControlState.class,
      this::setLeftBorderThicknessForState,
      DEFAULT_BORDER_THICKNESS);

  private final NonCascadingProperty<ControlState, Integer> rightBorderThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      RIGHT_BORDER_THICKNESS_HEADER,
      ControlState.class,
      this::setRightBorderThicknessForState,
      DEFAULT_BORDER_THICKNESS);

  private final NonCascadingProperty<ControlState, Integer> topBorderThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      TOP_BORDER_THICKNESS_HEADER,
      ControlState.class,
      this::setTopBorderThicknessForState,
      DEFAULT_BORDER_THICKNESS);

  private final NonCascadingProperty<ControlState, Integer> bottomBorderThickness = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      BOTTOM_BORDER_THICKNESS_HEADER,
      ControlState.class,
      this::setBottomBorderThicknessForState,
      DEFAULT_BORDER_THICKNESS);

  private final NonCascadingProperty<ControlState, IColor> leftBorderColor = new NonCascadingProperty<>(
    LEFT_BORDER_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_BORDER_COLOR);

  private final NonCascadingProperty<ControlState, IColor> rightBorderColor = new NonCascadingProperty<>(
    RIGHT_BORDER_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_BORDER_COLOR);

  private final NonCascadingProperty<ControlState, IColor> topBorderColor = new NonCascadingProperty<>(
    TOP_BORDER_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_BORDER_COLOR);

  private final NonCascadingProperty<ControlState, IColor> bottomBorderColor = new NonCascadingProperty<>(
    BOTTOM_BORDER_COLOR_HEADER,
    ControlState.class,
    Color::fromSpecification,
    IColor::getSpecification,
    DEFAULT_BORDER_COLOR);

  private final NonCascadingProperty<ControlState, IBackground> background = new NonCascadingProperty<>(
    BACKGROUND_HEADER,
    ControlState.class,
    Background::fromSpecification,
    IBackground::getSpecification,
    DEFAULT_BACKGROUND);

  private final NonCascadingProperty<ControlState, Integer> leftPadding = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      LEFT_PADDING_HEADER,
      ControlState.class,
      this::setLeftPaddingForState,
      DEFAULT_PADDING);

  private final NonCascadingProperty<ControlState, Integer> rightPadding = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      RIGHT_PADDING_HEADER,
      ControlState.class,
      this::setRightPaddingForState,
      DEFAULT_PADDING);

  private final NonCascadingProperty<ControlState, Integer> topPadding = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      TOP_PADDING_HEADER,
      ControlState.class,
      this::setTopPaddingForState,
      DEFAULT_PADDING);

  private final NonCascadingProperty<ControlState, Integer> bottomPadding = NonCascadingProperty
    .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
      BOTTOM_PADDING_HEADER,
      ControlState.class,
      this::setBottomPaddingForState,
      DEFAULT_PADDING);

  private final ForwardingProperty<ControlState, Integer> borderThickness = ForwardingProperty.withNameAndForProperty(
    BORDER_THICKNESS_HEADER,
    leftBorderThickness,
    rightBorderThickness,
    topBorderThickness,
    bottomBorderThickness);

  private final ForwardingProperty<ControlState, IColor> borderColor = ForwardingProperty.withNameAndForProperty(
    BORDER_COLOR_HEADER,
    leftBorderColor,
    rightBorderColor,
    topBorderColor,
    bottomBorderColor);

  private final ForwardingProperty<ControlState, Integer> padding = ForwardingProperty
    .withNameAndForProperty(PADDING_HEADER, leftPadding, rightPadding, topPadding, bottomPadding);

  @Override
  public final boolean definesHeightForState(final ControlState state) {
    return height.hasValueForState(state);
  }

  @Override
  public final boolean definesWidthForState(final ControlState state) {
    return width.hasValueForState(state);
  }

  @Override
  public final IBackground getBackgroundWhenHasState(final ControlState state) {
    return background.getValueWhenHasState(state);
  }

  @Override
  public final IColor getBottomBorderColorWhenHasState(final ControlState state) {
    return bottomBorderColor.getValueWhenHasState(state);
  }

  @Override
  public final int getBottomBorderThicknessWhenHasState(final ControlState state) {
    return bottomBorderThickness.getValueWhenHasState(state);
  }

  @Override
  public final int getBottomPaddingWhenHasState(final ControlState state) {
    return bottomPadding.getValueWhenHasState(state);
  }

  @Override
  public int getCornerRadiusWhenHasState(final ControlState state) {
    return cornerRadius.getValueWhenHasState(state);
  }

  @Override
  public final IColor getLeftBorderColorWhenHasState(final ControlState state) {
    return leftBorderColor.getValueWhenHasState(state);
  }

  @Override
  public final int getLeftBorderThicknessWhenHasState(final ControlState state) {
    return leftBorderThickness.getValueWhenHasState(state);
  }

  @Override
  public final IAbsoluteOrRelativeInt getHeightForState(final ControlState state) {
    return height.getValueWhenHasState(state);
  }

  @Override
  public final int getLeftPaddingWhenHasState(final ControlState state) {
    return leftPadding.getValueWhenHasState(state);
  }

  @Override
  public final IColor getRightBorderColorWhenHasState(final ControlState state) {
    return rightBorderColor.getValueWhenHasState(state);
  }

  @Override
  public final int getRightBorderThicknessWhenHasState(final ControlState state) {
    return rightBorderThickness.getValueWhenHasState(state);
  }

  @Override
  public final int getRightPaddingWhenHasState(final ControlState state) {
    return rightPadding.getValueWhenHasState(state);
  }

  @Override
  public final IColor getTopBorderColorWhenHasState(final ControlState state) {
    return topBorderColor.getValueWhenHasState(state);
  }

  @Override
  public final int getTopBorderThicknessWhenHasState(final ControlState state) {
    return topBorderThickness.getValueWhenHasState(state);
  }

  @Override
  public final int getTopPaddingWhenHasState(final ControlState state) {
    return topPadding.getValueWhenHasState(state);
  }

  @Override
  public final IAbsoluteOrRelativeInt getWidthForState(final ControlState state) {
    return width.getValueWhenHasState(state);
  }

  @Override
  public final void removeCustomBackgrounds() {
    background.setUndefined();
  }

  @Override
  public final void removeCustomBorderColors() {
    borderColor.setUndefined();
  }

  @Override
  public final void removeCustomBorderThicknesses() {
    removeCustomLeftBorderColors();
    removeCustomRightBorderColors();
    removeCustomTopBorderColors();
    removeCustomBottomBorderColors();
  }

  @Override
  public final void removeCustomBottomBorderColors() {
    bottomBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomBottomBorderThicknesses() {
    bottomBorderThickness.setUndefined();
  }

  @Override
  public final void removeCustomBottomPaddings() {
    bottomPadding.setUndefined();
  }

  @Override
  public void removeCustomCornerRadiuses() {
    cornerRadius.setUndefined();
  }

  @Override
  public final void removeCustomHeights() {
    height.setUndefined();
  }

  @Override
  public final void removeCustomLeftBorderColors() {
    leftBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomLeftBorderThicknesses() {
    leftBorderThickness.setUndefined();
  }

  @Override
  public final void removeCustomLeftPaddings() {
    leftPadding.setUndefined();
  }

  @Override
  public final void removeCustomPaddings() {
    removeCustomLeftPaddings();
    removeCustomRightPaddings();
    removeCustomTopPaddings();
    removeCustomBottomPaddings();
  }

  @Override
  public final void removeCustomRightBorderColors() {
    rightBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomRightBorderThicknesses() {
    rightBorderThickness.setUndefined();
  }

  @Override
  public final void removeCustomRightPaddings() {
    rightPadding.setUndefined();
  }

  @Override
  public final void removeCustomTopBorderColors() {
    topBorderColor.setUndefined();
  }

  @Override
  public final void removeCustomTopBorderThicknesses() {
    topBorderThickness.setUndefined();
  }

  @Override
  public final void removeCustomTopPaddings() {
    topPadding.setUndefined();
  }

  @Override
  public final void removeCustomWidths() {
    width.setUndefined();
  }

  @Override
  public final S setBackgroundColorForState(final ControlState state, final IColor backgroundColor) {
    return setBackgroundForState(state, Background.withColor(backgroundColor));
  }

  @Override
  public final S setBackgroundColorGradientForState(
    final ControlState state,
    final IColorGradient backgroundColorGradient) {
    return setBackgroundForState(state, Background.withColorGradient(backgroundColorGradient));
  }

  @Override
  public final S setBackgroundForState(ControlState state, IBackground background) {

    this.background.setValueForState(state, background);

    return asConcrete();
  }

  @Override
  public final S setBackgroundImageForState(
    final ControlState state,
    final IImage backgroundImage,
    final ImageApplication imageApplication) {
    return setBackgroundForState(
      state,
      Background.withImageAndImageApplication(backgroundImage, imageApplication));
  }

  @Override
  public final S setBorderColorForState(final ControlState state, final IColor borderColor) {

    this.borderColor.setValueForState(state, borderColor);

    return asConcrete();
  }

  @Override
  public final S setBorderThicknessForState(final ControlState state, final int borderThickness) {

    this.borderThickness.setValueForState(state, borderThickness);

    return asConcrete();
  }

  @Override
  public final S setBottomBorderColorForState(final ControlState state, final IColor bottomBorderColor) {

    this.bottomBorderColor.setValueForState(state, bottomBorderColor);

    return asConcrete();
  }

  @Override
  public final S setBottomBorderThicknessForState(final ControlState state, final int bottomBorderThickness) {

    Validator.assertThat(bottomBorderThickness).thatIsNamed("bottom border thickness").isNotNegative();

    this.bottomBorderThickness.setValueForState(state, bottomBorderThickness);

    return asConcrete();
  }

  @Override
  public final S setBottomPaddingForState(final ControlState state, final int bottomPadding) {

    Validator.assertThat(bottomPadding).thatIsNamed("bottom padding").isNotNegative();

    this.bottomPadding.setValueForState(state, bottomPadding);

    return asConcrete();
  }

  @Override
  public S setCornerRadiusForState(final ControlState state, final int cornerRadius) {

    Validator.assertThat(cornerRadius).thatIsNamed("corner radius").isNotNegative();

    this.cornerRadius.setValueForState(state, cornerRadius);

    return asConcrete();
  }

  @Override
  public final S setHeightForState(final ControlState state, final int height) {

    setHeightForState(state, AbsoluteOrRelativeInt.withIntValue(height));

    return asConcrete();
  }

  @Override
  public final S setHeightInPercentOfViewAreaForState(
    final ControlState state,
    final double heightInPercentOfViewAreaHeight) {

    setHeightForState(state, AbsoluteOrRelativeInt.withPercentage(heightInPercentOfViewAreaHeight));

    return asConcrete();
  }

  @Override
  public final S setLeftBorderColorForState(final ControlState state, final IColor leftBorderColor) {

    this.leftBorderColor.setValueForState(state, leftBorderColor);

    return asConcrete();
  }

  @Override
  public final S setLeftBorderThicknessForState(final ControlState state, final int leftBorderThickness) {

    Validator.assertThat(leftBorderThickness).thatIsNamed("left border thickness").isNotNegative();

    this.leftBorderThickness.setValueForState(state, leftBorderThickness);

    return asConcrete();
  }

  @Override
  public final S setLeftPaddingForState(final ControlState state, final int leftPadding) {

    Validator.assertThat(leftPadding).thatIsNamed("left padding").isNotNegative();

    this.leftPadding.setValueForState(state, leftPadding);

    return asConcrete();
  }

  @Override
  public final S setPaddingForState(final ControlState state, final int padding) {

    this.padding.setValueForState(state, padding);

    return asConcrete();
  }

  @Override
  public final S setRightBorderColorForState(final ControlState state, final IColor rightBorderColor) {

    this.rightBorderColor.setValueForState(state, rightBorderColor);

    return asConcrete();
  }

  @Override
  public final S setRightBorderThicknessForState(final ControlState state, final int rightBorderThickness) {

    Validator.assertThat(rightBorderThickness).thatIsNamed("right border thickness").isNotNegative();

    this.rightBorderThickness.setValueForState(state, rightBorderThickness);

    return asConcrete();
  }

  @Override
  public final S setRightPaddingForState(final ControlState state, final int rightPadding) {

    Validator.assertThat(rightPadding).thatIsNamed("right padding").isNotNegative();

    this.rightPadding.setValueForState(state, rightPadding);

    return asConcrete();
  }

  @Override
  public final S setTopBorderColorForState(final ControlState state, final IColor topBorderColor) {

    this.topBorderColor.setValueForState(state, topBorderColor);

    return asConcrete();
  }

  @Override
  public final S setTopBorderThicknessForState(final ControlState state, final int topBorderThickness) {

    Validator.assertThat(topBorderThickness).thatIsNamed("top border thickness").isNotNegative();

    this.topBorderThickness.setValueForState(state, topBorderThickness);

    return asConcrete();
  }

  @Override
  public final S setTopPaddingForState(final ControlState state, final int topPadding) {

    Validator.assertThat(topPadding).thatIsNamed("top padding").isNotNegative();

    this.topPadding.setValueForState(state, topPadding);

    return asConcrete();
  }

  @Override
  public final S setWidthForState(final ControlState state, final int width) {

    setWidthForState(state, AbsoluteOrRelativeInt.withIntValue(width));

    return asConcrete();
  }

  @Override
  public S setWidthInPercentOfViewAreaWidthForState(
    final ControlState state,
    final double widthInPercentOfViewAreaWidth) {

    setWidthForState(state, AbsoluteOrRelativeInt.withPercentage(widthInPercentOfViewAreaWidth));

    return asConcrete();
  }

  private void setHeightForState(final ControlState state, final IAbsoluteOrRelativeInt height) {

    AbsoluteOrRelativeIntValidator.assertIsPositive(height);

    this.height.setValueForState(state, height);
  }

  private void setWidthForState(final ControlState state, final IAbsoluteOrRelativeInt width) {

    AbsoluteOrRelativeIntValidator.assertIsPositive(width);

    this.width.setValueForState(state, width);
  }
}

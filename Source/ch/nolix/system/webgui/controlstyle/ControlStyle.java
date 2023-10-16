//package declaration
package ch.nolix.system.webgui.controlstyle;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.multistateconfiguration.ForwardingProperty;
import ch.nolix.system.element.multistateconfiguration.NonCascadingProperty;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.gui.canvas.Background;
import ch.nolix.system.structure.AbsoluteOrRelativeInt;
import ch.nolix.system.structure.AbsoluteOrRelativeIntValidator;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.canvasapi.IBackground;
import ch.nolix.systemapi.structureapi.IAbsoluteOrRelativeInt;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public abstract class ControlStyle<ECS extends IControlStyle<ECS> & IMultiStateConfiguration<ECS, ControlState>>
    extends ControlHeadStyle<ECS>
    implements IControlStyle<ECS> {

  //constant
  public static final int DEFAULT_BORDER_THICKNESS = 0;

  //constant
  public static final IColor DEFAULT_BORDER_COLOR = Color.BLACK;

  //constant
  public static final IBackground DEFAULT_BACKGROUND = Background.TRANSPARENT_BACKGROUND;

  //constant
  public static final int DEFAULT_PADDING = 0;

  //constant
  private static final String WIDTH_HEADER = PascalCaseCatalogue.WIDTH;

  //constant
  private static final String HEIGHT_HEADER = PascalCaseCatalogue.HEIGHT;

  //constant
  private static final String LEFT_BORDER_THICKNESS_HEADER = "LeftBorderThickness";

  //constant
  private static final String RIGHT_BORDER_THICKNESS_HEADER = "RightBorderThickness";

  //constant
  private static final String TOP_BORDER_THICKNESS_HEADER = "TopBorderThickness";

  //constant
  private static final String BOTTOM_BORDER_THICKNESS_HEADER = "BottomBorderThickness";

  //constant
  private static final String LEFT_BORDER_COLOR_HEADER = "LeftBorderColor";

  //constant
  private static final String RIGHT_BORDER_COLOR_HEADER = "RightBorderColor";

  //constant
  private static final String TOP_BORDER_COLOR_HEADER = "TopBorderColor";

  //constant
  private static final String BOTTOM_BORDER_COLOR_HEADER = "BottomBorderColor";

  //constant
  private static final String BACKGROUND_HEADER = "Background";

  //constant
  private static final String LEFT_PADDING_HEADER = "LeftPadding";

  //constant
  private static final String RIGHT_PADDING_HEADER = "RightPadding";

  //constant
  private static final String TOP_PADDING_HEADER = "TopPadding";

  //constant
  private static final String BOTTOM_PADDING_HEADER = "BottomPadding";

  //constant
  private static final String BORDER_COLOR_HEADER = "BorderColor";

  //constant
  private static final String BORDER_THICKNESS_HEADER = "BorderThickness";

  //constant
  private static final String PADDING_HEADER = "Padding";

  //constant
  private static final AbsoluteOrRelativeIntValidator ABSOLUTE_OR_RELATIVE_INT_VALIDATOR = //
      new AbsoluteOrRelativeIntValidator();

  //attribute
  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> width = new NonCascadingProperty<>(
      WIDTH_HEADER,
      ControlState.class,
      AbsoluteOrRelativeInt::fromSpecification,
      IAbsoluteOrRelativeInt::getSpecification,
      this::setWidthForState);

  //attribute
  private final NonCascadingProperty<ControlState, IAbsoluteOrRelativeInt> height = new NonCascadingProperty<>(
      HEIGHT_HEADER,
      ControlState.class,
      AbsoluteOrRelativeInt::fromSpecification,
      IAbsoluteOrRelativeInt::getSpecification,
      this::setHeightForState);

  //attribute
  private final NonCascadingProperty<ControlState, Integer> leftBorderThickness = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          LEFT_BORDER_THICKNESS_HEADER,
          ControlState.class,
          this::setLeftBorderThicknessForState,
          DEFAULT_BORDER_THICKNESS);

  //attribute
  private final NonCascadingProperty<ControlState, Integer> rightBorderThickness = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          RIGHT_BORDER_THICKNESS_HEADER,
          ControlState.class,
          this::setRightBorderThicknessForState,
          DEFAULT_BORDER_THICKNESS);

  //attribute
  private final NonCascadingProperty<ControlState, Integer> topBorderThickness = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          TOP_BORDER_THICKNESS_HEADER,
          ControlState.class,
          this::setTopBorderThicknessForState,
          DEFAULT_BORDER_THICKNESS);

  //attribute
  private final NonCascadingProperty<ControlState, Integer> bottomBorderThickness = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          BOTTOM_BORDER_THICKNESS_HEADER,
          ControlState.class,
          this::setBottomBorderThicknessForState,
          DEFAULT_BORDER_THICKNESS);

  //attribute
  private final NonCascadingProperty<ControlState, IColor> leftBorderColor = new NonCascadingProperty<>(
      LEFT_BORDER_COLOR_HEADER,
      ControlState.class,
      Color::fromSpecification,
      IColor::getSpecification,
      DEFAULT_BORDER_COLOR);

  //attribute
  private final NonCascadingProperty<ControlState, IColor> rightBorderColor = new NonCascadingProperty<>(
      RIGHT_BORDER_COLOR_HEADER,
      ControlState.class,
      Color::fromSpecification,
      IColor::getSpecification,
      DEFAULT_BORDER_COLOR);

  //attribute
  private final NonCascadingProperty<ControlState, IColor> topBorderColor = new NonCascadingProperty<>(
      TOP_BORDER_COLOR_HEADER,
      ControlState.class,
      Color::fromSpecification,
      IColor::getSpecification,
      DEFAULT_BORDER_COLOR);

  //attribute
  private final NonCascadingProperty<ControlState, IColor> bottomBorderColor = new NonCascadingProperty<>(
      BOTTOM_BORDER_COLOR_HEADER,
      ControlState.class,
      Color::fromSpecification,
      IColor::getSpecification,
      DEFAULT_BORDER_COLOR);

  //attribute
  private final NonCascadingProperty<ControlState, IBackground> background = new NonCascadingProperty<>(
      BACKGROUND_HEADER,
      ControlState.class,
      Background::fromSpecification,
      IBackground::getSpecification,
      DEFAULT_BACKGROUND);

  //attribute
  private final NonCascadingProperty<ControlState, Integer> leftPadding = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          LEFT_PADDING_HEADER,
          ControlState.class,
          this::setLeftPaddingForState,
          DEFAULT_PADDING);

  //attribute
  private final NonCascadingProperty<ControlState, Integer> rightPadding = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          RIGHT_PADDING_HEADER,
          ControlState.class,
          this::setRightPaddingForState,
          DEFAULT_PADDING);

  //attribute
  private final NonCascadingProperty<ControlState, Integer> topPadding = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          TOP_PADDING_HEADER,
          ControlState.class,
          this::setTopPaddingForState,
          DEFAULT_PADDING);

  //attribute
  private final NonCascadingProperty<ControlState, Integer> bottomPadding = NonCascadingProperty
      .forIntWithNameAndStateClassAndSetterMethodAndDefaultValue(
          BOTTOM_PADDING_HEADER,
          ControlState.class,
          this::setBottomPaddingForState,
          DEFAULT_PADDING);

  //attribute
  private final ForwardingProperty<ControlState, Integer> borderThickness = ForwardingProperty.withNameAndForProperty(
      BORDER_THICKNESS_HEADER,
      leftBorderThickness,
      rightBorderThickness,
      topBorderThickness,
      bottomBorderThickness);

  //attribute
  private final ForwardingProperty<ControlState, IColor> borderColor = ForwardingProperty.withNameAndForProperty(
      BORDER_COLOR_HEADER,
      leftBorderColor,
      rightBorderColor,
      topBorderColor,
      bottomBorderColor);

  //attribute
  private final ForwardingProperty<ControlState, Integer> padding = ForwardingProperty
      .withNameAndForProperty(PADDING_HEADER, leftPadding, rightPadding, topPadding, bottomPadding);

  //method
  @Override
  public final boolean definesHeightForState(final ControlState state) {
    return height.hasValueForState(state);
  }

  //method
  @Override
  public final boolean definesWidthForState(final ControlState state) {
    return width.hasValueForState(state);
  }

  //method
  @Override
  public final IBackground getBackgroundWhenHasState(final ControlState state) {
    return background.getValueWhenHasState(state);
  }

  //method
  @Override
  public final IColor getBottomBorderColorWhenHasState(final ControlState state) {
    return bottomBorderColor.getValueWhenHasState(state);
  }

  //method
  @Override
  public final int getBottomBorderThicknessWhenHasState(final ControlState state) {
    return bottomBorderThickness.getValueWhenHasState(state);
  }

  //method
  @Override
  public final int getBottomPaddingWhenHasState(final ControlState state) {
    return bottomPadding.getValueWhenHasState(state);
  }

  //method
  @Override
  public final IColor getLeftBorderColorWhenHasState(final ControlState state) {
    return leftBorderColor.getValueWhenHasState(state);
  }

  //method
  @Override
  public final int getLeftBorderThicknessWhenHasState(final ControlState state) {
    return leftBorderThickness.getValueWhenHasState(state);
  }

  //method
  @Override
  public final IAbsoluteOrRelativeInt getHeightForState(final ControlState state) {
    return height.getValueWhenHasState(state);
  }

  //method
  @Override
  public final int getLeftPaddingWhenHasState(final ControlState state) {
    return leftPadding.getValueWhenHasState(state);
  }

  //method
  @Override
  public final IColor getRightBorderColorWhenHasState(final ControlState state) {
    return rightBorderColor.getValueWhenHasState(state);
  }

  //method
  @Override
  public final int getRightBorderThicknessWhenHasState(final ControlState state) {
    return rightBorderThickness.getValueWhenHasState(state);
  }

  //method
  @Override
  public final int getRightPaddingWhenHasState(final ControlState state) {
    return rightPadding.getValueWhenHasState(state);
  }

  //method
  @Override
  public final IColor getTopBorderColorWhenHasState(final ControlState state) {
    return topBorderColor.getValueWhenHasState(state);
  }

  //method
  @Override
  public final int getTopBorderThicknessWhenHasState(final ControlState state) {
    return topBorderThickness.getValueWhenHasState(state);
  }

  //method
  @Override
  public final int getTopPaddingWhenHasState(final ControlState state) {
    return topPadding.getValueWhenHasState(state);
  }

  //method
  @Override
  public final IAbsoluteOrRelativeInt getWidthForState(final ControlState state) {
    return width.getValueWhenHasState(state);
  }

  //method
  @Override
  public final void removeCustomBackgrounds() {
    background.setUndefined();
  }

  //method
  @Override
  public final void removeCustomBorderColors() {
    borderColor.setUndefined();
  }

  //method
  @Override
  public final void removeCustomBorderThicknesses() {
    removeCustomLeftBorderColors();
    removeCustomRightBorderColors();
    removeCustomTopBorderColors();
    removeCustomBottomBorderColors();
  }

  //method
  @Override
  public final void removeCustomBottomBorderColors() {
    bottomBorderColor.setUndefined();
  }

  //method
  @Override
  public final void removeCustomBottomBorderThicknesses() {
    bottomBorderThickness.setUndefined();
  }

  //method
  @Override
  public final void removeCustomBottomPaddings() {
    bottomPadding.setUndefined();
  }

  //method
  @Override
  public final void removeCustomHeights() {
    height.setUndefined();
  }

  //method
  @Override
  public final void removeCustomLeftBorderColors() {
    leftBorderColor.setUndefined();
  }

  //method
  @Override
  public final void removeCustomLeftBorderThicknesses() {
    leftBorderThickness.setUndefined();
  }

  //method
  @Override
  public final void removeCustomLeftPaddings() {
    leftPadding.setUndefined();
  }

  //method
  @Override
  public final void removeCustomPaddings() {
    removeCustomLeftPaddings();
    removeCustomRightPaddings();
    removeCustomTopPaddings();
    removeCustomBottomPaddings();
  }

  //method
  @Override
  public final void removeCustomRightBorderColors() {
    rightBorderColor.setUndefined();
  }

  //method
  @Override
  public final void removeCustomRightBorderThicknesses() {
    rightBorderThickness.setUndefined();
  }

  //method
  @Override
  public final void removeCustomRightPaddings() {
    rightPadding.setUndefined();
  }

  //method
  @Override
  public final void removeCustomTopBorderColors() {
    topBorderColor.setUndefined();
  }

  //method
  @Override
  public final void removeCustomTopBorderThicknesses() {
    topBorderThickness.setUndefined();
  }

  //method
  @Override
  public final void removeCustomTopPaddings() {
    topPadding.setUndefined();
  }

  //method
  @Override
  public final void removeCustomWidths() {
    width.setUndefined();
  }

  //method
  @Override
  public final ECS setBackgroundColorForState(final ControlState state, final IColor backgroundColor) {
    return setBackgroundForState(state, Background.withColor(backgroundColor));
  }

  //method
  @Override
  public final ECS setBackgroundColorGradientForState(
      final ControlState state,
      final IColorGradient backgroundColorGradient) {
    return setBackgroundForState(state, Background.withColorGradient(backgroundColorGradient));
  }

  @Override
  public final ECS setBackgroundForState(ControlState state, IBackground background) {

    this.background.setValueForState(state, background);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setBackgroundImageForState(
      final ControlState state,
      final IImage backgroundImage,
      final ImageApplication imageApplication) {
    return setBackgroundForState(
        state,
        Background.withImageAndImageApplication(backgroundImage, imageApplication));
  }

  //method
  @Override
  public final ECS setBorderColorForState(final ControlState state, final IColor borderColor) {

    this.borderColor.setValueForState(state, borderColor);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setBorderThicknessForState(final ControlState state, final int borderThickness) {

    this.borderThickness.setValueForState(state, borderThickness);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setBottomBorderColorForState(final ControlState state, final IColor bottomBorderColor) {

    this.bottomBorderColor.setValueForState(state, bottomBorderColor);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setBottomBorderThicknessForState(final ControlState state, final int bottomBorderThickness) {

    GlobalValidator.assertThat(bottomBorderThickness).thatIsNamed("bottom border thickness").isNotNegative();

    this.bottomBorderThickness.setValueForState(state, bottomBorderThickness);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setBottomPaddingForState(final ControlState state, final int bottomPadding) {

    GlobalValidator.assertThat(bottomPadding).thatIsNamed("bottom padding").isNotNegative();

    this.bottomPadding.setValueForState(state, bottomPadding);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setHeightForState(final ControlState state, final int height) {

    setHeightForState(state, AbsoluteOrRelativeInt.withIntValue(height));

    return asConcrete();
  }

  //method
  @Override
  public final ECS setHeightInPercentOfViewAreaForState(
      final ControlState state,
      final double heightInPercentOfViewAreaHeight) {

    setHeightForState(state, AbsoluteOrRelativeInt.withPercentage(heightInPercentOfViewAreaHeight));

    return asConcrete();
  }

  //method
  @Override
  public final ECS setLeftBorderColorForState(final ControlState state, final IColor leftBorderColor) {

    this.leftBorderColor.setValueForState(state, leftBorderColor);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setLeftBorderThicknessForState(final ControlState state, final int leftBorderThickness) {

    GlobalValidator.assertThat(leftBorderThickness).thatIsNamed("left border thickness").isNotNegative();

    this.leftBorderThickness.setValueForState(state, leftBorderThickness);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setLeftPaddingForState(final ControlState state, final int leftPadding) {

    GlobalValidator.assertThat(leftPadding).thatIsNamed("left padding").isNotNegative();

    this.leftPadding.setValueForState(state, leftPadding);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setPaddingForState(final ControlState state, final int padding) {

    this.padding.setValueForState(state, padding);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setRightBorderColorForState(final ControlState state, final IColor rightBorderColor) {

    this.rightBorderColor.setValueForState(state, rightBorderColor);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setRightBorderThicknessForState(final ControlState state, final int rightBorderThickness) {

    GlobalValidator.assertThat(rightBorderThickness).thatIsNamed("right border thickness").isNotNegative();

    this.rightBorderThickness.setValueForState(state, rightBorderThickness);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setRightPaddingForState(final ControlState state, final int rightPadding) {

    GlobalValidator.assertThat(rightPadding).thatIsNamed("right padding").isNotNegative();

    this.rightPadding.setValueForState(state, rightPadding);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setTopBorderColorForState(final ControlState state, final IColor topBorderColor) {

    this.topBorderColor.setValueForState(state, topBorderColor);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setTopBorderThicknessForState(final ControlState state, final int topBorderThickness) {

    GlobalValidator.assertThat(topBorderThickness).thatIsNamed("top border thickness").isNotNegative();

    this.topBorderThickness.setValueForState(state, topBorderThickness);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setTopPaddingForState(final ControlState state, final int topPadding) {

    GlobalValidator.assertThat(topPadding).thatIsNamed("top padding").isNotNegative();

    this.topPadding.setValueForState(state, topPadding);

    return asConcrete();
  }

  //method
  @Override
  public final ECS setWidthForState(final ControlState state, final int width) {

    setWidthForState(state, AbsoluteOrRelativeInt.withIntValue(width));

    return asConcrete();
  }

  //method
  @Override
  public ECS setWidthInPercentOfViewAreaWidthForState(
      final ControlState state,
      final double widthInPercentOfViewAreaWidth) {

    setWidthForState(state, AbsoluteOrRelativeInt.withPercentage(widthInPercentOfViewAreaWidth));

    return asConcrete();
  }

  //method
  private void setHeightForState(final ControlState state, final IAbsoluteOrRelativeInt height) {

    ABSOLUTE_OR_RELATIVE_INT_VALIDATOR.assertIsPositive(height);

    this.height.setValueForState(state, height);
  }

  //method
  private void setWidthForState(final ControlState state, final IAbsoluteOrRelativeInt width) {

    ABSOLUTE_OR_RELATIVE_INT_VALIDATOR.assertIsPositive(width);

    this.width.setValueForState(state, width);
  }
}

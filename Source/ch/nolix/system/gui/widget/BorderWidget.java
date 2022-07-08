//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.math.GlobalCalculator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.valueholder.IntOrPercentageHolder;
import ch.nolix.system.valueholder.IntOrPercentageHolderValidator;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;

//class
/**
 * A {@link BorderWidget} is a {@link Widget} that:
 * -Can have an individual border at each of its 4 sides.
 * -Can have a minWidth and a minHeight.
 * -Can have a maxWidth and a maxHeight.
 * -Can have a proposalWidth and a proposalHeight.
 * 
 * -A minWidth, minHeight, proposalWidth or proposalHeight of a {@link BorderWidget}
 *  can make that it is stretched what moves around its content inside its borders.
 * -A maxWidth, maxHeight, proposalWidth or proposalHeight of a {@link BorderWidget}
 *  can make that its content is moved into a show area which can be scrolled.
 *  Inside a show area, the content of a {@link BorderWidget} gets its natural width resp. natural height.
 * -The width resp. height of a {@link BorderWidget}, in the case when it is expanded,
 *  is set from its target width resp. target height if it has a target width resp. target height.
 *  Otherwise the width resp. height of a {@link BorderWidget} is set from its natural width resp. natural height.
 * 
 * A {@link BorderWidget} has a natural width resp. natural height.
 * The natural width resp. natural height of a {@link BorderWidget}
 * is the width resp. height it would have with its content ignoring any min-, max-, proposal- width resp. height.
 * 
 * A {@link BorderWidget} can have a targetWidth resp. targetHeight.
 * The targetWidth resp. targetHeight of a {@link BorderWidget} will calculated by the following algorithm.
 * 1. The targetWidth resp. targetHeight of the {@link BorderWidget} is set as undefined.
 * 2. The targetWidth resp. targetHeight of the {@link BorderWidget} is set from its proposalWidth resp. proposalHeight if
 *    it has a proposalWidth resp proposalHeight.
 * 3. The targetWidth resp. targetHeight of the {@link BorderWidget} is set from its minWidth resp minHeight if:
 *    -It has a minWidth resp. minHeight.
 *    -And its minWidth resp. minHeight would be bigger than its targetWidth resp. targetHeight.
 * 4. The targetWidth resp. targetHeight of the {@link BorderWidget} is set from its maxWidth resp. maxHeight if:
 *    -It has a maxWidth resp. maxHeight.
 *    -And its maxWidth resp. maxHeight would be smaller than its targetWidth resp. targetHeight.
 * 5. If the targetWidth resp. targetHeight of the {@link BorderWidget} is still undefined,
 *    then the {@link BorderWidget} does not have a targetWidth resp. targetHeight.
 * 
 * A {@link BorderWidget} consists of the following areas from outer to inner.
 * 1. main area: Contains the probable borders and bordered area.
 * 2. bordered area: Contains the probable scroll bars and show area.
 * 3. show area: Is over the scrolled area and is like a hole to look on the scrolled area below.
 * 4. scrolled area: Contains the extending paddings and extended content area.
 * 5. extended content area: Contains the paddings and content area.
 * 6. content area: Contains the content.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <BW> is the type of a {@link BorderWidget}.
 * @param <BWL> is the type of the {@link BorderWidgetLook}s of a {@link BorderWidget}.
 */
public abstract class BorderWidget<BW extends BorderWidget<BW, BWL>,BWL extends BorderWidgetLook<BWL>>
extends Widget<BW, BWL> {
	
	//constants
	public static final int SCROLL_BAR_THICKNESS = 20;
	public static final boolean DEFAULT_AUTOMATIC_SIZE_STATE = false;
	public static final ContentPosition DEFAULT_CONTENT_POSITION = ContentPosition.TOP_LEFT;
	public static final int DEFAULT_SHOW_AREA_X_POSITION_ON_SCROLLED_AREA = 0;
	public static final int DEFAULT_SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA = 0;
	
	//constant
	private static final int SHOW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP = 50;
		
	//constants
	private static final String AUTOMATIC_SIZE_HEADER = "AutomaticSize";
	private static final String CONTENT_POSITION_HEADER = "ContentPosition";
	private static final String MIN_WIDTH_HEADER = "MinWidth";
	private static final String MIN_HEIGHT_HEADER = "MinHeight";
	private static final String MAX_WIDTH_HEADER = "MaxWidth";
	private static final String MAX_HEIGHT_HEADER = "MaxHeight";
	private static final String PROPOSAL_WIDTH_HEADER = "ProposalWidth";
	private static final String PROPOSAL_HEIGHT_HEADER = "ProposalHeight";
	private static final String SHOW_AREA_X_POSITION_ON_SCROLLED_AREA_HEADER = "ShowAreaXPositionOnScrolledArea";
	private static final String SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA_HEADER = "ShowAreaYPositionOnScrolledArea";
	
	//attribute
	private MutableValue<Boolean> automaticSize =
	new MutableValue<>(
		AUTOMATIC_SIZE_HEADER,
		DEFAULT_AUTOMATIC_SIZE_STATE,
		(final Boolean as) -> {
			if (as.booleanValue()) {
				activateAutomaticSize();
			} else {
				deactivateAutomaticSize();
			}
		},
		INode::getSingleChildNodeAsBoolean,
		Node::withChildNode
	);
	
	//attribute
	private MutableValue<ContentPosition> contentPosition =
	new MutableValue<>(
		CONTENT_POSITION_HEADER,
		DEFAULT_CONTENT_POSITION,
		this::setContentPosition,
		ContentPosition::fromSpecification,
		Node::fromEnum
	);
	
	//attribute
	private final MutableOptionalValue<IntOrPercentageHolder> minWidth =
	MutableOptionalValue.forElement(
		MIN_WIDTH_HEADER,
		this::setMinWidth,
		IntOrPercentageHolder::fromSpecification
	);
	
	//attribute
	private final MutableOptionalValue<IntOrPercentageHolder> minHeight =
	MutableOptionalValue.forElement(
		MIN_HEIGHT_HEADER,
		this::setMinHeight,
		IntOrPercentageHolder::fromSpecification
	);
	
	//attribute
	private final MutableOptionalValue<IntOrPercentageHolder> maxWidth =
	MutableOptionalValue.forElement(
		MAX_WIDTH_HEADER,
		this::setMaxWidth,
		IntOrPercentageHolder::fromSpecification
	);
	
	//attribute
	private final MutableOptionalValue<IntOrPercentageHolder> maxHeight =
	MutableOptionalValue.forElement(
		MAX_HEIGHT_HEADER,
		this::setMaxHeight,
		IntOrPercentageHolder::fromSpecification
	);
	
	//attribute
	private final MutableOptionalValue<IntOrPercentageHolder> proposalWidth =
	MutableOptionalValue.forElement(
		PROPOSAL_WIDTH_HEADER,
		this::setProposalWidth,
		IntOrPercentageHolder::fromSpecification
	);
	
	//attribute
	private final MutableOptionalValue<IntOrPercentageHolder> proposalHeight =
	MutableOptionalValue.forElement(
		PROPOSAL_HEIGHT_HEADER,
		this::setProposalHeight,
		IntOrPercentageHolder::fromSpecification
	);
	
	//attribute
	private final MutableValue<Integer> showAreaXPositionOnScrolledArea =
	new MutableValue<>(
		SHOW_AREA_X_POSITION_ON_SCROLLED_AREA_HEADER,
		DEFAULT_SHOW_AREA_X_POSITION_ON_SCROLLED_AREA,
		this::setShowAreaXPositionOnScrolledArea,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode
	);
	
	//attribute
	private final MutableValue<Integer> showAreaYPositionOnScrolledArea =
	new MutableValue<>(
		SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA_HEADER,
		DEFAULT_SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA,
		this::setShowAreaYPositionOnScrolledArea,
		INode::getSingleChildNodeAsInt,
		Node::withChildNode
	);
	
	//attributes
	private final BorderWidgetMainArea<BWL> mainArea = new BorderWidgetMainArea<>(this);
	private final BorderWidgetBorderedArea<BWL> borderedArea = new BorderWidgetBorderedArea<>(this);
	private final BorderWidgetShowArea<BWL> showArea = new BorderWidgetShowArea<>(this);
	private final BorderWidgetScrolledArea<BWL> scrolledArea = new BorderWidgetScrolledArea<>(this);
	private final BorderWidgetExtendedContentArea<BWL> extendedContentArea = new BorderWidgetExtendedContentArea<>(this);
	private final BorderWidgetContentArea<BWL> contentArea = new BorderWidgetContentArea<>(this);
	
	//attribute
	private final BorderWidgetVerticalScrollBar<BWL> verticalScrollBar = new BorderWidgetVerticalScrollBar<>(this);
	
	//attribute
	private final BorderWidgetVerticalScrollBarCursor<BWL> verticalScrollBarCursor =
	new BorderWidgetVerticalScrollBarCursor<>(this);
	
	//attribute
	private final BorderWidgetHorizontalScrollBar<BWL> horizontalScrollBar =
	new BorderWidgetHorizontalScrollBar<>(this);
	
	//attribute
	private final BorderWidgetHorizontalScrollBarCursor<BWL> horizontalScrollBarCursor =
	new BorderWidgetHorizontalScrollBarCursor<>(this);
	
	//attribute
	private final BorderWidgetInterScrollBarArea<BWL> interScrollBarArea =
	new BorderWidgetInterScrollBarArea<>(this);
		
	//attributes
	private boolean isMovingVerticalScrollBarCursor;
	private boolean isMovingHorizontalScrollBarCursor;
	
	//optional attributes
	private int verticalScrollingCursorStartYPosition;
	private int horizontalScrollingCursorStartXPosition;
	
	//method
	/**
	 * Lets the current {@link BorderWidget} active automatic size.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW activateAutomaticSize() {
		
		this.automaticSize.setValue(true);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} deactive automatic size.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW deactivateAutomaticSize() {
		
		this.automaticSize.setValue(false);
		
		return asConcrete();
	}
	
	//method
	/**
	 * A {@link BorderWidget} can always define a max height, but can calculate it only when
	 * the {@link BorderWidget} has all the required input parameters.
	 * 
	 * @return true if the current {@link BorderWidget} defines a max height.
	 */
	public final boolean definesMaxHeight() {
		return maxHeight.hasValue();
	}
	
	//method
	/**
	 * A {@link BorderWidget} can always define a max width, but can calculate it only when
	 * the {@link BorderWidget} has all the required input parameters.
	 * 
	 * @return true if the current {@link BorderWidget} defines a max width.
	 */
	public final boolean definesMaxWidth() {
		return maxWidth.hasValue();
	}
	
	//method
	/**
	 * A {@link BorderWidget} can always define a min height, but can calculate it only when
	 * the {@link BorderWidget} has all the required input parameters.
	 * 
	 * @return true if the current {@link BorderWidget} defines a min height.
	 */
	public final boolean definesMinHeight() {
		return minHeight.hasValue();
	}
	
	//method
	/**
	 * A {@link BorderWidget} can always define a min width, but can calculate it only when
	 * the {@link BorderWidget} has all the required input parameters.
	 * 
	 * @return true if the current {@link BorderWidget} defines a min width.
	 */
	public final boolean definesMinWidth() {
		return minWidth.hasValue();
	}
	
	//method
	/**
	 * A {@link BorderWidget} can always define a proposal height, but can calculate it only when
	 * the {@link BorderWidget} has all the required input parameters.
	 * 
	 * @return true if the current {@link BorderWidget} defines a proposal height.
	 */
	public final boolean definesProposalHeight() {
		return proposalHeight.hasValue();
	}
	
	//method
	/**
	 * A {@link BorderWidget} can always define a proposal width, but can calculate it only when
	 * the {@link BorderWidget} has all the required input parameters.
	 * 
	 * @return true if the current {@link BorderWidget} defines a proposal width.
	 */
	public final boolean definesProposalWidth() {
		return proposalWidth.hasValue();
	}
	
	//method
	/**
	 * @return the bordered area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetBorderedArea<BWL> getBorderedArea() {
		return borderedArea;
	}
	
	//method
	/**
	 * @return the content area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetContentArea<BWL> getContentArea() {
		return contentArea;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getContentAreaXPosition() {
		return contentArea.getXPosition();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getContentAreaYPosition() {
		return contentArea.getYPosition();
	}
	
	//method
	/**
	 * @return the content position of the current {@link BorderWidget}.
	 */
	public final ContentPosition getContentPosition() {
		return contentPosition.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CursorIcon getCursorIcon() {
		
		if (anyScrollBarCursorIsUnderCursor()) {
			return CursorIcon.HAND;
		}
		
		if (showAreaIsUnderCursor()) {
			return super.getCursorIcon();
		}
		
		return CursorIcon.ARROW;
	}
	
	//method
	/**
	 * @return the extended content area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetExtendedContentArea<BWL> getExtendedContentArea() {
		return extendedContentArea;
	}
		
	//method
	/**
	 * @return the horizontal scroll bar of the current {@link BorderWidget}.
	 */
	public final BorderWidgetHorizontalScrollBar<BWL> getHorizontalScrollBar() {
		return horizontalScrollBar;
	}
	
	//method
	/**
	 * @return the horizontal scroll bar cursor of the current {@link BorderWidget}.
	 */
	public final BorderWidgetHorizontalScrollBarCursor<BWL> getHorizontalScrollBarCursor() {
		return horizontalScrollBarCursor;
	}
	
	//method
	/**
	 * @return the inter scroll bar area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetInterScrollBarArea<BWL> getInterScrollBarArea() {
		return interScrollBarArea;
	}
	
	//method
	/**
	 * @return the main area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetMainArea<BWL> getMainArea() {
		return mainArea;
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a max height.
	 */
	public final int getMaxHeight() {
		
		if (maxHeight.getValue().hasIntValue()) {
			return maxHeight.getValue().getIntValue();
		}
		
		return maxHeight.getValue().getValueInRelationToHundredPercentValue(getParentGUI().getViewAreaHeight());
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a max width.
	 */
	public final int getMaxWidth() {
		
		if (maxWidth.getValue().hasIntValue()) {
			return maxWidth.getValue().getIntValue();
		}
		
		return maxWidth.getValue().getValueInRelationToHundredPercentValue(getParentGUI().getViewAreaWidth());
	}
	
	//method
	/**
	 * @return the min height of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a min height.
	 */
	public final int getMinHeight() {
		
		if (minHeight.getValue().hasIntValue()) {
			return minHeight.getValue().getIntValue();
		}
		
		return minHeight.getValue().getValueInRelationToHundredPercentValue(getParentGUI().getViewAreaHeight());
	}
	
	//method
	/**
	 * @return the min width of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a min width.
	 */
	public final int getMinWidth() {
		
		if (minWidth.getValue().hasIntValue()) {
			return minWidth.getValue().getIntValue();
		}
		
		return minWidth.getValue().getValueInRelationToHundredPercentValue(getParentGUI().getViewAreaWidth());
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidget}.
	 */
	public final int getNaturalHeight() {
		return mainArea.getNaturalHeight();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidget}.
	 */
	public final int getNaturalWidth() {
		return mainArea.getNaturalWidth();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a proposal height.
	 */
	public final int getProposalHeight() {
		
		if (proposalHeight.getValue().hasIntValue()) {
			return proposalHeight.getValue().getIntValue();
		}
		
		return proposalHeight.getValue().getValueInRelationToHundredPercentValue(getParentGUI().getViewAreaHeight());
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a proposal width.
	 */
	public final int getProposalWidth() {
		
		if (proposalWidth.getValue().hasIntValue()) {
			return proposalWidth.getValue().getIntValue();
		}
		
		return proposalWidth.getValue().getValueInRelationToHundredPercentValue(getParentGUI().getViewAreaWidth());
	}
	
	//method
	/**
	 * @return the scrolled area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetScrolledArea<BWL> getScrolledArea() {
		return scrolledArea;
	}
	
	//method
	/**
	 * @return x-position of the show area of the current {@link BorderWidget} on the scroll area.
	 */
	public final int getShowAreaXPositionOnScrolledArea() {
		return showAreaXPositionOnScrolledArea.getValue();
	}
	
	//method
	/**
	 * @return the y-position of the show area of the current {@link BorderWidget} on the scroll area.
	 */
	public final int getShowAreaYPositionOnScrolledArea() {
		return showAreaYPositionOnScrolledArea.getValue();
	}
	
	//method
	/**
	 * @return the target height of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a target height.
	 */
	public final int getTargetHeight() {
		
		var hasTargetHeight = false;
		var targetHeight = 0;
		
		if (hasProposalHeight()) {
			hasTargetHeight = true;
			targetHeight = getProposalHeight();
		}
		
		if (hasMinHeight() && (getMinHeight() > targetHeight || !hasTargetHeight)) {
			hasTargetHeight = true;
			targetHeight = getMinHeight();
		}
		
		if (hasMaxHeight() && (getMaxHeight() < targetHeight || !hasTargetHeight)) {
			hasTargetHeight = true;
			targetHeight = getMaxHeight();
		}
		
		if (!hasTargetHeight) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "target height");
		}
		
		return targetHeight;
	}
	
	//method
	/**
	 * @return the target width of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a target width.
	 */
	public final int getTargetWidth() {
		
		var hasTargetWidth = false;
		var targetWidth = 0;
		
		if (hasProposalWidth()) {
			hasTargetWidth = true;
			targetWidth = getProposalWidth();
		}
		
		if (hasMinWidth() && (getMinWidth() > targetWidth || !hasTargetWidth)) {
			hasTargetWidth = true;
			targetWidth = getMinWidth();
		}
		
		if (hasMaxWidth() && (getMaxWidth() < targetWidth || !hasTargetWidth)) {
			hasTargetWidth = true;
			targetWidth = getMaxWidth();
		}
		
		if (!hasTargetWidth) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "target width");
		}
		
		return targetWidth;
	}
	
	//method
	/**
	 * @return the vertical scroll bar of the current {@link BorderWidget}.
	 */
	public final BorderWidgetVerticalScrollBar<BWL> getVerticalScrollBar() {
		return verticalScrollBar;
	}
	
	//method
	/**
	 * @return the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	public final BorderWidgetVerticalScrollBarCursor<BWL> getVerticalScrollBarCursor() {
		return verticalScrollBarCursor;
	}
	
	//method
	/**
	 * @return the show area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetShowArea<BWL> getShowArea() {
		return showArea;
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has automatic size.
	 */
	public final boolean hasAutomaticSize() {
		return automaticSize.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a max height.
	 */
	public final boolean hasMaxHeight() {
		return maxHeight.hasValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a max width.
	 */
	public final boolean hasMaxWidth() {
		return
		definesMaxWidth()
		&& (maxWidth.getValue().hasIntValue() || belongsToGUI());
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a min height.
	 */	
	public final boolean hasMinHeight() {
		return
		definesMinHeight()
		&& (minHeight.getValue().hasIntValue() || belongsToGUI());
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a min width.
	 */
	public final boolean hasMinWidth() {
		return
		definesMinWidth()
		&& (minWidth.getValue().hasIntValue() || belongsToGUI());
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a proposal height.
	 */
	public final boolean hasProposalHeight() {
		return
		definesProposalHeight()
		&& (proposalHeight.getValue().hasIntValue() || belongsToGUI());
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a proposal width.
	 */
	public final boolean hasProposalWidth() {
		return
		definesProposalWidth()
		&& (proposalWidth.getValue().hasIntValue() || belongsToGUI());
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a target height.
	 */
	public final boolean hasTargetHeight() {
		return (hasMinHeight() || hasMaxHeight() || hasProposalHeight());
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a target width.
	 */
	public final boolean hasTargetWidth() {
		return (hasMinWidth() || hasMaxWidth() || hasProposalWidth());
	}
	
	//method
	/**
	 * Removes the max height of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMaxHeight() {
		
		maxHeight.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the max width of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMaxWidtht() {
		
		maxWidth.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the min height of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMinHeight() {
		
		minHeight.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the min width of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMinWidth() {
		
		minWidth.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the proposal height of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeProposalHeight() {
		
		proposalHeight.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the proposal width of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeProposalWidth() {
		
		proposalWidth.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to bottom.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToBottom() {
		
		setShowAreaYPositionOnScrolledArea(scrolledArea.getHeight());
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to left.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToLeft() {
		
		setShowAreaXPositionOnScrolledArea(0);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to right.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToRight() {
		
		setShowAreaXPositionOnScrolledArea(scrolledArea.getWidth());
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to top.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToTop() {
		
		setShowAreaYPositionOnScrolledArea(0);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the content position of the current {@link BorderWidget}.
	 * 
	 * @param contentPosition
	 * @return the current {@link BorderWidget}.
	 * @throws ArgumentIsNullException if the given contentPosition is null.
	 */
	public final BW setContentPosition(final ContentPosition contentPosition) {
		
		//Sets the contentPosition of the current BorderWidget.
		this.contentPosition.setValue(contentPosition);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the max height of the current {@link BorderWidget}.
	 * 
	 * @param maxHeight
	 * @return the current {@link BorderWidget}.
	 * @throws NonPositiveArgumentException if the given maxHeight is not positive.
	 */
	public final BW setMaxHeight(final int maxHeight) {
		
		setMaxHeight(IntOrPercentageHolder.withIntValue(maxHeight));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the max height of the current {@link BorderWidget} in percent of the GUI view area height.
	 * 
	 * @param maxHeightInPercentOfGUIViewAreaHeight
	 * @return the current {@link BorderWidget}.
	 * @throws NegativeArgumentException if the given maxHeightInPercentOfGUIViewAreaHeight is negative.
	 */
	public final BW setMaxHeightInPercentOfGUIViewAreaHeight(final double maxHeightInPercentOfGUIViewAreaHeight) {
		
		setMaxHeight(IntOrPercentageHolder.withPercentage(maxHeightInPercentOfGUIViewAreaHeight));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the max width of the current {@link BorderWidget}.
	 * 
	 * @param maxWidth
	 * @return the current {@link BorderWidget}.
	 * @throws InvalidArgumentException if the given maxWidth is not positive.
	 */
	public final BW setMaxWidth(final int maxWidth) {
		
		setMaxWidth(IntOrPercentageHolder.withIntValue(maxWidth));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the max width of the current {@link BorderWidget} in percent of the GUI view area width.
	 * 
	 * @param maxWidthInPercentOfGUIViewAreaWidth
	 * @return the current {@link BorderWidget}.
	 * @throws NegativeArgumentException if the given maxWidthInPercentOfGUIViewAreaWidth is negative.
	 */
	public final BW setMaxWidthInPercentOfGUIViewAreaWidth(final double maxWidthInPercentOfGUIViewAreaWidth) {
		
		setMaxWidth(IntOrPercentageHolder.withPercentage(maxWidthInPercentOfGUIViewAreaWidth));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the min height of the current {@link BorderWidget}.
	 * 
	 * @param minHeight
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setMinHeight(final int minHeight) {
		
		setMinHeight(IntOrPercentageHolder.withIntValue(minHeight));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the min height of the current {@link BorderWidget} in percent of the GUI view area height.
	 * 
	 * @param minHeightInPercentOfGUIViewAreaHeight
	 * @return the current {@link BorderWidget}.
	 * @throws NegativeArgumentException if the given minHeightInPercentOfGUIViewAreaHeight is negative.
	 */
	public final BW setMinHeightInPercentOfGUIViewAreaHeight(final double minHeightInPercentOfGUIViewAreaHeight) {
		
		setMinHeight(IntOrPercentageHolder.withPercentage(minHeightInPercentOfGUIViewAreaHeight));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the min width of the current {@link BorderWidget}.
	 * 
	 * @param minWidth
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setMinWidth(final int minWidth) {
		
		setMinWidth(IntOrPercentageHolder.withIntValue(minWidth));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the min width of the current {@link BorderWidget} in percent of the GUI view area width.
	 * 
	 * @param minWidthInPercentOfGUIViewAreaWidth
	 * @return the current {@link BorderWidget}.
	 * @throws NegativeArgumentException if the given minWidthInPercentOfGUIViewAreaWidth is negative.
	 */
	public final BW setMinWidthInPercentOfGUIViewAreaWidth(final double minWidthInPercentOfGUIViewAreaWidth) {
		
		setMinWidth(IntOrPercentageHolder.withPercentage(minWidthInPercentOfGUIViewAreaWidth));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the proposal height of the current {@link BorderWidget}.
	 * 
	 * @param proposalHeight
	 * @return the current {@link BorderWidget}.
	 * @throws InvalidArgumentException if the given proposalHeight is not positive.
	 */
	public final BW setProposalHeight(final int proposalHeight) {
		
		setProposalHeight(IntOrPercentageHolder.withIntValue(proposalHeight));
				
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the proposal height of the current {@link BorderWidget} in percent of the GUI view area height.
	 * 
	 * @param proposalHeightInPercentOfGUIViewAreaHeight
	 * @return the current {@link BorderWidget}.
	 * @throws NegativeArgumentException if the given proposalHeightInPercentOfGUIViewAreaHeight is negative.
	 */
	public final BW setProposalHeightInPercentOfGUIViewAreaHeight(
		final double proposalHeightInPercentOfGUIViewAreaHeight
	) {
		
		setProposalHeight(IntOrPercentageHolder.withPercentage(proposalHeightInPercentOfGUIViewAreaHeight));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the proposal with of the current {@link BorderWidget}.
	 * 
	 * @param proposalWidth
	 * @return the current {@link BorderWidget}.
	 * @throws InvalidArgumentException if the given proposalWidth is not positive.
	 */
	public final BW setProposalWidth(final int proposalWidth) {
		
		setProposalWidth(IntOrPercentageHolder.withIntValue(proposalWidth));
				
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the proposal width of the current {@link BorderWidget} in percent of the GUI view area width.
	 * 
	 * @param proposalWidthInPercentOfGUIViewAreaWidth
	 * @return the current {@link BorderWidget}.
	 * @throws NegativeArgumentException if the given proposalWidthInPercentOfGUIViewAreaWidth is negative.
	 */
	public final BW setProposalWidthInPercentOfGUIViewAreaWidth(
		final double proposalWidthInPercentOfGUIViewAreaWidth
	) {
		
		setProposalWidth(IntOrPercentageHolder.withPercentage(proposalWidthInPercentOfGUIViewAreaWidth));
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the x-position of the show area on the scroll area of the current {@link BorderWidget}.
	 * 
	 * @param showAreaXPositionOnScrolledArea
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setShowAreaXPositionOnScrolledArea(int showAreaXPositionOnScrolledArea) {
		
		showAreaXPositionOnScrolledArea =
		GlobalCalculator.getMin(showAreaXPositionOnScrolledArea,	scrolledArea.getWidth() - showArea.getWidth());
		
		showAreaXPositionOnScrolledArea = GlobalCalculator.getMax(showAreaXPositionOnScrolledArea, 0);
		
		this.showAreaXPositionOnScrolledArea.setValue(showAreaXPositionOnScrolledArea);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the y-position of the show area on the scroll area of the current {@link BorderWidget}.
	 * 
	 * @param showAreaYPositionOnScrolledArea
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setShowAreaYPositionOnScrolledArea(int showAreaYPositionOnScrolledArea) {
		
		showAreaYPositionOnScrolledArea =
		GlobalCalculator.getMin(showAreaYPositionOnScrolledArea,	scrolledArea.getHeight() - showArea.getHeight());
		
		showAreaYPositionOnScrolledArea = GlobalCalculator.getMax(showAreaYPositionOnScrolledArea, 0);
		
		this.showAreaYPositionOnScrolledArea.setValue(showAreaYPositionOnScrolledArea);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean showAreaIsUnderCursor() {
		return showArea.isUnderCursor();
	}
	
	//method
	/**
	 * @return true if a scroll bar cursor of the current {@link BorderWidget} is under the cursor.
	 */
	protected final boolean anyScrollBarCursorIsUnderCursor() {
		return (verticalScrollBarCursor.isUnderCursor() || horizontalScrollBarCursor.isUnderCursor());
	}
	
	//method declaration
	/**
	 * @return true if the width resp. height of the content area of the current {@link BorderWidget} should be expanded
	 * to its target width resp. target height in the case when:
	 * -The current {@link BorderWidget} has a target width resp. target height.
	 * -The target width resp. target height of the content area of the current {@link BorderWidget}
	 *  is bigger than its natural width resp. natural height.
	 */
	protected abstract boolean contentAreaMustBeExpandedToTargetSize();
	
	//method
	/**
	 * @return the height of the current {@link BorderWidget} when it is not collapsed.
	 */
	@Override
	protected final int getHeightWhenExpanded() {
		
		int height;
		
		if (!hasProposalHeight()) {
			height = getNaturalHeight();
		} else {
			height = getProposalHeight();
		}
		
		if (hasMinHeight()) {
			height = GlobalCalculator.getMax(getMinHeight(), height);
		}
		
		if (hasMaxHeight()) {
			height = GlobalCalculator.getMin(getMaxHeight(), height);
		}
		
		return height;
	}
	
	//method declaration
	/**
	 * @return the natural height of the content area of the current {@link BorderWidget}.
	 */
	protected abstract int getNaturalContentAreaHeight();
	
	//method declaration
	/**
	 * @return the natural width of the content area of the current {@link BorderWidget}.
	 */
	protected abstract int getNaturalContentAreaWidth();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getWidthWhenExpanded() {
		
		int width;
		
		if (!hasProposalWidth()) {
			width = getNaturalWidth();
		} else {
			width = getProposalWidth();
		}
		
		if (hasMinWidth()) {
			width = GlobalCalculator.getMax(getMinWidth(), width);
		}
		
		if (hasMaxWidth()) {
			width = GlobalCalculator.getMin(getMaxWidth(), width);
		}
		
		return width;
	}
	
	//method
	/**
	 * @return true if the user is moving any scroll bar cursor of the current {@link BorderWidget}.
	 */
	protected final boolean isMovingAnyScrollBarCursor() {
		return (isMovingHorizontalScrollBarCursor() || isMovingVerticalScrollBarCursor());
	}

	//method
	/**
	 * @return true if the user is moving the horizontal scroll bar cursor of the current {@link BorderWidget}.
	 */
	protected final boolean isMovingHorizontalScrollBarCursor() {
		return isMovingHorizontalScrollBarCursor;
	}

	//method
	/**
	 * @return true if the user is moving the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	protected final boolean isMovingVerticalScrollBarCursor() {
		return isMovingVerticalScrollBarCursor;
	}

	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a key press on the content area
	 * for the case when the current {@link BorderWidget} is focused.
	 * 
	 * @param key
	 */
	protected abstract void noteKeyDownOnSelfWhenFocused(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a left mouse button click on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonClickOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonClickOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteLeftMouseButtonClickOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a left mouse button press on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonPressOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonPressOnSelfWhenEnabled() {
		
		//Handles the case that the vertical scroll bar cursor is under the cursor.
		if (verticalScrollBarCursor.isUnderCursor()) {
			
			isMovingVerticalScrollBarCursor = true;
			
			verticalScrollingCursorStartYPosition =
			getCursorYPosition() - verticalScrollBarCursor.getYPositionOnVerticalScrollBar();
			
		//Handles the case that the horizontal scroll bar cursor is under the cursor.
		} else if (horizontalScrollBarCursor.isUnderCursor()) {
			
			isMovingHorizontalScrollBarCursor = true;
			
			horizontalScrollingCursorStartXPosition =
			getCursorXPosition() - horizontalScrollBarCursor.getXPositionOnHorizontalScrollBar();
			
		//Handles the case that the show area and content area is under the cursor.
		} else if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteLeftMouseButtonPressOnContentAreaWhenEnabled();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonReleaseOnSelfWhenEnabled() {
		
		isMovingHorizontalScrollBarCursor = false;
		isMovingVerticalScrollBarCursor = false;
		
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteLeftMouseButtonReleaseOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a left mouse button release on the show area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveOnSelfWhenEnabled() {
				
		if (isMovingVerticalScrollBarCursor) {
			
			final var verticalScrollBarCursorYDelta =
			getCursorYPosition() - verticalScrollingCursorStartYPosition;
			
			final var showAreaHeight = showArea.getHeight();
			
			final var showAreaYDelta =
			(verticalScrollBarCursorYDelta * (scrolledArea.getHeight() - showAreaHeight))
			/ (showAreaHeight - verticalScrollBarCursor.getHeight());
			
			setShowAreaYPositionOnScrolledArea(showAreaYDelta);
		} else if (isMovingHorizontalScrollBarCursor) {
			
			final var horizontalScrollBarCursorXDelta =
			getCursorXPosition() - horizontalScrollingCursorStartXPosition;
			
			final var showAreaWidth = showArea.getWidth();
			
			final var showAreaXDelta =
			(horizontalScrollBarCursorXDelta * (scrolledArea.getWidth() - showAreaWidth))
			/ (showAreaWidth - horizontalScrollBarCursor.getWidth());
			
			setShowAreaXPositionOnScrolledArea(showAreaXDelta);
		}
		
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteMouseMoveOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a mouse move on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteMouseMoveOnContentAreaWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a mouse wheel click on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteMouseWheelClickOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelClickOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteMouseWheelClickOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a mouse wheel press on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteMouseWheelPressOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelPressOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteMouseWheelPressOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a mouse wheel release on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteMouseWheelReleaseOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelReleaseOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteMouseWheelReleaseOnContentAreaWhenEnabled();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelRotationStepOnSelfWhenFocused(final RotationDirection rotationDirection) {
		
		setShowAreaYPositionOnScrolledArea(
			getShowAreaYPositionOnScrolledArea()
			+ rotationDirection.toInt() * SHOW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP
		);
		
		noteMouseWheelRotationStepOnBorderWidgetWhenFocused(rotationDirection);
	}
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a mouse wheel rotation step
	 * for the case when the current {@link Widget} is focused.
	 * 
	 * @param rotationDirection
	 */
	protected abstract void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(
		final RotationDirection rotationDirection
	);
		
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a right mouse button click on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteRightMouseButtonClickOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonClickOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteRightMouseButtonClickOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a right mouse button press on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteRightMouseButtonPressOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonPressOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteRightMouseButtonPressOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a right mouse button release on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteRightMouseButtonReleaseOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonReleaseOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteRightMouseButtonReleaseOnContentAreaWhenEnabled();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void paint(final IPainter painter, final BWL borderWidgetLook) {
		mainArea.paint(painter, borderWidgetLook);
	}
	
	//method declaration
	/**
	 * Paints the content area of the current {@link BorderWidget} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	protected abstract void paintContentArea(IPainter painter, BWL borderWidgetLook);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean paintsWidgetsForPaintingAPriori() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void recalculateWidgetSelf() {
		
		recalculateBorderWidget();
		
		contentArea.recalculate();
	}
	
	//method declaration
	/**
	 * Recalculates the current {@link BorderWidget}.
	 */
	protected abstract void recalculateBorderWidget();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean redirectsInputsToShownWidgets() {
		return (isEnabled() && (showAreaIsUnderCursor() || !anyScrollBarIsVisible()));
	}
	
	//method declaration
	/**
	 * Resets the current {@link BorderWidget}.
	 */
	protected abstract void resetBorderWidget();
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link BorderWidget} on itself, and on itself only.
	 */
	protected abstract void resetBorderWidgetConfiguration();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetWidget() {
		
		removeProposalWidth();
		removeProposalHeight();
		removeMinWidth();
		removeMinHeight();
		removeMaxWidtht();
		removeMaxHeight();
		
		showAreaXPositionOnScrolledArea.setValue(DEFAULT_SHOW_AREA_X_POSITION_ON_SCROLLED_AREA);
		showAreaYPositionOnScrolledArea.setValue(DEFAULT_SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA);
		
		resetBorderWidget();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetWidgetConfiguration() {
		
		deactivateAutomaticSize();
		removeMinWidth();
		removeMinHeight();
		removeMaxWidtht();
		removeMaxHeight();
		removeProposalWidth();
		removeProposalHeight();
		
		setContentPosition(ContentPosition.TOP_LEFT);
		setShowAreaXPositionOnScrolledArea(0);
		setShowAreaYPositionOnScrolledArea(0);
		
		resetBorderWidgetConfiguration();
	}
	
	//method
	private boolean anyScrollBarIsVisible() {
		return (verticalScrollBar.isVisible() || horizontalScrollBar.isVisible());
	}
	
	//method
	private void setMaxHeight(final IntOrPercentageHolder maxHeight) {
		
		IntOrPercentageHolderValidator.INSTANCE.assertIsPositive(maxHeight);
		
		this.maxHeight.setValue(maxHeight);
		setShowAreaYPositionOnScrolledArea(0);
	}
	
	//method
	private void setMaxWidth(final IntOrPercentageHolder maxWidth) {
		
		IntOrPercentageHolderValidator.INSTANCE.assertIsPositive(maxWidth);
		
		this.maxWidth.setValue(maxWidth);
		setShowAreaXPositionOnScrolledArea(0);
	}
	
	//method
	private void setMinHeight(final IntOrPercentageHolder minHeight) {
		this.minHeight.setValue(minHeight);
		setShowAreaYPositionOnScrolledArea(0);
	}
	
	//method
	private void setMinWidth(final IntOrPercentageHolder minWidth) {
		this.minWidth.setValue(minWidth);
		setShowAreaXPositionOnScrolledArea(0);
	}
	
	//method
	private void setProposalHeight(final IntOrPercentageHolder proposalHeight) {
		
		IntOrPercentageHolderValidator.INSTANCE.assertIsPositive(proposalHeight);
		
		this.proposalHeight.setValue(proposalHeight);
		setShowAreaYPositionOnScrolledArea(0);
	}
	
	//method
	private void setProposalWidth(final IntOrPercentageHolder proposalWidth) {
		
		IntOrPercentageHolderValidator.INSTANCE.assertIsPositive(proposalWidth);
		
		this.proposalWidth.setValue(proposalWidth);
		setShowAreaXPositionOnScrolledArea(0);
	}
}

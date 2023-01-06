//package declaration
package ch.nolix.core.document.node;

import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.pair.IntPair;
import ch.nolix.core.document.xml.XMLNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//class
/**
 * A {@link BaseNode} can have:
 * -1 header
 * -several attributes which are a {@link BaseNode}s
 *  
 * @author Silvan Wyss
 * @date 2017-06-24
 * @param <BN> is the type of a {@link BaseNode}.
 */
public abstract class BaseNode<BN extends BaseNode<BN>> implements INode<BN> {
	
	//constant
	public static final String COMMA_CODE = "$M";
	
	//constant
	public static final String DOLLAR_SYMBOL_CODE = "$X";
	
	//constant
	public static final String OPEN_BRACKET_CODE = "$O";
	
	//constant
	public static final String CLOSED_BRACKET_CODE = "$C";
	
	//static method
	/**
	 * @param string
	 * @return an escape {@link String} for the given string.
	 */
	public static String getEscapeStringFor(final String string) {
		
		final var stringBuilder = new StringBuilder();
		
		for (var i = 0; i < string.length(); i++) {
			switch (string.charAt(i)) {
				case CharacterCatalogue.COMMA:
					stringBuilder.append(COMMA_CODE);
					break;
				case CharacterCatalogue.DOLLAR:
					stringBuilder.append(DOLLAR_SYMBOL_CODE);
					break;
				case CharacterCatalogue.OPEN_BRACKET:
					stringBuilder.append(OPEN_BRACKET_CODE);
					break;
				case CharacterCatalogue.CLOSED_BRACKET:
					stringBuilder.append(CLOSED_BRACKET_CODE);
					break;
				default:
					stringBuilder.append(string.charAt(i));
			}
		}
		
		return stringBuilder.toString();
	}
	
	//static method
	/**
	 * @param escapeString
	 * @return an origin {@link String} from the given escapeString.
	 */
	public static String getOriginStringFromEscapeString(final String escapeString) {
			
		return
		escapeString
		.replace(COMMA_CODE, String.valueOf(CharacterCatalogue.COMMA))
		.replace(OPEN_BRACKET_CODE, String.valueOf(CharacterCatalogue.OPEN_BRACKET))
		.replace(CLOSED_BRACKET_CODE, String.valueOf(CharacterCatalogue.CLOSED_BRACKET))
		
		//It is essential to replace the dollar symbol code at last.
		.replace(DOLLAR_SYMBOL_CODE, String.valueOf(CharacterCatalogue.DOLLAR));
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} contains attributes.
	 */
	@Override
	public final boolean containsChildNodes() {
		return getRefChildNodes().containsAny();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsChildNodeThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().containsAny(selector::getOutput);
	}
	
	//method
	/**
	 * @param header
	 * @return true if the current {@link BaseNode} contains an attribute with the given header.
	 */
	@Override
	public final boolean containsChildNodeWithHeader(final String header) {
		return containsChildNodeThat(a -> a.hasHeader(header));
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} contains exactly 1 attribute.
	 */
	@Override
	public final boolean containsOneChildNode() {
		return getRefChildNodes().containsOne();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getChildNodeCount() {
		return getRefChildNodes().getElementCount();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getHeaderOrEmptyString() {
		
		//Handles the case that current BaseNode<?> does not have a header.
		if (!hasHeader()) {
			return StringCatalogue.EMPTY_STRING;
		}
		
		//Handles the case that current BaseNode<?> has a header.
		return getHeader();
	}
	

	
	//method
	/**
	 * @return the headers of the attributes of the current {@link BaseNode}.
	 */
	@Override
	public final IContainer<String> getChildNodesHeaders() {
		return getRefChildNodes().to(INode::getHeader);
	}
	
	//method declaration
	/**
	 * @param index
	 * @return the attribute at the given index from the current {@link BaseNode}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseNode} does not contain an attribute at the given index.
	 */
	@Override
	public final BN getRefChildNodeAt1BasedIndex(final int index) {
		return getRefChildNodes().getRefAt1BasedIndex(index);
	}
	
	//method declaration
	/**
	 * 
	 * @param header
	 * @return the attributes of the current {@link BaseNode} that have the given header.
	 */
	@Override
	public final IContainer<BN> getRefChildNodesWithHeader(final String header) {
		return getRefChildNodesThat(a -> a.hasHeader(header));
	}
	
	//method declaration
	/**
	 * @param selector
	 * @return the attributes the given selector selects from the current {@link BaseNode}.
	 */
	@Override
	public final IContainer<BN> getRefChildNodesThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().getRefSelected(selector);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final BN getRefFirstChildNode() {
		return getRefChildNodes().getRefFirst();
	}
	
	//method
	/**
	 * @return the one attribute of the current {@link Node}
	 * @throws EmptyArgumentException if the current {@link Node} is empty.
	 * @throws InvalidArgumentException if the current {@link Node} contains several attributes.
	 */
	@Override
	public final BN getRefSingleChildNode() {
		return getRefChildNodes().getRefOne();
	}
	
	//method
	/**
	 * @param selector
	 * @return the first attribute the given selector selects from the current {@link BaseNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link BaseNode} does not contain an attribute the given selector selects.
	 */
	@Override
	public final BN getRefFirstChildNodeThat(IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().getRefFirst(selector);
	}
	
	//method
	/**
	 * @param selector
	 * @return the first attribute the given selector selects from the current {@link BaseNode} or null.
	 */
	@Override
	public final BN getRefFirstChildNodeThatOrNull(IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().getRefFirstOrNull(selector);
	}
	
	//method
	/**
	 * @param header
	 * @return the first attribute of the current {@link BaseNode} with the given header.
	 */
	@Override
	public final BN getRefFirstChildNodeWithHeader(final String header) {
		return getRefFirstChildNodeThat(a -> a.hasHeader(header));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean getSingleChildNodeAsBoolean() {
		return getRefSingleChildNode().toBoolean();
	}

	//method
	/**
	 * @return the double the one attribute of the current {@link BaseNode} represents.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link BaseNode} does not represent a double.
	 */
	@Override
	public final double getSingleChildNodeAsDouble() {
		return getRefSingleChildNode().toDouble();
	}

	//method
	/**
	 * @return the integer the one attribute of the current {@link BaseNode} represents.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link BaseNode} does not represent an integer.
	 */
	@Override
	public final int getSingleChildNodeAsInt() {
		return getRefSingleChildNode().toInt();
	}

	//method
	/**
	 * @return the long the one attribute of the current {@link BaseNode} represents.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link BaseNode} does not represent a long.
	 */
	@Override
	public final long getSingleChildNodeAsLong() {
		return getRefSingleChildNode().toLong();
	}

	//method
	/**
	 * @return the header of the one attribute of the current {@link BaseNode}.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 */
	@Override
	public final String getSingleChildNodeHeader() {
		return getRefSingleChildNode().getHeader();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasHeader(final String header) {
		
		//Handles the case that current BaseNode<?> does not have a header.
		if (!hasHeader()) {
			return false;
		}
		
		//Handles the case that current BaseNode<?> has a header.
		return getHeader().equals(header);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} does not have a header and does not contain attributes.
	 */
	@Override
	public final boolean isBlank() {
		return (!hasHeader() && !containsChildNodes());
	}
	
	//method
	/**
	 * Saves the current {@link BaseNode} to the file with the given file path.
	 * 
	 * @param path
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws InvalidArgumentException if the given path is blank.
	 * @throws InvalidArgumentException if there exists already a file system item with the given path.
	 */
	public final void saveToFile(final String path) {
		
		//Calls other method.
		saveToFile(path, WriteMode.THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY);
	}
	
	//method
	/**
	 * Saves the current {@link BaseNode} to the file with the given path.
	 * 
	 * @param path
	 * @param writeMode
	 * @throws ArgumentIsNullException if the given path is null.
	 * @throws InvalidArgumentException if the given path is blank.
	 * @throws InvalidArgumentException
	 * if the given writeMode flag={@link WriteMode#THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY}
	 * and there exists already a file system item with the given path.
	 */
	public final void saveToFile(final String path, final WriteMode writeMode) {
		FileSystemAccessor.createFile(path, writeMode, toFormattedString());
	}
	
	//method
	/**
	 * @return the boolean the current {@link BaseNode} represents.
	 * @throws UnrepresentingArgumentException if the current {@link BaseNode} does not represent a boolean.
	 */
	@Override
	public final boolean toBoolean() {
		return GlobalStringHelper.toBoolean(toString());
	}
	
	//method
	/**
	 * @return the double the current {@link BaseNode} represents.
	 * @throws InvalidArgumentException if the current {@link BaseNode} does not represent a double.
	 */
	@Override
	public final double toDouble() {
		return GlobalStringHelper.toDouble(toString());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toFormattedString() {
		return toFormattedString(0);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int toInt() {
		
		if (!hasHeader() || containsChildNodes()) {
			throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
		}
		
		try {
			return Integer.parseInt(getHeader());
		} catch (final NumberFormatException numberFormatException) {
			throw UnrepresentingArgumentException.forArgumentAndType(this, Integer.class);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IntPair toIntPair() {
		
		//Asserts that the current BaseNode contains 2 attributes.
		if (getChildNodeCount() != 2) {
			throw UnrepresentingArgumentException.forArgumentAndType(this, IntPair.class);
		}
		
		return new IntPair(getRefChildNodeAt1BasedIndex(1).toInt(), getRefChildNodeAt1BasedIndex(2).toInt());
	}
	
	//method
	/**
	 * @return the long the current {@link BaseNode} represents.
	 * @throws InvalidArgumentException if the current {@link BaseNode} does not represent a long.
	 */
	@Override
	public final long toLong() {
		return GlobalStringHelper.toLong(toString());
	}
	
	//method
	/**
	 * @return a {@link String} representation of the current {@link BaseNode}.
	 */
	@Override
	public final String toString() {
		
		final var stringBuilder = new StringBuilder();
		
		//Handles the case that the current specification has a header.
		if (hasHeader()) {
			stringBuilder.append(getReproducingHeader());
		}
		
		//Handles the case that the current specification contains attributes.
		if (containsChildNodes()) {
			stringBuilder
			.append(CharacterCatalogue.OPEN_BRACKET)
			.append(getRefChildNodes().toString())
			.append(CharacterCatalogue.CLOSED_BRACKET);
		}
		
		return stringBuilder.toString();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final XMLNode toXML() {
		
		//Creates an XMLNode.
		final var lXMLNode =
		new XMLNode()
		.setName(getHeader());
		
		//Iterates the attributes of the current specification.
		for (final BaseNode<?> a : getRefChildNodes()) {
			
			//Handles the case that the current attribute does not contain attributes.
			if (!a.containsChildNodes()) {
				lXMLNode.setValue(a.toString());
				
			//Handles the case that the current attribute contains attributes.
			} else {
				lXMLNode.addChildNode(a.toXML());
			}
		}
		
		return lXMLNode;
	}
	
	//method
	/**
	 * @param node
	 * @return true if the current {@link BaseNode} equals the given node.
	 */
	protected final boolean equalsNode(final BaseNode<?> node) {
		
		if (!hasHeader()) {
			if (node.hasHeader()) {
				return false;
			}
			
		} else {
			if (!node.hasHeader(getHeader())) {
				return false;
			}
		}
		
		if (getChildNodeCount() != node.getChildNodeCount()) {
			return false;
		}
		
		final var iterator = node.getRefChildNodes().iterator();
		for (final BN a : getRefChildNodes()) {
			if (!a.equals(iterator.next())) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	private void appendFormattedStringRepresentationOfChildNodesToStringBuilder(
		final int leadingTabulators,
		final StringBuilder stringBuilder
	) {
		
		//Handles the case that all attributes of the current specification do not contain any attributes.
		if (getRefChildNodes().containsNone(INode::containsChildNodes)) {
			stringBuilder
			.append(CharacterCatalogue.OPEN_BRACKET)
			.append(getRefChildNodes().toString())
			.append(CharacterCatalogue.CLOSED_BRACKET);
			
		//Handles the case that the current specification contains attributes with attributes.
		} else {
			
			stringBuilder
			.append(CharacterCatalogue.OPEN_BRACKET)
			.append(CharacterCatalogue.NEW_LINE);
			
			//Iterates the attributes of the current specification.
			final var attributeCount = getChildNodeCount();
			var index = 1;
			for (final BaseNode<?> a : getRefChildNodes()) {
				
				stringBuilder.append(a.toFormattedString(leadingTabulators + 1));
				
				if (index < attributeCount) {
					stringBuilder.append(CharacterCatalogue.COMMA);
				}
				
				stringBuilder.append(CharacterCatalogue.NEW_LINE);
				
				index++;
			}
			
			stringBuilder
			.append(GlobalStringHelper.createTabulators(leadingTabulators))
			.append(CharacterCatalogue.CLOSED_BRACKET);
		}
	}
	
	//method
	/**
	 * @return a reproducing {@link String} representation of the header of the current {@link BaseNode}.
	 */
	private String getReproducingHeader() {
		return getEscapeStringFor(getHeader());
	}
	
	//method
	/**
	 * @param leadingTabulators
	 * @return a formated {@link String} representation of the current {@link BaseNode}
	 * with as many leading tabulators as the given leading tabulator count says.
	 */
	private String toFormattedString(final int leadingTabulators) {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder.append(GlobalStringHelper.createTabulators(leadingTabulators));
		
		//Handles the case that the current specification has a header.
		if (hasHeader()) {
			stringBuilder.append(getReproducingHeader());
		}
		
		//Handles the case that the current specification contains attributes.
		if (containsChildNodes()) {
			appendFormattedStringRepresentationOfChildNodesToStringBuilder(leadingTabulators, stringBuilder);
		}
		
		return stringBuilder.toString();
	}
}

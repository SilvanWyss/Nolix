//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.commontype.commontypeconstant.CharacterCatalogue;
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.pair.IntPair;
import ch.nolix.core.document.xml.XMLNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;
import ch.nolix.coreapi.programcontrolapi.processproperty.WriteMode;

//class
/**
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsChildNodeWithHeader(final String header) {
		return containsChildNodeThat(a -> a.hasHeader(header));
	}
	
	//method
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<String> getChildNodesHeaders() {
		return getRefChildNodes().to(INode::getHeader);
	}
	
	//method declaration
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final BN getRefChildNodeAt1BasedIndex(final int index) {
		return getRefChildNodes().getRefAt1BasedIndex(index);
	}
	
	//method declaration
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<BN> getRefChildNodesWithHeader(final String header) {
		return getRefChildNodesThat(a -> a.hasHeader(header));
	}
	
	//method declaration
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public final BN getRefSingleChildNode() {
		return getRefChildNodes().getRefOne();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final BN getRefFirstChildNodeThat(IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().getRefFirst(selector);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final BN getRefFirstChildNodeThatOrNull(IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().getRefFirstOrNull(selector);
	}
	
	//method
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public final double getSingleChildNodeAsDouble() {
		return getRefSingleChildNode().toDouble();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getSingleChildNodeAsInt() {
		return getRefSingleChildNode().toInt();
	}
	
	//method
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public final boolean toBoolean() {
		return GlobalStringHelper.toBoolean(toString());
	}
	
	//method
	/**
	 * {@inheritDoc}
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
		
		//Asserts that the current BaseNode contains 2 child nodes.
		if (getChildNodeCount() != 2) {
			throw UnrepresentingArgumentException.forArgumentAndType(this, IntPair.class);
		}
		
		return new IntPair(getRefChildNodeAt1BasedIndex(1).toInt(), getRefChildNodeAt1BasedIndex(2).toInt());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final var stringBuilder = new StringBuilder();
		
		//Handles the case that the current specification has a header.
		if (hasHeader()) {
			stringBuilder.append(getReproducingHeader());
		}
		
		//Handles the case that the current BaseNode contains child nodes.
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
		
		//Iterates the child nodes of the current BaseNode.
		for (final BaseNode<?> cn : getRefChildNodes()) {
			
			//Handles the case that the current child node itself does not contain child nodes.
			if (!cn.containsChildNodes()) {
				lXMLNode.setValue(cn.toString());
				
			//Handles the case that the current child node itself contains child nodes.
			} else {
				lXMLNode.addChildNode(cn.toXML());
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
		
		//Handles the case that all child nodes of the current BaseNode themselves do not contain child nodes.
		if (getRefChildNodes().containsNone(INode::containsChildNodes)) {
			stringBuilder
			.append(CharacterCatalogue.OPEN_BRACKET)
			.append(getRefChildNodes().toString())
			.append(CharacterCatalogue.CLOSED_BRACKET);
			
		//Handles the case that the current BaseNode contains child nodes that themselves contains child nodes.
		} else {
			
			stringBuilder
			.append(CharacterCatalogue.OPEN_BRACKET)
			.append(CharacterCatalogue.NEW_LINE);
			
			//Iterates the child nodes of the current BaseNode.
			final var attributeCount = getChildNodeCount();
			var index = 1;
			for (final BaseNode<?> cn : getRefChildNodes()) {
				
				stringBuilder.append(cn.toFormattedString(leadingTabulators + 1));
				
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
		
		//Handles the case that the current BaseNode contains child nodes.
		if (containsChildNodes()) {
			appendFormattedStringRepresentationOfChildNodesToStringBuilder(leadingTabulators, stringBuilder);
		}
		
		return stringBuilder.toString();
	}
}

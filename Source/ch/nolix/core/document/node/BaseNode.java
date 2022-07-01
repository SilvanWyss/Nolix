//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.container.pair.IntPair;
import ch.nolix.core.document.xml.XMLNode;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.programcontrol.processproperty.WriteMode;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerBooleanGetter;

//class
/**
 * A {@link BaseNode} can have:
 * -1 header
 * -several attributes which are a {@link BaseNode}s
 *  
 * @author Silvan Wyss
 * @date 2017-06-24
 * @param <T> is the type of a {@link BaseNode}.
 */
public abstract class BaseNode<T extends BaseNode<T>> implements INode<T> {
	
	//constants
	public static final String COMMA_CODE = "$M";
	public static final String DOLLAR_SYMBOL_CODE = "$X";
	public static final String OPEN_BRACKET_CODE = "$O";
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
	public boolean containsChildNodes() {
		return getRefChildNodes().containsAny();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsChildNodeThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().containsAny(selector::getOutput);
	}
	
	//method
	/**
	 * @param header
	 * @return true if the current {@link BaseNode} contains an attribute with the given header.
	 */
	public boolean containsChildNodeWithHeader(final String header) {
		return containsChildNodeThat(a -> a.hasHeader(header));
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} contains exactly 1 attribute.
	 */
	public boolean containsOneChildNode() {
		return getRefChildNodes().containsOne();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildNodeCount() {
		return getRefChildNodes().getElementCount();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHeaderOrEmptyString() {
		
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
	public boolean equals(final Object object) {
		
		//Handles the case that the given object is not a BaseNode.
		if (!(object instanceof BaseNode)) {
			return false;
		}
		
		final var baseNode = (BaseNode<?>)object;
		
		//Handles the case that the current BaseNode<?> does not have a header.
		if (!hasHeader()) {
			if (baseNode.hasHeader()) {
				return false;
			}
			
		//Handles the case that the current BaseNode<?> has a header.
		} else {
			if (!baseNode.hasHeader(getHeader())) {
				return false;
			}
		}
		
		if (getChildNodeCount() != baseNode.getChildNodeCount()) {
			return false;
		}
		
		//Iterates the attributes of the current BaseNode.
		final var iterator = baseNode.getRefChildNodes().iterator();
		for (final var a : getRefChildNodes()) {
			if (!a.equals(iterator.next())) {
				return false;
			}
		}
		
		return true;
	}
	
	//method
	/**
	 * @return the headers of the attributes of the current {@link BaseNode}.
	 */
	public IContainer<String> getChildNodesHeaders() {
		return getRefChildNodes().to(BaseNode::getHeader);
	}
	
	//method
	/**
	 * @return the boolean the one attribute of the current {@link BaseNode} represents.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link BaseNode} does not represent a boolean.
	 */
	@SuppressWarnings("all")
	public boolean getSingleChildNodeAsBoolean() {
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
	public double getSingleChildNodeAsDouble() {
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
	public int getSingleChildNodeAsInt() {
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
	public long getSingleChildNodeAsLong() {
		return getRefSingleChildNode().toLong();
	}
	
	//method
	/**
	 * @return the header of the one attribute of the current {@link BaseNode}.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 */
	public String getSingleChildNodeHeader() {
		return getRefSingleChildNode().getHeader();
	}
	
	//method declaration
	/**
	 * @param index
	 * @return the attribute at the given index from the current {@link BaseNode}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseNode} does not contain an attribute at the given index.
	 */
	public T getRefChildNodeAt1BasedIndex(final int index) {
		return getRefChildNodes().getRefAt(index);
	}
	
	//method declaration
	/**
	 * 
	 * @param header
	 * @return the attributes of the current {@link BaseNode} that have the given header.
	 */
	public IContainer<T> getRefChildNodesWithHeader(final String header) {
		return getRefChildNodesThat(a -> a.hasHeader(header));
	}
	
	//method declaration
	/**
	 * @param selector
	 * @return the attributes the given selector selects from the current {@link BaseNode}.
	 */
	public IContainer<T> getRefChildNodesThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().getRefSelected(selector);
	}
	
	//method
	/**
	 * @return the one attribute of the current {@link Node}
	 * @throws EmptyArgumentException if the current {@link Node} is empty.
	 * @throws InvalidArgumentException if the current {@link Node} contains several attributes.
	 */
	public T getRefSingleChildNode() {
		return getRefChildNodes().getRefOne();
	}
	
	//method
	/**
	 * @param selector
	 * @return the first attribute the given selector selects from the current {@link BaseNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link BaseNode} does not contain an attribute the given selector selects.
	 */
	public T getRefFirstChildNodeThat(IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().getRefFirst(selector);
	}
	
	//method
	/**
	 * @param selector
	 * @return the first attribute the given selector selects from the current {@link BaseNode} or null.
	 */
	public T getRefFirstChildNodeThatOrNull(IElementTakerBooleanGetter<INode<?>> selector) {
		return getRefChildNodes().getRefFirstOrNull(selector);
	}
	
	//method
	/**
	 * @param header
	 * @return the first attribute of the current {@link BaseNode} with the given header.
	 */
	public T getRefFirstChildNodeWithHeader(final String header) {
		return getRefFirstChildNodeThat(a -> a.hasHeader(header));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasHeader(final String header) {
		
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
	public boolean isBlank() {
		return (!hasHeader() && !containsChildNodes());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
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
	public void saveToFile(final String path) {
		
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
	public void saveToFile(final String path, final WriteMode writeMode) {
		FileSystemAccessor.createFile(path, writeMode, toFormattedString());
	}
	
	//method
	/**
	 * @return the boolean the current {@link BaseNode} represents.
	 * @throws UnrepresentingArgumentException if the current {@link BaseNode} does not represent a boolean.
	 */
	public boolean toBoolean() {
		return GlobalStringHelper.toBoolean(toString());
	}
	
	//method
	/**
	 * @return the double the current {@link BaseNode} represents.
	 * @throws InvalidArgumentException if the current {@link BaseNode} does not represent a double.
	 */
	public double toDouble() {
		return GlobalStringHelper.toDouble(toString());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final String toFormattedString() {
		return toFormattedString(0);
	}
	
	//method
	/**
	 * @return the integer the current {@link BaseNode} represents.
	 * @throws InvalidArgumentException if the current {@link BaseNode} does not represent a int.
	 */
	public int toInt() {
		return GlobalStringHelper.toInt(toString());
	}
	
	//method
	/**
	 * @return the {@link IntPair} the current {@link BaseNode} represents.
	 * @throws UnrepresentingArgumentException if the current {@link BaseNode} does not represent a {@link IntPair}.
	 */
	public IntPair toIntPair() {
		
		//Asserts that the current BaseNode<?> contains 2 attributes.
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
	public long toLong() {
		return GlobalStringHelper.toLong(toString());
	}
	
	//method
	/**
	 * @return a {@link String} representation of the current {@link BaseNode}.
	 */
	@Override
	public String toString() {
		
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
	 * @return a {@link XMLNode} representation of the current {@link BaseNode}.
	 */
	public XMLNode toXML() {
		
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
			
			//Handles the case that all attributes of the current specification do not contain any attributes.
			if (getRefChildNodes().containsNone(BaseNode::containsChildNodes)) {
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
		
		return stringBuilder.toString();
	}
}

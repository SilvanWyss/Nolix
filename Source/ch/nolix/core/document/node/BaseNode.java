//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.attributeuniversalapi.mutableoptionalattributeuniversalapi.OptionalHeaderable;
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.container.pair.IntPair;
import ch.nolix.core.document.xml.XMLNode;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementTakerBooleanGetter;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.processproperty.WriteMode;

//class
/**
 * A {@link BaseNode} can have:
 * -1 header
 * -several attributes which are a {@link BaseNode}s
 *  
 * @author Silvan Wyss
 * @date 2017-06-24
 */
public abstract class BaseNode implements OptionalHeaderable<BaseNode> {
	
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
	
	//method declaration
	/**
	 * Adds the given attribute to the current {@link BaseNode}.
	 * 
	 * @param attribute
	 * @return the current {@link BaseNode}
	 */
	public abstract BaseNode addAttribute(BaseNode attribute);
	
	//method
	/**
	 * Adds the given attributes to the current {@link BaseNode}.
	 * 
	 * @param attributes
	 * @return the current {@link BaseNode}
	 */
	public BaseNode addAttribute(final BaseNode... attributes) {

		//Iterates the given attributes.
		for (final var a : attributes) {
			addAttribute(a);
		}

		return this;
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link BaseNode}.
	 * 
	 * @param attribute
	 * @throws UnrepresentingArgumentException if the given attribute does not represent a {@link Node}.
	 */
	public void addAttribute(final String attribute) {
		
		//Calls other method
		addAttribute(Node.fromString(attribute));
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link BaseNode}.
	 * 
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
	 * @return the current {@link BaseNode}
	 */
	public <BN extends BaseNode> BaseNode addAttributes(final Iterable<BN> attributes) {
		
		//Iterates the given attributes.
		attributes.forEach(this::addAttribute);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given postfix to the header of the current {@link BaseNode}.
	 * Sets the given postfix as header to the current {@link BaseNode} if it does not have a header.
	 * 
	 * @param postfix
	 * @throws ArgumentIsNullException if the given postfix is null.
	 * @throws InvalidArgumentException if the given postfix is blank.
	 */
	public void addPostfixToHeader(final String postfix) {
		
		//Asserts that the given postfix is not null or blank.
		GlobalValidator.assertThat(postfix).thatIsNamed(LowerCaseCatalogue.POSTFIX).isNotBlank();
		
		//Handles the case that the current Node does not have a header.
		if (hasHeader()) {
			setHeader(postfix);
			
		//Handles the case that the current Node has a header.
		} else {
			setHeader(getHeader() + postfix);
		}
	}
	
	//method
	/**
	 * Adds the given prefix to the header of the current {@link BaseNode}.
	 * Sets the given prefix as header to the current {@link BaseNode} if it does not have a header.
	 * 
	 * @param prefix
	 * @throws ArgumentIsNullException if the given prefix is null.
	 * @throws InvalidArgumentException if the given prefix is blank.
	 */
	public void addPrefixToHeader(final String prefix) {
		
		//Asserts that the given prefix is not null or blank.
		GlobalValidator.assertThat(prefix).thatIsNamed(LowerCaseCatalogue.PREFIX).isNotBlank();
		
		//Handles the case that the current BaseNode does not have a header.
		if (!hasHeader()) {
			setHeader(prefix);
			
		//Handles the case that the current BaseNode has a header.
		} else {
			setHeader(prefix + getHeader());
		}
	}
	
	//method
	/**
	 * @param selector
	 * @return true if the current {@link BaseNode} contains an attribute the given selector selects.
	 */
	public boolean containsAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		return getRefAttributes().containsAny(selector);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} contains attributes.
	 */
	public boolean containsAttributes() {
		return getRefAttributes().containsAny();
	}
	
	//method
	/**
	 * @param header
	 * @return true if the current {@link BaseNode} contains an attribute with the given header.
	 */
	public boolean containsAttributeWithHeader(final String header) {
		return containsAttribute(a -> a.hasHeader(header));
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} contains exactly 1 attribute.
	 */
	public boolean containsOneAttribute() {
		return getRefAttributes().containsOne();
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} contains exactly 1 attribute, that has a header.
	 */
	public boolean containsOneAttributeWithHeader() {
		return (containsOneAttribute() && getRefOneAttribute().hasHeader());
	}
		
	//method
	/**
	 * @return a new copy of the current {@link BaseNode}.
	 */
	public Node getCopy() {
		
		final var copy = new Node();
		
		//Handles the case that the current Node has a header.
		if (hasHeader()) {
			copy.setHeader(getHeader());
		}
		
		//Iterates the attributes of the current Node.
		for (final var a : getRefAttributes()) {
			copy.addAttribute(a.getCopy());
		}
		
		return copy;
	}
	
	//method
	/**
	 * @param header
	 * @return a new copy of the current {@link BaseNode} with the given header.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	public Node getCopyWithHeader(final String header) {
		return getCopy().setHeader(header);
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
		
		final var baseNode = (BaseNode)object;
		
		//Handles the case that the current BaseNode does not have a header.
		if (!hasHeader()) {
			if (baseNode.hasHeader()) {
				return false;
			}
			
		//Handles the case that the current BaseNode has a header.
		} else {
			if (!baseNode.hasHeader(getHeader())) {
				return false;
			}
		}
		
		if (getAttributeCount() != baseNode.getAttributeCount()) {
			return false;
		}
		
		//Iterates the attributes of the current BaseNode.
		final var iterator = baseNode.getRefAttributes().iterator();
		for (final var a : getRefAttributes()) {
			if (!a.equals(iterator.next())) {
				return false;
			}
		}
		
		return true;
	}
	
	//method declaration
	/**
	 * @return the number of attributes of the current {@link BaseNode}.
	 */
	public abstract int getAttributeCount();
	
	//method
	/**
	 * @return a {@link String} representations of the attributes of the current {@link BaseNode}.
	 */
	public IContainer<String> getAttributesAsStrings() {
		return getRefAttributes().to(BaseNode::toString);
	}
	
	//method
	/**
	 * @return the integer the first attribute of the current {@link BaseNode} represents.
	 * @throws InvalidArgumentException if the current {@link BaseNode} does not have attributes.
	 * @throws InvalidArgumentException
	 * if the first attribute of the current {@link BaseNode} does not represent an integer.
	 */
	public int getFirstAttributeAsInt() {
		return getRefFirstAttribute().toInt();
	}
	
	//method
	/**
	 * @return the long the first attribute of the current {@link BaseNode} represents.
	 * @throws InvalidArgumentException if the current {@link BaseNode} does not have attributes.
	 * @throws InvalidArgumentException
	 * if the first attribute of the current {@link BaseNode} does not represent a long.
	 */
	public long getFirstAttributeAsLong() {
		return getRefFirstAttribute().toLong();
	}
	
	//method
	/**
	 * @return the headers of the attributes of the current {@link BaseNode}.
	 */
	public IContainer<String> getHeadersOfAttributes() {
		return getRefAttributes().to(BaseNode::getHeader);
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
	public boolean getOneAttributeAsBoolean() {
		return getRefOneAttribute().toBoolean();
	}
	
	//method
	/**
	 * @return the double the one attribute of the current {@link BaseNode} represents.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link BaseNode} does not represent a double.
	 */
	public double getOneAttributeAsDouble() {
		return getRefOneAttribute().toDouble();
	}
	
	//method
	/**
	 * @return the integer the one attribute of the current {@link BaseNode} represents.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link BaseNode} does not represent an integer.
	 */
	public int getOneAttributeAsInt() {
		return getRefOneAttribute().toInt();
	}
	
	//method
	/**
	 * @return the long the one attribute of the current {@link BaseNode} represents.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link BaseNode} does not represent a long.
	 */
	public long getOneAttributeAsLong() {
		return getRefOneAttribute().toLong();
	}
	
	//method
	/**
	 * @return the header of the one attribute of the current {@link BaseNode}.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 */
	public String getOneAttributeHeader() {
		return getRefOneAttribute().getHeader();
	}
	
	//method declaration
	/**
	 * @param index
	 * @return the attribute at the given index from the current {@link BaseNode}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseNode} does not contain an attribute at the given index.
	 */
	public BaseNode getRefAttributeAt(final int index) {
		return getRefAttributes().getRefAt(index);
	}
	
	//method declaration
	/**
	 * @return the attributes of the current {@link BaseNode}.
	 */
	public abstract IContainer<BaseNode> getRefAttributes();
	
	//method declaration
	/**
	 * 
	 * @param header
	 * @return the attributes of the current {@link BaseNode} that have the given header.
	 */
	public IContainer<BaseNode> getRefAttributes(final String header) {
		return getRefAttributes(a -> a.hasHeader(header));
	}
	
	//method declaration
	/**
	 * @param selector
	 * @return the attributes the given selector selects from the current {@link BaseNode}.
	 */
	public IContainer<BaseNode> getRefAttributes(final IElementTakerBooleanGetter<BaseNode> selector) {
		return getRefAttributes().getRefSelected(selector);
	}
	
	//method
	/**
	 * @param header
	 * @return the attributes of the first attribute with the given header
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Node} does not contain an attribute with the given header
	 */
	public IContainer<BaseNode> getRefAttributesOfFirstAttribute(String header) {
		return getRefAttributes().getRefFirst(a -> a.hasHeader(header)).getRefAttributes();
	}

	//method
	/**
	 * @return the one attribute of the current {@link Node}
	 * @throws EmptyArgumentException if the current {@link Node} is empty.
	 * @throws InvalidArgumentException if the current {@link Node} contains several attributes.
	 */
	public BaseNode getRefOneAttribute() {
		return getRefAttributes().getRefOne();
	}
	
	//method
	/**
	 * @param header
	 * @return the one attribute of the first attribute with the given header
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link Node} does not contain an attribute with the given header.
	 * @throws EmptyArgumentException
	 * if the first attribute of the current {@link Node} with the given header does not contain an attribute.
	 * @throws InvalidArgumentException
	 * if the first attribute of the current {@link Node} with the given header contains several attributes.
	 */
	public BaseNode getRefOneAttributeOfFirstAttribute(String header) {
		return getRefAttributes().getRefFirst(a -> a.hasHeader(header)).getRefOneAttribute();
	}
	
	//method
	/**
	 * @param header
	 * @return a {@link String} representation
	 * of the one attribute of the first attribute with the given header of the current {@link Node}.
	 */
	public String getRefOneAttributeOfFirstAttributeAsString(String header) {
		return getRefOneAttributeOfFirstAttribute(header).toString();
	}
	
	//method
	/**
	 * @return the first attribute of the current {@link BaseNode}.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 */
	public BaseNode getRefFirstAttribute() {
		return getRefAttributes().getRefFirst();
	}
	
	//method
	/**
	 * @param selector
	 * @return the first attribute the given selector selects from the current {@link BaseNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link BaseNode} does not contain an attribute the given selector selects.
	 */
	public BaseNode getRefFirstAttribute(IElementTakerBooleanGetter<BaseNode> selector) {
		return getRefAttributes().getRefFirst(selector);
	}
	
	//method
	/**
	 * @param selector
	 * @return the first attribute the given selector selects from the current {@link BaseNode} or null.
	 */
	public BaseNode getRefFirstAttributeOrNull(IElementTakerBooleanGetter<BaseNode> selector) {
		return getRefAttributes().getRefFirstOrNull(selector);
	}
	
	//method
	/**
	 * @param header
	 * @return the first attribute of the current {@link BaseNode} with the given header.
	 */
	public BaseNode getRefFirstAttribute(final String header) {
		return getRefFirstAttribute(a -> a.hasHeader(header));
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} does not have a header and does not contain attributes.
	 */
	public boolean isBlank() {
		return (!hasHeader() && !containsAttributes());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method declaration
	/**
	 * Removes the first attribute the given selector selects from the current {@link BaseNode}.
	 * 
	 * @param selector
	 * @return the first attribute the given selector selects.
	 */
	public abstract BaseNode removeAndGetRefFirstAttribute(IElementTakerBooleanGetter<BaseNode> selector);
	
	//method declaration
	/**
	 * Removes the attributes of the current {@link BaseNode}.
	 */
	public abstract void removeAttributes();
	
	//method declaration
	/**
	 * Removes the first attribute the given selector selects from the current {@link BaseNode}.
	 * 
	 * @param selector
	 */
	public abstract void removeFirstAttribute(IElementTakerBooleanGetter<BaseNode> selector);
	
	//method
	/**
	 * Removes the first attribute with the given header from the current {@link BaseNode}.
	 * 
	 * @param header
	 */
	public void removeFirstAttribute(final String header) {
		removeFirstAttribute(a -> a.hasHeader(header));
	}
	
	//method declaration
	/**
	 * Replaces the first attribute of the current {@link BaseNode}, that has the given header, with
	 * the given attribute.
	 * 
	 * @param header
	 * @param attribute
	 */
	public abstract void replaceFirstAttributeHavingGivenHeaderWithGivenAttribute(String header, BaseNode attribute);
	
	//method
	/**
	 * Removes the header and the attributes of the current {@link BaseNode}.
	 */
	public void reset() {
		removeHeader();
		removeAttributes();
	}
	
	//method
	/**
	 * Resets the attributes of the current {@link BaseNode} with the given attributes.
	 * 
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
	 */
	public <BN extends BaseNode> void resetAttributes(final Iterable<BN> attributes) {
		removeAttributes();
		addAttributes(attributes);
	}
	
	//method
	/**
	 * Resets the current {@link BaseNode} from the file with the given file path.
	 * 
	 * @param filePath
	 */
	public void resetFromFile(final String filePath) {
		resetFromString(
			new FileAccessor(filePath)
			.readFile()
			.replace(String.valueOf(CharacterCatalogue.TABULATOR), StringCatalogue.EMPTY_STRING)
			.replace(String.valueOf(CharacterCatalogue.NEW_LINE), StringCatalogue.EMPTY_STRING)
		);
	}
	
	//method
	/**
	 * Resets the current {@link BaseNode} from the given string.
	 * 
	 * @param string
	 * @throws InvalidArgumentException if the given string is not valid.
	 */
	public void resetFromString(final String string) {
		
		reset();
		
		if (setAndGetEndIndex(string, 0) != string.length() - 1) {
			throw new UnrepresentingArgumentException(string, Node.class);
		}
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
		FileSystemAccessor.createFile(path, writeMode, toFormatedString());
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
	 * @return a formated {@link String} representation of the current {@link BaseNode}.
	 */
	public String toFormatedString() {
		return toFormatedString(0);
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
		
		//Asserts that the current BaseNode contains 2 attributes.
		if (getAttributeCount() != 2) {
			throw new UnrepresentingArgumentException(this, IntPair.class);
		}
		
		return new IntPair(getRefAttributeAt(1).toInt(), getRefAttributeAt(2).toInt());
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
		if (containsAttributes()) {
			stringBuilder
			.append(CharacterCatalogue.OPEN_BRACKET)
			.append(getRefAttributes().toString())
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
		for (final BaseNode a : getRefAttributes()) {
			
			//Handles the case that the current attribute does not contain attributes.
			if (!a.containsAttributes()) {
				lXMLNode.setValue(a.toString());
				
			//Handles the case that the current attribute contains attributes.
			} else {
				lXMLNode.addChildNode(a.toXML());
			}
		}
		
		return lXMLNode;
	}
	
	//method
	int setAndGetEndIndex(final String substring, final int startIndex) {
		
		var index = startIndex;
		
		var endIndex = -1;
		while (index < substring.length()) {
			
			var character = substring.charAt(index);
			
			if (character == '(') {
				break;
			} else if (character == ',' || character == ')') {
				endIndex = index - 1;
				break;
			}
			
			index++;
		}
		
		if (index > startIndex) {
			this.setHeader(getOriginStringFromEscapeString(substring.substring(startIndex, index)));
		}
		
		if (index == substring.length()) {
			return (index - 1);
		}
		
		if (endIndex != -1) {
			return endIndex;
		}
		
		if (index < substring.length()) {
			var node = new Node();
			index = node.setAndGetEndIndex(substring, index + 1) + 1;
			this.addAttribute(node);
		}
		
		while (index < substring.length()) {
			switch (substring.charAt(index)) {
				case ',':
					var node = new Node();
					index = node.setAndGetEndIndex(substring, index + 1) + 1;
					this.addAttribute(node);
					break;
				case ')':
					return index;
				default:
			}
		}
		
		throw new UnrepresentingArgumentException(substring, Node.class);
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
	private String toFormatedString(final int leadingTabulators) {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder.append(GlobalStringHelper.createTabulators(leadingTabulators));
		
		//Handles the case that the current specification has a header.
		if (hasHeader()) {
			stringBuilder.append(getReproducingHeader());
		}
		
		//Handles the case that the current specification contains attributes.
		if (containsAttributes()) {
			
			//Handles the case that all attributes of the current specification do not contain any attributes.
			if (getRefAttributes().containsNone(BaseNode::containsAttributes)) {
				stringBuilder
				.append(CharacterCatalogue.OPEN_BRACKET)
				.append(getRefAttributes().toString())
				.append(CharacterCatalogue.CLOSED_BRACKET);
				
			//Handles the case that the current specification contains attributes with attributes.
			} else {
				stringBuilder
				.append(CharacterCatalogue.OPEN_BRACKET)
				.append(CharacterCatalogue.NEW_LINE);
				
				//Iterates the attributes of the current specification.
				final var attributeCount = getAttributeCount();
				var index = 1;
				for (final BaseNode a : getRefAttributes()) {
					
					stringBuilder.append(a.toFormatedString(leadingTabulators + 1));
					
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

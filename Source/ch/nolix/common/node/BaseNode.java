//package declaration
package ch.nolix.common.node;

import ch.nolix.common.XML.XMLNode;
import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.common.commonTypeHelpers.StringHelper;
import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.fileSystem.FileAccessor;
import ch.nolix.common.fileSystem.FileSystemAccessor;
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.UnrepresentingArgumentException;
import ch.nolix.common.validator.Validator;

//abstract class
/**
 * A {@link BaseNode} can have:
 * -1 header
 * -several attributes that are a {@link BaseNode} themselves
 * 
 * The methods of a {@link BaseNode} are not final
 * that they can be overwritten by an implementation with a better performance.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 740
 */
public abstract class BaseNode implements Headered {
	
	//constants
	public static final String DOT_CODE = "$D";
	public static final String COMMA_CODE = "$M";
	public static final String DOLLAR_SYMBOL_CODE = "$X";
	public static final String OPEN_BRACKET_CODE = "$O";
	public static final String CLOSED_BRACKET_CODE = "$C";
	
	//static method
	/**
	 * @param string
	 * @return a reproducing string for the given string on the specification context.
	 * @throws ArgumentIsNullException if the given string is null.
	 */
	public static String createReproducingString(final String string) {
		
		//Checks if the given string is not null.
		Validator.suppose(string).isNotNull();
		
		return
			string
			
			//It is important that the dollar symbol is replaced at first.
			.replace(String.valueOf(CharacterCatalogue.DOLLAR), DOLLAR_SYMBOL_CODE)
			
			.replace(String.valueOf(CharacterCatalogue.DOT), DOT_CODE)
			.replace(String.valueOf(CharacterCatalogue.COMMA), COMMA_CODE)
			.replace(String.valueOf(CharacterCatalogue.OPEN_BRACKET), OPEN_BRACKET_CODE)
			.replace(String.valueOf(CharacterCatalogue.CLOSED_BRACKET), CLOSED_BRACKET_CODE);
	}
	
	//static method
	/**
	 * @param reproducingString
	 * @return an origin string from the given reproducing string on the specification context.
	 * @throws ArgumentIsNullException if the given reproducingString is null.
	 */
	public static String createOriginStringFromReproducingString(final String reproducingString) {
		
		//Checks if the given reproducing string is not null.
		Validator
		.suppose(reproducingString)
		.thatIsNamed("reproducing string")
		.isNotNull();
		
		return
			reproducingString
			.replace(DOT_CODE, String.valueOf(CharacterCatalogue.DOT))
			.replace(COMMA_CODE, String.valueOf(CharacterCatalogue.COMMA))
			.replace(OPEN_BRACKET_CODE, String.valueOf(CharacterCatalogue.OPEN_BRACKET))
			.replace(CLOSED_BRACKET_CODE, String.valueOf(CharacterCatalogue.CLOSED_BRACKET))
			
			//It is important that the dollar symbol code is replaced at last.
			.replace(DOLLAR_SYMBOL_CODE, String.valueOf(CharacterCatalogue.DOLLAR));
	}
	
	//method declaration
	/**
	 * Adds the given attribute to the current {@link BaseNode}.
	 * 
	 * @param attribute
	 */
	public abstract void addAttribute(BaseNode attribute);
	
	//method
	/**
	 * Adds the given attributes to the current {@link BaseNode}.
	 * 
	 * @param attributes
	 */
	public void addAttribute(final BaseNode... attributes) {
		
		//Calls other method.
		addAttributes(new ReadContainer<BaseNode>(attributes));
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link BaseNode}.
	 * 
	 * @param attributes
	 */
	public <S extends BaseNode> void addAttributes(final Iterable<S> attributes) {
		
		//Iterates the given attributes.
		attributes.forEach(a -> addAttribute(a));
	}
	
	//method
	/**
	 * @return true if all attributes of the current {@link BaseNode} do not have attributes
	 */
	public boolean allAttributesDoNotHaveAttributes() {
		return getRefAttributes().containsNone(s -> s.containsAttributes());
	}
	
	//method
	/**
	 * @param selector
	 * @return true if the current {@link BaseNode} contains an attribute the given selector selects.
	 */
	public boolean containsAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		return getRefAttributes().contains(a -> selector.getOutput(a));
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
		
		//Handles the case that the current document node has a header.
		if (hasHeader()) {
			copy.setHeader(getHeader());
		}
		
		getRefAttributes().forEach(a -> copy.addAttribute(a.getCopy()));
		
		return copy;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseNode} equals the given object.
	 */
	@Override
	public boolean equals(final Object object) {
		
		//Handles the case that the given object is not a document node.
		if (!(object instanceof Node)) {
			return false;
		}
		
		//Handles the case that the given object is a document node.
			final var baseNode = (BaseNode)object;
			
			//Handles the case that the current document node does not have a header.
			if (!hasHeader()) {
				if (baseNode.hasHeader()) {
					return false;
				}
			}
			
			//Handles the case that the current document node has a header.
			else {
				if (!baseNode.hasHeader(getHeader())) {
					return false;
				}
			}
			
			if (getAttributeCount() != baseNode.getAttributeCount()) {
				return false;
			}
									
			//Iterates the attributes of the current document node.
			final var iterator = baseNode.getRefAttributes().iterator();
			for (final var a : getRefAttributes()) {
				if (!a.equals(iterator.next())) {
					return false;
				}
			}
			
			return true;
	}
	
	//method
	/**
	 * @return the number of attributes of the current {@link BaseNode}.
	 */
	public int getAttributeCount() {
		return getRefAttributes().getSize();
	}
	
	//method
	/**
	 * @return string representations of the attributes of the current {@link BaseNode}.
	 */
	public List<String> getAttributesToStrings() {
		return getRefAttributes().to(a -> a.toString());
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
	 * @return the boolean the one attribute of the current {@link BaseNode} represents.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link BaseNode} does not represent a boolean.
	 */
	public final boolean getOneAttributeAsBoolean() {
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
	 * @return a string representation of the one attribute of the current {@link BaseNode}.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 */
	public String getOneAttributeAsString() {
		return getRefOneAttribute().toString();
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
	
	//method
	/**
	 * @return a reproducing string representation of the header of the current {@link BaseNode}.
	 */
	public String getReproducingHeader() {
		return createReproducingString(getHeader());
	}
	
	//method declaration
	/**
	 * @param index
	 * @return the attribute at the given index from the current {@link BaseNode}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseNode} does not contain an attribute at the given index.
	 */
	@SuppressWarnings("unchecked")
	public <S extends BaseNode> S getRefAttributeAt(final int index) {
		return (S)getRefAttributes().getRefAt(index);
	}
	
	//method declaration
	/**
	 * @return the attributes of the current {@link BaseNode}.
	 */
	public abstract <S extends BaseNode> IContainer<S> getRefAttributes();
	
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
	public IContainer<BaseNode> getRefAttributes(
		final IElementTakerBooleanGetter<BaseNode> selector
	) {
		return getRefAttributes().getRefSelected(a -> selector.getOutput(a));
	}
	
	//method
	/**
	 * @return the first attribute of the current {@link BaseNode}.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 */
	@SuppressWarnings("unchecked")
	public <S extends BaseNode> S getRefFirstAttribute() {
		return (S)getRefAttributes().getRefFirst();
	}
	
	//method
	/**
	 * @param selector
	 * @return the first attribute the given selector selects from the current {@link BaseNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseNode} does not contain an attribute the given selector selects.
	 */
	@SuppressWarnings("unchecked")
	public <S extends BaseNode> S getRefFirstAttribute(
		IElementTakerBooleanGetter<BaseNode> selector
	) {
		return (S)getRefAttributes().getRefFirst(a -> selector.getOutput(a));
	}
	
	//method
	/**
	 * @param header
	 * @return the first attribute of the current {@link BaseNode} with the given header.
	 */
	public <S extends BaseNode> S getRefFirstAttribute(final String header) {
		return getRefFirstAttribute(a -> a.hasHeader(header));
	}
	
	//method declaration
	/**
	 * @return the one attribute of the current {@link BaseNode}.
	 * @throws EmptyArgumentException if the current {@link BaseNode} does not contain attributes.
	 * @throws InvalidArgumentException if the current {@link BaseNode} contains several attributes.
	 */
	public BaseNode getRefOneAttribute() {
		return getRefAttributes().getRefOne();
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link BaseNode} has a header.
	 */
	public abstract boolean hasHeader();
	
	//method
	/**
	 * @param header
	 * @return true if the current {@link BaseNode} has the given header.
	 */
	@Override
	public boolean hasHeader(final String header) {
		
		//Handles the case that the current document node does not have a header.
		if (!hasHeader()) {
			return false;
		}
		
		//Handles the case that the current document node has a header.
		return getHeader().equals(header);
	}
	
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
	 * Removes the header of the current {@link BaseNode}.
	 */
	public abstract void removeHeader();
	
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
	 * Resets the current {@link BaseNode} from the given string.
	 * 
	 * @param string
	 * @throws InvalidArgumentException if the given string is not valid.
	 */
	public void reset(final String string) {
		
		reset();
		
		if (setAndGetEndIndex(string, 0) != string.length() - 1) {
			throw new UnrepresentingArgumentException(string, Node.class);
		}
	}
	
	//method
	/**
	 * Resets the attributes of the current {@link BaseNode} with the given attributes.
	 * 
	 * @param attributes
	 */
	public <S extends BaseNode> void resetAttributes(final Iterable<S> attributes) {
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
		reset(
			new FileAccessor(filePath)
			.readFile()
			.replace(String.valueOf(CharacterCatalogue.TABULATOR), StringCatalogue.EMPTY_STRING)
			.replace(String.valueOf(CharacterCatalogue.NEW_LINE), StringCatalogue.EMPTY_STRING)
		);
	}
	
	//method
	/**
	 * Saves the current {@link BaseNode} to the file with the given file path.
	 * 
	 * @param filePath
	 * @throws ArgumentIsNullException if the given relative file path is null.
	 * @throws EmptyArgumentException if the given relative file path is empty.
	 * @throws InvalidArgumentException
	 * if a file system item with the given file path exists already.
	 */
	public void saveToFile(final String filePath) {
		
		//Calls other method.
		saveToFile(filePath, false);
	}
	
	//method
	/**
	 * Saves the current {@link BaseNode} to the file with the given path.
	 * 
	 * If the given overwrite flag is true,
	 * a file with the given file path, that exists already, will be overwritten.
	 * 
	 * @param filePath
	 * @param overwrite
	 * @throws ArgumentIsNullException if the given relative file path is null.
	 * @throws EmptyArgumentException if the given relative file path is empty.
	 * @throws InvalidArgumentException if the given overwrite flag is false
	 * and a file system item with the given file path exists already.
	 */
	public void saveToFile(final String filePath, final boolean overwrite) {
		FileSystemAccessor.createFile(filePath, overwrite, toFormatedString());
	}
	
	//method declaration
	/**
	 * Sets the header of the current {@link BaseNode}.
	 * 
	 * @param header
	 */
	public abstract void setHeader(final String header);
	
	//method
	/**
	 * @return the boolean the current {@link BaseNode} represents.
	 * @throws InvalidArgumenException if the current {@link BaseNode} does not represent a boolean.
	 */
	public boolean toBoolean() {
		return StringHelper.toBoolean(toString());
	}
	
	//method
	/**
	 * @return the double the current {@link BaseNode} represents.
	 * @throws InvalidArgumentException if the current {@link BaseNode} does not represent a double.
	 */
	public double toDouble() {
		return StringHelper.toDouble(toString());
	}
	
	//method
	/**
	 * @return a formated string representation of the current {@link BaseNode}.
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
		return StringHelper.toInt(toString());
	}
	
	//method
	/**
	 * @return a string representation of the current {@link BaseNode}.
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
	 * @return a XML representation of the current {@link BaseNode}.
	 */
	public final XMLNode toXML() {
		
		//Creates an XML node.
		final var XMLNode = new XMLNode(getHeader());
		
		//Iterates the attributes of the current specification.
		for (final BaseNode a : getRefAttributes()) {
			
			//Handles the case that the current attribute does not contain attributes.
			if (!a.containsAttributes()) {
				XMLNode.setValue(a.toString());
			}
			
			//Handles the case that the current attribute contains attributes.
			else {
				XMLNode.addChildNode(a.toXML());
			}
		}
		
		return XMLNode;
	}
	
	//package-visible method
	final int setAndGetEndIndex(final String substring, final int startIndex) {
		
		var index = startIndex;
		
		var endIndex = -1;
		while (index < substring.length()) {
			
			var character = substring.charAt(index);
			
			if (character == '(') {
				break;
			}
			
			else if (character == ',' || character == ')') {
				endIndex = index - 1;
				break;
			}
			
			index++;
		}
		
		if (index > startIndex) {
			this.setHeader(createOriginStringFromReproducingString(substring.substring(startIndex, index)));
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
	 * @param leadingTabulators
	 * @return a formated string representation of the current {@link BaseNode}
	 * with as many leading tabulators as the given leading tabulator count says.
	 */
	private String toFormatedString(final int leadingTabulators) {
		
		final var stringBuilder = new StringBuilder();
		
		stringBuilder.append(StringHelper.createTabulators(leadingTabulators));
		
		//Handles the case that the current specification has a header.
		if (hasHeader()) {
			stringBuilder.append(getReproducingHeader());
		}
		
		//Handles the case that the current specification contains attributes.
		if (containsAttributes()) {
			
			//Handles the case that all attributes of the current specification do not contain any attributes.
			if (allAttributesDoNotHaveAttributes()) {
				stringBuilder
				.append(CharacterCatalogue.OPEN_BRACKET)
				.append(getRefAttributes().toString())
				.append(CharacterCatalogue.CLOSED_BRACKET);
			}
			
			//Handles the case that the current specification contains attributes with attributes.
			else {
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
				.append(StringHelper.createTabulators(leadingTabulators))
				.append(CharacterCatalogue.CLOSED_BRACKET);
			}
		}
		
		return stringBuilder.toString();
	}
}

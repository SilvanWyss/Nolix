//package declaration
package ch.nolix.core.specification;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.core.helper.StringHelper;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * A {@link Specification} can have:
 * -1 header
 * -several attributes that are a {@link Specification} themselves
 * 
 * The methods of a {@link Specification} are not final
 * that they can be overwritten by an implementation with a higher performance.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 630
 */
public abstract class Specification {
	
	//constants
	public static final String OPEN_BRACKET_CODE = "$O";
	public static final String CLOSED_BRACKET_CODE = "$C";
	public static final String COMMA_CODE = "$M";
	public static final String DOLLAR_SYMBOL_CODE = "$D";
	
	//static method
	/**
	 * @param string
	 * @return a reproducing string for the given string on the specification context.
	 * @throws NullArgumentException if the given string is null.
	 */
	public static String createReproducingString(final String string) {
		
		//Checks if the given string is not null.
		Validator
		.suppose(string)
		.isNotNull();
		
		return
		string
		.replace(String.valueOf(CharacterCatalogue.COMMA), COMMA_CODE)
		.replace(String.valueOf(CharacterCatalogue.OPEN_BRACKET), OPEN_BRACKET_CODE)
		.replace(String.valueOf(CharacterCatalogue.CLOSED_BRACKET), CLOSED_BRACKET_CODE)
		.replace(String.valueOf(CharacterCatalogue.DOLLAR_SYMBOL), DOLLAR_SYMBOL_CODE);
	}

	//abstract method
	/**
	 * Adds the given attribute to the current {@link Specification}.
	 * 
	 * @param attribute
	 */
	public abstract void addAttribute(Specification attribute);
	
	//method
	/**
	 * Adds the given attributes to the current {@link Specification}.
	 * 
	 * @param attributes
	 */
	public void addAttribute(final Specification... attributes) {
		
		//Calls other method.
		addAttributes(new ReadContainer<Specification>(attributes));
	}
	
	//method
	/**
	 * Adds the given attributes to the current {@link Specification}.
	 * 
	 * @param attributes
	 */
	public <S extends Specification> void addAttributes(final Iterable<S> attributes) {
		
		//Iterates the given attributes.
		attributes.forEach(a -> addAttribute(a));
	}
	
	//method
	/**
	 * @return true if all attributes of this standard specification have no attributes
	 */
	public boolean allAttributesHaveNoAttributes() {
		return getRefAttributes().containsNone(s -> s.containsAttributes());
	}
	
	//abstract method
	/**
	 * @return true if the current {@link Specification} contains attributes.
	 */
	public boolean containsAttributes() {
		return getRefAttributes().containsAny();
	}
	
	//method
	/**
	 * @return a new copy of the current {@link Specification}.
	 */
	public StandardSpecification createCopy() {
		
		final StandardSpecification specification = new StandardSpecification();
		
		//Handles the case that the current specification has a header.
		if (hasHeader()) {
			specification.setHeader(getHeader());
		}
		
		getRefAttributes().forEach(a -> specification.addAttribute(a.createCopy()));
		
		return specification;
	}
	
	//method
	/**
	 * @return true if the current {@link Specification} equals the given object.
	 */
	public boolean equals(final Object object) {
		
		//Handles the case that the given object is no standard specification.
		if (!(object instanceof StandardSpecification)) {
			return false;
		}
		
		//Handles the case that the given object is a standard specification.
		
			final var specification = (Specification)object;
			
			if (!hasHeader()) {
				if (specification.hasHeader()) {
					return false;
				}
			}
			else {
				if (!specification.hasHeader(getHeader())) {
					return false;
				}
			}
			
			if (getAttributeCount() != specification.getAttributeCount()) {
				return false;
			}
									
			//Iterates the attributes of the current {@link Specification}.
			final var iterator = specification.getRefAttributes().iterator();
			for (final var a : getRefAttributes()) {
				if (!a.equals(iterator.next())) {
					return false;
				}
			}
			
			return true;
	}
	
	//method
	/**
	 * @return the number of attributes of the current {@link Specification}.
	 */
	public int getAttributeCount() {
		return getRefAttributes().getElementCount();
	}
	
	//method
	/**
	 * @return the string representations of the attributes of the current {@link Specification}.
	 */
	public List<String> getAttributesToStrings() {
		return getRefAttributes().to(a -> a.toString());
	}
	
	//abstract method
	/**
	 * @return the header of the current {@link Specification}.
	 */
	public abstract String getHeader();
	
	//method
	/**
	 * @return the header of the current {@link Specification} in quotes.
	 */
	public String getHeaderInQuotes() {
		return
		CharacterCatalogue.APOSTROPH
		+ getHeader()
		+ CharacterCatalogue.APOSTROPH;
	}
	
	//method
	/**
	 * @return the double the one attribute of the current {@link Specification} specifies.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link Specification} specifies no double.
	 */
	public double getOneAttributeAsDouble() {
		return getRefOneAttribute().toDouble();
	}
	
	//method
	/**
	 * @return the integer the one attribute of the current {@link Specification} specifies.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link Specification} specifies no int.
	 */
	public int getOneAttributeAsInt() {
		return getRefOneAttribute().toInt();
	}
	
	//method
	/**
	 * @return a string representation of the one attribute of the current {@link Specification}.
	 */
	public String getOneAttributeAsString() {
		return getRefOneAttribute().toString();
	}
	
	//method
	/**
	 * @return a reproducing string representation of the header of the current {@link Specification}.
	 */
	public String getReproducingHeader() {
		return createReproducingString(getHeader());
	}
	
	//abstract method
	/**
	 * @param index
	 * @return the attribute at the given index from the current {@link Specification}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws UnexistingAttributeException if the current {@link Specification} contains no attribute at the given index.
	 */
	@SuppressWarnings("unchecked")
	public <S extends Specification> S getRefAttributeAt(final int index) {
		return (S)getRefAttributes().getRefAt(index);
	}
	
	//abstract method
	/**
	 * @return the attributes of the current {@link Specification}.
	 */
	public abstract <S extends Specification> IContainer<S> getRefAttributes();
	
	//abstract method
	/**
	 * 
	 * @param header
	 * @return the attributes of the current {@link Specification} that have the given header.
	 */
	public IContainer<Specification> getRefAttributes(final String header) {
		return getRefAttributes(a -> a.hasHeader(header));
	}
	
	//abstract method
	/**
	 * @param selector
	 * @return the attributes the given selector selects from the current {@link Specification}.
	 */
	public IContainer<Specification> getRefAttributes(
		final IElementTakerBooleanGetter<Specification> selector
	) {
		return getRefAttributes().getRefSelected(a -> selector.getOutput(a));
	}
	
	//method
	/**
	 * @param selector
	 * @return the first attribute the given selector selects from the current {@link Specification}.
	 * @throws UnexistingAttributeException if the current {@link Specification} contains no attribtue the given selector selects.
	 */
	@SuppressWarnings("unchecked")
	public <S extends Specification> S getRefFirstAttribute(
		IElementTakerBooleanGetter<Specification> selector
	) {
		return (S)getRefAttributes().getRefFirst(a -> selector.getOutput(a));
	}
	
	//method
	/**
	 * @param header
	 * @return the first attribute of the current {@link Specification} with the given header.
	 */
	public <S extends Specification> S getRefFirstAttribute(final String header) {
		return getRefFirstAttribute(a -> a.hasHeader(header));
	}
	
	//abstract method
	/**
	 * @return the one attribute of the current {@link Specification}.
	 * @throws EmptyStateException if the current {@link Specification} contains no attributes.
	 * @throws InvalidStateException if the current {@link Specification} contains several attributes.
	 */
	public Specification getRefOneAttribute() {
		return getRefAttributes().getRefOne();
	}
	
	//abstract method
	/**
	 * @return true if the current {@link Specification} has a header.
	 */
	public abstract boolean hasHeader();
	
	//method
	/**
	 * @param header
	 * @return true if the current {@link Specification} has the given header.
	 */
	public boolean hasHeader(final String header) {
		
		//Handles the case that the current specification has no header.
		if (!hasHeader()) {
			return false;
		}
		
		//Handles the case that the current specification has a header.
		return getHeader().equals(header);
	}
	
	//abstract method
	/**
	 * Removes the attributes of the current {@link Specification}.
	 */
	public abstract void removeAttributes();
	
	//abstract method
	/**
	 * Removes the first attribute the given selector selects from the current {@link Specification}.
	 * 
	 * @param selector
	 */
	public abstract void removeFirstAttribute(IElementTakerBooleanGetter<Specification> selector);
	
	//method
	/**
	 * Removes the first attribute with the given header from the current {@link Specification}.
	 * 
	 * @param header
	 */
	public void removeFirstAttribute(final String header) {
		removeFirstAttribute(a -> a.hasHeader(header));
	}
	
	//abstract method
	/**
	 * Removes the header of the current {@link Specification}.
	 */
	public abstract void removeHeader();
	
	//method
	/**
	 * Removes the header and the attributes of the current {@link Specification}.
	 */
	public void reset() {
		removeHeader();
		removeAttributes();
	}
	
	//method
	/**
	 * Resets the current {@link Specification} from the given string.
	 * 
	 * @param string
	 * @throws InvalidValueException if the given string is not valid.
	 */
	public void reset(final String string) {
		
		reset();
		
		boolean hasAttributes = false;
		int attributestartIndex = 0;
		for (int i = 0; i < string.length(); i++) {
			
			final Character character = string.charAt(i);
			
			//Checks if the current character is a closing bracket before an opening bracket.
			if (character == CharacterCatalogue.CLOSED_BRACKET) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(string)
				);
			}
			
			//Checks if the current character is a comma before an opening bracket.
			if (character == CharacterCatalogue.COMMA) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(string)
				);
			}
			
			//Handles the case that the current character is an opening bracket.
			if (character == CharacterCatalogue.OPEN_BRACKET) {
				
				hasAttributes = true;
				
				if (i > 0) {
					setHeader(string.substring(0, i));
				}
				
				attributestartIndex = i + 1;
				break;
			}
		}
		
		if (!hasAttributes && string.length() > 0) {
			setHeader(string);
		}
		
		//Extract the attributes of the given value.
		if (hasAttributes) {

			//Checks if the start index is not too big.
			if (attributestartIndex > string.length() - 1) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(string)
				);
			}
			
			int level = 0;
			String attributeString = StringCatalogue.EMPTY_STRING;
			for (int i = attributestartIndex; i < string.length() - 1; i++)
			{
				final char character = string.charAt(i);
				
				if (character == CharacterCatalogue.OPEN_BRACKET) {
					level++;
				}
				else if (character == CharacterCatalogue.CLOSED_BRACKET) {
					level--;
				}
				if (character == CharacterCatalogue.COMMA && level == 0) {
					addAttribute(new StandardSpecification(attributeString));
					attributeString = StringCatalogue.EMPTY_STRING;
				}
				else {
					attributeString += character;
				}
			}
			addAttribute(new StandardSpecification(attributeString));
			
			//Checks if the given value has as many opening brackets as closing brackets.
			if (level != 0) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(string)
				);
			}
			
			//Checks if the last character of the given value is a closing bracket.
			if (string.charAt(string.length() - 1) != CharacterCatalogue.CLOSED_BRACKET) {
				throw new InvalidArgumentException(
					new ArgumentName("content"),
					new Argument(string)
				);
			}
		}
	}
	
	//method
	/**
	 * Resets the attributes of the current {@link Specification} to the given attributes.
	 * 
	 * @param attributes
	 */
	public <S extends Specification> void resetAttributes(final Iterable<S> attributes) {
		removeAttributes();
		addAttributes(attributes);
	}
	
	//method
	/**
	 * Resets the current {@link Specification} from the file with the given file path.
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
	 * Saves the current {@link Specification} to the file with the given path.
	 * 
	 * @param filePath
	 * @throws InvalidArgumentException if the given file path is not valid.
	 */
	public void saveToFile(final String filePath) {
		new FileSystemAccessor().createFile(
			filePath,
			toFormatedString()
		);
	}
	
	//abstract method
	/**
	 * Sets the header of the current {@link Specification}.
	 * 
	 * @param header
	 */
	public abstract void setHeader(final String header);
	
	//method
	/**
	 * @return the boolean the current {@link Specification} represents.
	 * @throws InvalidArgumenException if the current {@link Specification} represents no boolean.
	 */
	public boolean toBoolean() {
		return StringHelper.toBoolean(toString());
	}
	
	//method
	/**
	 * @return the double the current {@link Specification} represents.
	 * @throws InvalidArgumentException if the current {@link Specification} represents no double.
	 */
	public double toDouble() {
		return StringHelper.toDouble(toString());
	}
	
	//method
	/**
	 * @return a formated string representation of the current {@link Specification}.
	 */
	public String toFormatedString() {
		return toFormatedString(0);
	}
	
	//method
	/**
	 * @return the integer the current {@link Specification} represents.
	 * @throws InvalidArgumentException if the current {@link Specification} represents no int.
	 */
	public int toInt() {
		return StringHelper.toInt(toString());		
	}
	
	//method
	/**
	 * @return a string representation of the current {@link Specification}.
	 */
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
	 * @param leadingTabulators
	 * @return a formated string representation of the current {@link Specification}
	 * with as many leading tabulators as the given leading tabulator count says.
	 */
	protected String toFormatedString(final int leadingTabulators) {
		
		final var stringBuilder = new StringBuilder();
		
		//Handles the case that the current specification has a header.
		if (hasHeader()) {
			stringBuilder
			.append(StringHelper.createTabulators(leadingTabulators))
			.append(getReproducingHeader());
		}
		
		//Handles the case that the current specification contains attributes.
		if (containsAttributes()) {
			
			//Handles the case that all attributes of the current specification contain no attributes.
			if (allAttributesHaveNoAttributes()) {
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
				var atBegin = true;
				for (final Specification a : getRefAttributes()) {
					
					if (!atBegin) {
						stringBuilder.append(CharacterCatalogue.COMMA);
					}
					
					stringBuilder
					.append(a.toFormatedString(leadingTabulators + 1))					
					.append(CharacterCatalogue.COMMA)
					.append(CharacterCatalogue.NEW_LINE);
					
					atBegin = false;
				}
				
				stringBuilder
				.append(StringHelper.createTabulators(leadingTabulators))
				.append(CharacterCatalogue.CLOSED_BRACKET);
			}
		}
		
		return stringBuilder.toString();
	}
}

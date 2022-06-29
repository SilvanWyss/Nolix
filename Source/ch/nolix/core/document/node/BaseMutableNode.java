//package declaration
package ch.nolix.core.document.node;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.environment.filesystem.FileAccessor;
//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public abstract class BaseMutableNode<MN extends BaseMutableNode<MN>> extends BaseNode<MN> implements IMutableNode<MN> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final MN addPostfixToHeader(final String postfix) {
		
		//Asserts that the given postfix is not blank.
		GlobalValidator.assertThat(postfix).thatIsNamed(LowerCaseCatalogue.POSTFIX).isNotBlank();
		
		//Handles the case that the current BaseMutableNode does not have a header.
		if (!hasHeader()) {
			setHeader(postfix);
			
		//Handles the case that the current BaseMutableNode has a header.
		} else {
			setHeader(getHeader() + postfix);
		}
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final MN addPrefixToHeader(final String prefix) {
		
		//Asserts that the given prefix is not blank.
		GlobalValidator.assertThat(prefix).thatIsNamed(LowerCaseCatalogue.PREFIX).isNotBlank();
		
		//Handles the case that the current BaseMutableNode does not have a header.
		if (!hasHeader()) {
			setHeader(prefix);
			
		//Handles the case that the current BaseMutableNode has a header.
		} else {
			setHeader(prefix + getHeader());
		}
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void resetFromFile(final String filePath) {
		resetFromString(
			new FileAccessor(filePath)
			.readFile()
			.replace(String.valueOf(CharacterCatalogue.TABULATOR), StringCatalogue.EMPTY_STRING)
			.replace(String.valueOf(CharacterCatalogue.NEW_LINE), StringCatalogue.EMPTY_STRING)
		);
	}
	
	//method declaration
	/**
	 * @return the current {@link BaseMutableNode}.
	 */
	protected abstract MN asConcrete();
}

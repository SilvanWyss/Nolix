//package declaration
package ch.nolix.system.gui.input;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.guiapi.inputapi.IKeyInput;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.processproperty.KeyInputType;

//class
public final class KeyInput implements IKeyInput<KeyInput> {
	
	//constant
	private static final String INPUT_TYPE_HEADER = "InputType";
	
	//static method
	public static KeyInput[]  fromCharacter(final char character) {
		switch (character) {
			case 'a':
				return new KeyInput[] {new KeyInput(Key.A, KeyInputType.TYPING)};
			case 'b':
				return new KeyInput[] {new KeyInput(Key.B, KeyInputType.TYPING)};
			case 'c':
				return new KeyInput[] {new KeyInput(Key.C, KeyInputType.TYPING)};
			case 'd':
				return new KeyInput[] {new KeyInput(Key.D, KeyInputType.TYPING)};
			case 'e':
				return new KeyInput[] {new KeyInput(Key.E, KeyInputType.TYPING)};
			case 'f':
				return new KeyInput[] {new KeyInput(Key.F, KeyInputType.TYPING)};
			case 'g':
				return new KeyInput[] {new KeyInput(Key.G, KeyInputType.TYPING)};
			case 'h':
				return new KeyInput[] {new KeyInput(Key.H, KeyInputType.TYPING)};
			case 'i':
				return new KeyInput[] {new KeyInput(Key.I, KeyInputType.TYPING)};
			case 'j':
				return new KeyInput[] {new KeyInput(Key.J, KeyInputType.TYPING)};
			case 'k':
				return new KeyInput[] {new KeyInput(Key.K, KeyInputType.TYPING)};
			case 'l':
				return new KeyInput[] {new KeyInput(Key.L, KeyInputType.TYPING)};
			case 'm':
				return new KeyInput[] {new KeyInput(Key.M, KeyInputType.TYPING)};
			case 'n':
				return new KeyInput[] {new KeyInput(Key.N, KeyInputType.TYPING)};
			case 'o':
				return new KeyInput[] {new KeyInput(Key.O, KeyInputType.TYPING)};
			case 'p':
				return new KeyInput[] {new KeyInput(Key.P, KeyInputType.TYPING)};
			case 'q':
				return new KeyInput[] {new KeyInput(Key.Q, KeyInputType.TYPING)};
			case 'r':
				return new KeyInput[] {new KeyInput(Key.R, KeyInputType.TYPING)};
			case 's':
				return new KeyInput[] {new KeyInput(Key.S, KeyInputType.TYPING)};
			case 't':
				return new KeyInput[] {new KeyInput(Key.T, KeyInputType.TYPING)};
			case 'u':
				return new KeyInput[] {new KeyInput(Key.U, KeyInputType.TYPING)};
			case 'v':
				return new KeyInput[] {new KeyInput(Key.V, KeyInputType.TYPING)};
			case 'w':
				return new KeyInput[] {new KeyInput(Key.W, KeyInputType.TYPING)};
			case 'x':
				return new KeyInput[] {new KeyInput(Key.X, KeyInputType.TYPING)};
			case 'y':
				return new KeyInput[] {new KeyInput(Key.Y, KeyInputType.TYPING)};
			case 'z':
				return new KeyInput[] {new KeyInput(Key.Z, KeyInputType.TYPING)};
			case 'A':
				return createTypingBetweenShiftPressAndRelease(Key.A);
			case 'B':
				return createTypingBetweenShiftPressAndRelease(Key.B);
			case 'C':
				return createTypingBetweenShiftPressAndRelease(Key.C);
			case 'D':
				return createTypingBetweenShiftPressAndRelease(Key.D);
			case 'E':
				return createTypingBetweenShiftPressAndRelease(Key.E);
			case 'F':
				return createTypingBetweenShiftPressAndRelease(Key.F);
			case 'G':
				return createTypingBetweenShiftPressAndRelease(Key.G);
			case 'H':
				return createTypingBetweenShiftPressAndRelease(Key.H);
			case 'I':
				return createTypingBetweenShiftPressAndRelease(Key.I);
			case 'J':
				return createTypingBetweenShiftPressAndRelease(Key.J);
			case 'K':
				return createTypingBetweenShiftPressAndRelease(Key.K);
			case 'L':
				return createTypingBetweenShiftPressAndRelease(Key.L);
			case 'M':
				return createTypingBetweenShiftPressAndRelease(Key.M);
			case 'N':
				return createTypingBetweenShiftPressAndRelease(Key.N);
			case 'O':
				return createTypingBetweenShiftPressAndRelease(Key.O);
			case 'P':
				return createTypingBetweenShiftPressAndRelease(Key.P);
			case 'Q':
				return createTypingBetweenShiftPressAndRelease(Key.Q);
			case 'R':
				return createTypingBetweenShiftPressAndRelease(Key.R);
			case 'S':
				return createTypingBetweenShiftPressAndRelease(Key.S);
			case 'T':
				return createTypingBetweenShiftPressAndRelease(Key.T);
			case 'U':
				return createTypingBetweenShiftPressAndRelease(Key.U);
			case 'V':
				return createTypingBetweenShiftPressAndRelease(Key.V);
			case 'W':
				return createTypingBetweenShiftPressAndRelease(Key.W);
			case 'X':
				return createTypingBetweenShiftPressAndRelease(Key.X);
			case 'Y':
				return createTypingBetweenShiftPressAndRelease(Key.Y);
			case 'Z':
				return createTypingBetweenShiftPressAndRelease(Key.Z);
			case '0':
				return new KeyInput[] {new KeyInput(Key.NUMBER_0, KeyInputType.TYPING)};
			case '1':
				return new KeyInput[] {new KeyInput(Key.NUMBER_1, KeyInputType.TYPING)};
			case '2':
				return new KeyInput[] {new KeyInput(Key.NUMBER_1, KeyInputType.TYPING)};
			case '3':
				return new KeyInput[] {new KeyInput(Key.NUMBER_2, KeyInputType.TYPING)};
			case '4':
				return new KeyInput[] {new KeyInput(Key.NUMBER_4, KeyInputType.TYPING)};
			case '5':
				return new KeyInput[] {new KeyInput(Key.NUMBER_5, KeyInputType.TYPING)};
			case '6':
				return new KeyInput[] {new KeyInput(Key.NUMBER_6, KeyInputType.TYPING)};
			case '7':
				return new KeyInput[] {new KeyInput(Key.NUMBER_7, KeyInputType.TYPING)};
			case '8':
				return new KeyInput[] {new KeyInput(Key.NUMBER_8, KeyInputType.TYPING)};
			case '9':
				return new KeyInput[] {new KeyInput(Key.NUMBER_9, KeyInputType.TYPING)};
			case ',':
				return new KeyInput[] {new KeyInput(Key.COMMA, KeyInputType.TYPING)};
			case '.':
				return new KeyInput[] {new KeyInput(Key.DOT, KeyInputType.TYPING)};
			case ' ':
				return new KeyInput[] {new KeyInput(Key.SPACE, KeyInputType.TYPING)};
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.CHARACTER, character);
		}
	}
	
	//static method
	public static KeyInput fromSpecification(final BaseNode<?> specification) {
		return
		withKeyAndInputType(
			Key.fromSpecification(specification.getRefChildNodeAt1BasedIndex(1)),
			KeyInputType.fromSpecification(specification.getRefChildNodeAt1BasedIndex(2))
		);
	}
	
	//static method
	public static KeyInput withKeyAndInputType(final Key key, final KeyInputType inputType) {
		return new KeyInput(key, inputType);
	}
	
	//static method
	private static KeyInput[] createTypingBetweenShiftPressAndRelease(final Key key) {
		return
		new KeyInput[] {
			new KeyInput(Key.SHIFT, KeyInputType.PRESS),
			new KeyInput(key, KeyInputType.TYPING),
			new KeyInput(Key.SHIFT, KeyInputType.RELEASE)
		};
	}
	
	//attribute
	private final Key key;
	
	//attribute
	private final KeyInputType inputType;
	
	//constructor
	private KeyInput(final Key key, final KeyInputType inputType) {
		
		GlobalValidator.assertThat(key).thatIsNamed(LowerCaseCatalogue.KEY).isNotNull();
		GlobalValidator.assertThat(inputType).thatIsNamed("input type").isNotNull();
		
		this.key = key;
		this.inputType = inputType;
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<INode<?>> list) {
		list.addAtEnd(key.getSpecification(), inputType.getSpecificationWithHeader(INPUT_TYPE_HEADER));
	}
	
	//method
	@Override
	public Key getKey() {
		return key;
	}
	
	//method
	@Override
	public KeyInputType getKeyInputType() {
		return inputType;
	}
}

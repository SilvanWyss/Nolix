//package declaration
package ch.nolix.systemapi.guiapi.inputapi;

//Java imports
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum Key {
	A,
	B,
	C,
	D,
	E,
	F,
	G,
	H,
	I,
	J,
	K,
	L,
	M,
	N,
	O,
	P,
	Q,
	R,
	S,
	T,
	U,
	V,
	W,
	X,
	Y,
	Z,
	AE,
	OE,
	UE,
	NUMBER_0,
	NUMBER_1,
	NUMBER_2,
	NUMBER_3,
	NUMBER_4,
	NUMBER_5,
	NUMBER_6,
	NUMBER_7,
	NUMBER_8,
	NUMBER_9,
	NUMBERPAD_0,
	NUMBERPAD_1,
	NUMBERPAD_2,
	NUMBERPAD_3,
	NUMBERPAD_4,
	NUMBERPAD_5,
	NUMBERPAD_6,
	NUMBERPAD_7,
	NUMBERPAD_8,
	NUMBERPAD_9,
	F1,
	F2,
	F3,
	F4,
	F5,
	F6,
	F7,
	F8,
	F9,
	F10,
	F11,
	F12,
	ARROW_UP,
	ARROW_DOWN,
	ARROW_LEFT,
	ARROW_RIGHT,
	ALTERNATIVE,
	BACKSPACE,
	BREAK,
	CAPS_LOCK,
	COMMA,
	CONTROL,
	DELETE,
	DOLLAR_SYMBOL,
	DOT,
	END,
	ENTER,
	ESCAPE,
	EXCLAMATION_MARK,
	GRADE_SYMBOL,
	GRAVIS,
	HOME,
	HYPHEN,
	INSERT,
	MENU,
	NUMBER_LOCK,
	PAGE_DOWN,
	PAGE_UP,
	PRINT_SCREEN,
	QUESTION_MARK,
	RELATIONS,
	SCROLL_LOCK,
	SHIFT,
	SPACE,
	TABULATOR,
	WINDOWS;
	
	//static method
	public static Key fromAWTKeyEvent(final KeyEvent keyEvent) {
		return
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_A ->
				A;
			case KeyEvent.VK_B ->
				B;
			case KeyEvent.VK_C ->
				C;	
			case KeyEvent.VK_D ->
				D;
			case KeyEvent.VK_E ->
				E;
			case KeyEvent.VK_F ->
				F;	
			case KeyEvent.VK_G ->
				G;
			case KeyEvent.VK_H ->
				H;
			case KeyEvent.VK_I ->
				I;	
			case KeyEvent.VK_J ->
				J;
			case KeyEvent.VK_K ->
				K;
			case KeyEvent.VK_L ->
				L;	
			case KeyEvent.VK_M ->
				M;
			case KeyEvent.VK_N ->
				N;
			case KeyEvent.VK_O ->
				O;	
			case KeyEvent.VK_P ->
				P;
			case KeyEvent.VK_Q ->
				Q;
			case KeyEvent.VK_R ->
				R;
			case KeyEvent.VK_S ->
				S;
			case KeyEvent.VK_T ->
				T;
			case KeyEvent.VK_U ->
				U;
			case KeyEvent.VK_V ->
				V;
			case KeyEvent.VK_W ->
				W;
			case KeyEvent.VK_X ->
				X;
			case KeyEvent.VK_Y ->
				Y;
			case KeyEvent.VK_Z ->
				Z;
			case KeyEvent.VK_0 ->
				NUMBER_0;
			case KeyEvent.VK_1 ->
				NUMBER_1;
			case KeyEvent.VK_2 ->
				NUMBER_2;
			case KeyEvent.VK_3 ->
				NUMBER_3;
			case KeyEvent.VK_4 ->
				NUMBER_4;
			case KeyEvent.VK_5 ->
				NUMBER_5;
			case KeyEvent.VK_6 ->
				NUMBER_6;
			case KeyEvent.VK_7 ->
				NUMBER_7;
			case KeyEvent.VK_8 ->
				NUMBER_8;
			case KeyEvent.VK_9 ->
				NUMBER_9;
			case KeyEvent.VK_NUMPAD0 ->
				NUMBERPAD_0;
			case KeyEvent.VK_NUMPAD1 ->
				NUMBERPAD_1;
			case KeyEvent.VK_NUMPAD2 ->
				NUMBERPAD_2;
			case KeyEvent.VK_NUMPAD3 ->
				NUMBERPAD_3;
			case KeyEvent.VK_NUMPAD4 ->
				NUMBERPAD_4;
			case KeyEvent.VK_NUMPAD5 ->
				NUMBERPAD_5;
			case KeyEvent.VK_NUMPAD6 ->
				NUMBERPAD_6;
			case KeyEvent.VK_NUMPAD7 ->
				NUMBERPAD_7;
			case KeyEvent.VK_NUMPAD8 ->
				NUMBERPAD_8;
			case KeyEvent.VK_NUMPAD9 ->
				NUMBERPAD_9;
			case KeyEvent.VK_LEFT ->
				ARROW_LEFT;
			case KeyEvent.VK_RIGHT ->
				ARROW_RIGHT;
			case KeyEvent.VK_UP ->
				ARROW_UP;
			case KeyEvent.VK_DOWN ->
				ARROW_DOWN;
			case KeyEvent.VK_BACK_SPACE ->
				BACKSPACE;
			case KeyEvent.VK_COMMA ->
				COMMA;
			case KeyEvent.VK_CONTROL ->
				CONTROL;
			case KeyEvent.VK_CAPS_LOCK ->
				Key.CAPS_LOCK;
			case KeyEvent.VK_DELETE ->
				Key.DELETE;
			case KeyEvent.VK_DOLLAR ->
				DOLLAR_SYMBOL;
			case KeyEvent.VK_ENTER ->
				Key.ENTER;
			case KeyEvent.VK_ESCAPE ->
				ESCAPE;
			case KeyEvent.VK_SHIFT ->
				SHIFT;
			case KeyEvent.VK_SPACE ->
				Key.SPACE;
			default ->
				throw new IllegalArgumentException("The given key event does not represent a Key.");
		};
	}
	
	//static method
	public static Key fromCharacter(final char character) {
		return
		switch (character) {
			case 'A' ->
				Key.A;
			case 'B' ->
				Key.B;
			case 'C' ->
				Key.C;
			case 'D' ->
				Key.D;
			case 'E' ->
				Key.E;
			case 'F' ->
				Key.F;
			case 'G' ->
				Key.G;
			case 'H' ->
				Key.H;
			case 'I' ->
				Key.I;
			case 'J' ->
				Key.J;
			case 'K' ->
				Key.K;
			case 'L' ->
				Key.L;
			case 'M' ->
				Key.M;
			case 'N' ->
				Key.N;
			case 'O' ->
				Key.O;
			case 'P' ->
				Key.P;
			case 'Q' ->
				Key.Q;
			case 'R' ->
				Key.R;
			case 'S' ->
				Key.S;
			case 'T' ->
				Key.T;
			case 'U' ->
				Key.U;
			case 'V' ->
				Key.V;
			case 'W' ->
				Key.W;
			case 'X' ->
				Key.X;
			case 'Y' ->
				Key.Y;
			case 'Z' ->
				Key.Z;
			case 'a' ->
				Key.A;
			case 'b' ->
				Key.B;
			case 'c' ->
				Key.C;
			case 'd' ->
				Key.D;
			case 'e' ->
				Key.E;
			case 'f' ->
				Key.F;
			case 'g' ->
				Key.G;
			case 'h' ->
				Key.H;
			case 'i' ->
				Key.I;
			case 'j' ->
				Key.J;
			case 'k' ->
				Key.K;
			case 'l' ->
				Key.L;
			case 'm' ->
				Key.M;
			case 'n' ->
				Key.N;
			case 'o' ->
				Key.O;
			case 'p' ->
				Key.P;
			case 'q' ->
				Key.Q;
			case 'r' ->
				Key.R;
			case 's' ->
				Key.S;
			case 't' ->
				Key.T;
			case 'u' ->
				Key.U;
			case 'v' ->
				Key.V;
			case 'w' ->
				Key.W;
			case 'x' ->
				Key.X;
			case 'y' ->
				Key.Y;
			case 'z' ->
				Key.Z;
			case '0' ->
				Key.NUMBER_0;
			case '1' ->
				Key.NUMBER_1;
			case '2' ->
				Key.NUMBER_2;
			case '3' ->
				Key.NUMBER_3;
			case '4' ->
				Key.NUMBER_4;
			case '5' ->
				Key.NUMBER_5;
			case '6' ->
				Key.NUMBER_6;
			case '7' ->
				Key.NUMBER_7;
			case '8' ->
				Key.NUMBER_8;
			case '9' ->
				Key.NUMBER_9;
			default ->
				throw new IllegalArgumentException("The given char is not a character.");
		};
	}
	
	//static method
	public static Key fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
	
	//method
	public boolean isCharacter() {
		
		if (isLetter() || isNumber()) {
			return true;
		}
		
		return
		switch (this) {
			case
			COMMA,	
			DOLLAR_SYMBOL,
			DOT,
			EXCLAMATION_MARK,
			GRADE_SYMBOL,
			GRAVIS,
			HYPHEN,
			QUESTION_MARK,
			RELATIONS,
			SPACE ->
				true;
			default ->
				false;
		};
	}
	
	//method
	public boolean isLetter() {
		return
		switch(this) {
			case
			A,
			B,
			C,
			D,
			E,
			F,
			G,
			H,
			I,
			J,	
			K,
			L,
			M,
			N,
			O,
			P,
			Q,
			R,
			S,
			T,
			U,
			V,
			W,
			X,
			Y,
			Z ->
				true;
			default ->
				false;
		};
	}
	
	//method
	public boolean isNumber() {
		return
		switch(this) {
			case
			NUMBER_0,
			NUMBER_1,
			NUMBER_2,
			NUMBER_3,
			NUMBER_4,
			NUMBER_5,
			NUMBER_6,
			NUMBER_7,
			NUMBER_8,
			NUMBER_9,
			NUMBERPAD_0,
			NUMBERPAD_1,
			NUMBERPAD_2,
			NUMBERPAD_3,
			NUMBERPAD_4,
			NUMBERPAD_5,
			NUMBERPAD_6,
			NUMBERPAD_7,
			NUMBERPAD_8,
			NUMBERPAD_9 ->
				true;
			default ->
				false;
		};
	}
	
	//method
	public boolean isOnNumberPad() {
		return
		switch(this) {
			case
			NUMBERPAD_0,
			NUMBERPAD_1,
			NUMBERPAD_2,
			NUMBERPAD_3,
			NUMBERPAD_4,
			NUMBERPAD_5,
			NUMBERPAD_6,
			NUMBERPAD_7,
			NUMBERPAD_8,
			NUMBERPAD_9 ->
				true;
			default ->
				false;
		};
	}
	
	//method
	public char toLowerCaseChar() {
		return
		switch(this) {
			case A ->
				'a';
			case B ->
				'b';
			case C ->
				'c';
			case D ->
				'd';
			case E ->
				'e';
			case F ->
				'f';
			case G ->
				'g';
			case H ->
				'h';
			case I ->
				'i';
			case J ->
				'j';
			case K ->
				'k';
			case L ->
				'l';
			case M ->
				'm';
			case N ->
				'n';
			case O ->
				'o';
			case P ->
				'p';
			case Q ->
				'q';
			case R ->
				'r';
			case S ->
				's';
			case T ->
				't';
			case U ->
				'u';
			case V ->
				'v';
			case W ->
				'w';
			case X ->
				'x';
			case Y ->
				'y';
			case Z ->
				'z';
			default ->
				toNonNumberChar();
		};
	}
	
	//method
	public char toUpperCaseChar() {
		return
		switch(this) {
			case A ->
				'A';
			case B ->
				'B';
			case C ->
				'C';
			case D ->
				'D';
			case E ->
				'E';
			case F ->
				'F';
			case G ->
				'G';
			case H ->
				'H';
			case I ->
				'I';
			case J ->
				'J';
			case K ->
				'K';
			case L ->
				'L';
			case M ->
				'M';
			case N ->
				'N';
			case O ->
				'O';
			case P ->
				'P';
			case Q ->
				'Q';
			case R ->
				'R';
			case S ->
				'S';
			case T ->
				'T';
			case U ->
				'U';
			case V ->
				'V';
			case W ->
				'W';
			case X ->
				'X';
			case Y ->
				'Y';
			case Z ->
				'Z';
			default ->
				toNonNumberChar();
		};
	}
	
	//method
	private char toNonNumberChar() {
		return
		switch (this) {
			case NUMBER_0 ->
				'0';
			case NUMBER_1 ->
				'1';
			case NUMBER_2 ->
				'2';
			case NUMBER_3 ->
				'3';
			case NUMBER_4 ->
				'4';
			case NUMBER_5 ->
				'5';
			case NUMBER_6 ->
				'6';
			case NUMBER_7 ->
				'7';
			case NUMBER_8 ->
				'8';
			case NUMBER_9 ->
				'9';
			case NUMBERPAD_0 ->
				'0';
			case NUMBERPAD_1 ->
				'1';
			case NUMBERPAD_2 ->
				'2';
			case NUMBERPAD_3 ->
				'3';
			case NUMBERPAD_4 ->
				'4';
			case NUMBERPAD_5 ->
				'5';
			case NUMBERPAD_6 ->
				'6';
			case NUMBERPAD_7 ->
				'7';
			case NUMBERPAD_8 ->
				'8';
			case NUMBERPAD_9 ->
				'9';
			case COMMA ->
				',';
			case DOLLAR_SYMBOL ->
				'$';
			case DOT ->
				'.';
			case EXCLAMATION_MARK ->
				'!';
			case GRADE_SYMBOL ->
				'°';
			case GRAVIS ->
				'`';
			case HYPHEN ->
				'-';
			case QUESTION_MARK ->
				'?';
			case RELATIONS ->
				'<';
			case SPACE ->
				' ';
		default ->
			throw new IllegalArgumentException("The current Key is not a non-number character.");			
		};
	}
}

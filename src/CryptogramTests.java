import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CryptogramTests {

	@Test
	void testReplaceMethod() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		System.out.println(control.getEncryptedQuote());
		control.makeReplacement("A","B");
		
	}
	
	@Test
	void testGetProgressMethod() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		control.getUsersProgress();
//		System.out.println(control.getUsersProgress());
	}
	
	@Test
	void testGetFreqMethod() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		control.getFreq();
//		System.out.println(control.getFreq());
	}
	
	@Test
	void testGetHintMethod() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		control.giveHint();
//		System.out.println(control.giveHint());
	}
	
	@Test
	void testGameOverMethod() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		System.out.println(control.isGameOver());
	}
	
	@Test
	void testPunctuationMethod1() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		control.makeReplacement("-","B");
	}
	
	@Test
	void testPunctuationMethod2() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		control.makeReplacement("B","-");
	}
	
	@Test
	void testHelpMethod() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		control.giveHelp();
	}

	@Test
	void testCorrectGuessedLetter() {
		CryptogramController control = new CryptogramController(new CryptogramModel());
		control.makeReplacement("A", "A");
		control.makeReplacement("E", "E");
		control.makeReplacement("I", "I");
		control.makeReplacement("O", "O");
		control.makeReplacement("U", "U");
		control.getUsersProgress();
	}
}

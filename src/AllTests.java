import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {
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
	
	@Test
	void testCreateArrayMap() {
		ArrayMap<String, Integer> map = new ArrayMap();
		map.put("a", 0);
		map.put("b", 1);
		map.put("c", 2);
		map.put("d", 3);
		map.put("e", 4);
		map.put("f", 5);
		map.put("g", 6);
		map.put("h", 7);
		map.put("i", 8);
		map.put("j", 9);
		int x = map.size();
		assertEquals(10, x);
		
		map.put("k", 10);
		map.put("l", 11);
		map.put("m", 12);
		map.put("n", 13);
		map.put("o", 14);
		map.put("p", 15);
		map.put("q", 16);
		map.put("r", 17);
		map.put("s", 18);
		map.put("t", 19);
		x = map.size();
		assertEquals(20, x);
		
		map.put("u", 20);
		map.put("v", 21);
		map.put("w", 22);
		x = map.size();
		assertEquals(23, x);
	}
	

	@Test
	void testEntrySet() {
		Set<SimpleEntry> s = new HashSet<>();
		s.add(new SimpleEntry<String, Integer>("a", 0));
		s.add(new SimpleEntry<String, Integer>("b", 1));
		s.add(new SimpleEntry<String, Integer>("c", 2));
		s.add(new SimpleEntry<String, Integer>("d", 3));
		s.add(new SimpleEntry<String, Integer>("e", 4));
		s.add(new SimpleEntry<String, Integer>("f", 5));
		s.add(new SimpleEntry<String, Integer>("g", 6));
		s.add(new SimpleEntry<String, Integer>("h", 7));
		s.add(new SimpleEntry<String, Integer>("i", 8));
		s.add(new SimpleEntry<String, Integer>("j", 9));
		s.add(new SimpleEntry<String, Integer>("k", 10));
		s.add(new SimpleEntry<String, Integer>("l", 11));
		s.add(new SimpleEntry<String, Integer>("m", 12));
		
		ArrayMap<String, Integer> map = new ArrayMap();
		map.put("a", 0);
		map.put("b", 1);
		map.put("c", 2);
		map.put("d", 3);
		map.put("e", 4);
		map.put("f", 5);
		map.put("g", 6);
		map.put("h", 7);
		map.put("i", 8);
		map.put("j", 9);
		map.put("k", 10);
		map.put("l", 11);
		map.put("m", 12);
		
		assertEquals(s, map.entrySet());
	}
	
	
	@Test
	void testUpdateKey() {
		Set<SimpleEntry> s = new HashSet<>();
		s.add(new SimpleEntry<String, Integer>("a", 987));
		s.add(new SimpleEntry<String, Integer>("b", 1));
		s.add(new SimpleEntry<String, Integer>("c", 987));
		s.add(new SimpleEntry<String, Integer>("d", 3));
		s.add(new SimpleEntry<String, Integer>("e", 4));
		s.add(new SimpleEntry<String, Integer>("f", 5));
		s.add(new SimpleEntry<String, Integer>("g", 6));
		s.add(new SimpleEntry<String, Integer>("h", 7));
		s.add(new SimpleEntry<String, Integer>("i", 56));
		s.add(new SimpleEntry<String, Integer>("j", 9));
		s.add(new SimpleEntry<String, Integer>("k", 10));
		s.add(new SimpleEntry<String, Integer>("l", 11));
		s.add(new SimpleEntry<String, Integer>("m", 34));
		
		ArrayMap<String, Integer> map = new ArrayMap();
		map.put("a", 0);
		map.put("b", 1);
		map.put("c", 2);
		map.put("d", 3);
		map.put("e", 4);
		map.put("f", 5);
		map.put("g", 6);
		map.put("h", 7);
		map.put("i", 8);
		map.put("j", 9);
		map.put("k", 10);
		map.put("l", 11);
		map.put("m", 12);
		
		map.put("a", 987);
		map.put("c", 987);
		map.put("m", 34);
		map.put("i", 56);
		
		assertEquals(s, map.entrySet());
		
	}
	
	@Test
	void testIterator() {
		Set<SimpleEntry> s = new HashSet<>();
		s.add(new SimpleEntry<String, Integer>("a", 0));
		s.add(new SimpleEntry<String, Integer>("b", 1));
		s.add(new SimpleEntry<String, Integer>("c", 2));
		s.add(new SimpleEntry<String, Integer>("d", 3));
		s.add(new SimpleEntry<String, Integer>("e", 4));
		s.add(new SimpleEntry<String, Integer>("f", 5));
		s.add(new SimpleEntry<String, Integer>("g", 6));
		s.add(new SimpleEntry<String, Integer>("h", 7));
		s.add(new SimpleEntry<String, Integer>("i", 8));
		s.add(new SimpleEntry<String, Integer>("j", 9));
		
		ArrayMap<String, Integer> map = new ArrayMap();
		map.put("a", 0);
		map.put("b", 1);
		map.put("c", 2);
		map.put("d", 3);
		map.put("e", 4);
		map.put("f", 5);
		map.put("g", 6);
		map.put("h", 7);
		map.put("i", 8);
		map.put("j", 9);
		Set<Entry<String, Integer>> set = map.entrySet();
		System.out.println(set.size());
//		set.iterator();
		Iterator<Entry<String, Integer>> itr = set.iterator();
		
		for(int i = 0; i < 10; i++) {
			assertTrue(itr.hasNext());
			itr.next();
		}
		assertFalse(itr.hasNext());
		assertEquals(10, set.size());
	}
	
	@Test
	void testRemoveFromEmptySet() {
		ArrayMap<String, Integer> map = new ArrayMap();
		Set set = map.entrySet();
		Iterator itr = set.iterator();
		assertThrows(
				IllegalStateException.class, 
				() -> { itr.remove();
				}
		);
	}
	
	@Test
	void testContains() {
		ArrayMap<String, Integer> map = new ArrayMap();
		map.put("a", 0);
		map.put("b", 1);
		map.put("c", 2);
		map.put("d", 3);
		map.put("e", 4);
		map.put("f", 5);
		map.put("g", 6);
		map.put("h", 7);
		map.put("i", 8);
		map.put("j", 9);
		map.put("k", 10);
		map.put("l", 11);
		map.put("m", 12);
		map.put("n", 13);
		map.put("o", 14);
		map.put("p", 15);
		map.put("q", 16);
		map.put("r", 17);
		map.put("s", 18);
		map.put("t", 19);
		Set set = map.entrySet();
		SimpleEntry entry = new SimpleEntry<String, Integer>("e", 4);
		SimpleEntry fakeEntry = new SimpleEntry<String, Integer>("e", 6);
		boolean t = set.contains(entry);
		assertTrue(t);
		boolean f = set.contains(fakeEntry);
		assertFalse(f);
	}
	
	@Test
	void returnNull() {
		ArrayMap<String, Integer> map = new ArrayMap();
		Set set = map.entrySet();
		Iterator<Entry<String, Integer>> itr = set.iterator();
		Entry<String, Integer> x = itr.next();
		assertEquals(null, x);
	}
}

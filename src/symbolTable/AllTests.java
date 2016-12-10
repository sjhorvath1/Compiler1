package symbolTable;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AllTests {
		
		
		@Test
		public void testAdd(){
			SymbolTable symbols = new SymbolTable();
			boolean actual = symbols.add("foo", IDType.VARIABLE, NumType.REAL);
			boolean expected = true;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testAdd2(){
			SymbolTable symbols = new SymbolTable();
			boolean actual = symbols.add("foo", IDType.ARRAY, NumType.REAL, 4, 6);
			boolean expected = true;
			assertEquals(actual,expected);
		}
		
		@Test
		public void testAddFalse(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.VARIABLE, NumType.REAL);
			boolean actual = symbols.add("foo", IDType.VARIABLE, NumType.REAL);
			boolean expected = false;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testAddFalse2(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.ARRAY, NumType.REAL, 4, 6);
			boolean actual = symbols.add("foo", IDType.ARRAY, NumType.REAL, 4, 6);
			boolean expected = false;
			assertEquals(actual,expected);
		}
		
		@Test
		public void testIsProgram(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.PROGRAM, NumType.REAL);
			boolean actual = symbols.isProgram("foo");
			boolean expected = true;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsProgramFalse(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.FUNCTION, NumType.REAL);
			boolean actual = symbols.isProgram("foo");
			boolean expected = false;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsVARIABLE(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.VARIABLE, NumType.REAL);
			boolean actual = symbols.isVARIABLE("foo");
			boolean expected = true;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsVARIABLEFalse(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.PROGRAM, NumType.REAL);
			boolean actual = symbols.isVARIABLE("foo");
			boolean expected = false;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsARRAY(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.ARRAY, NumType.REAL, 3, 6);
			boolean actual = symbols.isARRAY("foo");
			boolean expected = true;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsARRAYFalse(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.PROGRAM, NumType.REAL);
			boolean actual = symbols.isARRAY("foo");
			boolean expected = false;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsFUNCTION(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.FUNCTION, NumType.REAL);
			boolean actual = symbols.isFUNCTION("foo");
			boolean expected = true;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsFUNCTIONFalse(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.PROGRAM, NumType.REAL);
			boolean actual = symbols.isFUNCTION("foo");
			boolean expected = false;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsPROCEDURE(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.PROCEDURE, NumType.REAL);
			boolean actual = symbols.isPROCEDURE("foo");
			boolean expected = true;
			assertEquals(actual, expected);
		}
		
		@Test
		public void testIsPROCEDUREFalse(){
			SymbolTable symbols = new SymbolTable();
			symbols.add("foo", IDType.PROGRAM, NumType.REAL);
			boolean actual = symbols.isPROCEDURE("foo");
			boolean expected = false;
			assertEquals(actual, expected);
		}
		
		
		
}

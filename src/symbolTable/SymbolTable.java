package symbolTable;
import java.util.Hashtable;
import java.util.Set;

public class SymbolTable {
	private Hashtable<String, IDInfo> symbols;
	
	/**
	 * Implements symbol table constructor, intializes hashtable
	 * 
	 */
	public SymbolTable(){
		symbols = new Hashtable<String, IDInfo>();
	}
	
	/**
	 * Adds an ID string to the symbol table, with information about its IDType and number type
	 * @return boolean of true or false
	 */
	public boolean add(String idstring, IDType id, NumType num){
		System.out.println("Adding " + idstring);
		if(!symbols.containsKey(idstring)){
			IDInfo infoSet = new IDInfo(id, num);
			symbols.put(idstring, infoSet);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds an IDString for an array to the symbol table
	 * @return boolean of true or false
	 */
	public boolean add(String idstring, IDType id, NumType num, int indexBegin, int indexEnd){
		System.out.println("Adding " + idstring);
		if(!symbols.containsKey(idstring)){
			IDInfo infoSet = new IDInfo(id, num, indexBegin, indexEnd);
			symbols.put(idstring, infoSet);
			return true;
		}
		return false;
	}
	
	/**
	 * Tests whether or not an element in the symbol table is a program
	 * 
	 * @return Boolean of true or false
	 */
	public boolean isProgram(String key){
		IDInfo infoSet = symbols.get(key);
		if(infoSet.idType == IDType.PROGRAM){
			return true;
		}
		return false;
	}
	
	/**
	 * Tests whether or not an element in the symbol table is a variable
	 * 
	 * @return Boolean of true or false
	 */
	public boolean isVARIABLE(String key){
		IDInfo infoSet = symbols.get(key);
		System.out.println(key);
		if(infoSet.idType == IDType.VARIABLE){
			return true;
		}
		return false;
	}
	
	/**
	 * Tests whether or not an element in the symbol table is an array
	 * 
	 * @return Boolean of true or false
	 */
	public boolean isARRAY(String key){
		IDInfo infoSet = symbols.get(key);
		if(infoSet.idType == IDType.ARRAY){
			return true;
		}
		return false;
	}
	
	/**
	 * Tests whether or not an element in the symbol table is a function
	 * 
	 * @return Boolean of true or false
	 */
	public boolean isFUNCTION(String key){
		IDInfo infoSet = symbols.get(key);
		if(infoSet.idType == IDType.FUNCTION){
			return true;
		}
		return false;
	}
	
	/**
	 * Tests whether or not an element in the symbol table is a procedure
	 * 
	 * @return Boolean of true or false
	 */
	public boolean isPROCEDURE(String key){
		IDInfo infoSet = symbols.get(key);
		if(infoSet.idType == IDType.PROCEDURE){
			return true;
		}
		return false;
	}
	
	public String toString(){
		String ret = "ID\tKIND\t\tTYPE\tINDEXSTART\tINDEXEND\n";
		Set<String> keys = symbols.keySet();
		String line;
		for (String key: keys){
			line = "";
			IDInfo temp = symbols.get(key);
			if(temp.idType == IDType.VARIABLE
					|| temp.idType == IDType.PROCEDURE
					|| temp.idType == IDType.FUNCTION){
				line = key + "\t" + temp.idType + "\t" + temp.numType + "\t" + temp.ia + "\t\t" + temp.iz + "\n";
			}
			else{
				line = key + "\t" + temp.idType + "\t\t" + temp.numType + "\t" + temp.ia + "\t\t" + temp.iz + "\n";
			}
			ret += line;
		}
		return ret;
	}
	
	
	
	private class IDInfo{
		IDType idType; //program, variable, array, function, or procedure
		NumType numType; //integer or real
		int ia; //beginning index
		int iz; //end index
		
		
		public IDInfo(IDType idtype, NumType numtype){
			this.idType = idtype;
			this.numType = numtype;
		}
		
		public IDInfo(IDType idtype, NumType numtype, int ia, int iz){
			this.idType = idtype;
			this.numType = numtype;
			this.ia = ia;
			this.iz = iz;
		}
		
		
		
	}
	
}


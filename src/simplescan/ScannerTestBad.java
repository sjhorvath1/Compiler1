package simplescan;

import java.io.File;

public class ScannerTestBad {
	
	public static void main(String[] args){
		
		String[] fileNames = {"WrongComment.txt","ExponentWrong1.txt","ExponentWrong2.txt","ExponentWrong3.txt"};
		for(String file : fileNames){
			System.out.println("Checking file "+file);
			Scanner s = new Scanner( new File( file));
			while(s.nextToken()){
				System.out.println(s.getToken()+" "+s.getLexeme());
				
			}
		}
		 
		
	}	 
}

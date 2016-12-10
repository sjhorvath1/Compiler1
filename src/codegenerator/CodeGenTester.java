package codegenerator;
import parser.*;
import symbolTable.*;
import java.io.*;

public class CodeGenTester {
	public static void main(String[] args){
		
		String inputFile = args[0];
		String outputFile = args[1];
		PascalParser testParser = new PascalParser(inputFile);
		CodeGenerator testGenerator = new CodeGenerator(testParser.program());
		//System.out.println(testGenerator.stringGen());
		BufferedWriter bw = null;
		try{
			File file = new File(outputFile);
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(testGenerator.stringGen());
			System.out.println("File written successfully");
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		finally{
			try{
				if(bw != null){
					bw.close();
				}
			}
			catch(Exception ex){
				System.out.println("Error in closing BufferedWriter"+ex);
			}
			
					
		}
		
	}
}

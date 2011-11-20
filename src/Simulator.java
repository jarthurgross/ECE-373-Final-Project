import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
		
public class Simulator {

	public static void main(String[] args) {
		
		// "/Users/delbertgallego/Documents/workspace/Digital Logic Simulator/src/sample3.txt" file location local computer
		// ReadInputFile trial1 = new ReadInputFile("/Users/delbertgallego/Documents/workspace/Digital Logic Simulator/src/sample3.txt");
	
		Scanner keyboard = new Scanner(System.in);
		String fileName;

		System.out.println("Digital Logic Simulator");
		System.out.println();
		System.out.print("Enter file name: ");
		fileName=keyboard.nextLine();
		System.out.println();
		
		ReadInputFile trial1 = new ReadInputFile(fileName);
		trial1.parseFile();
	 
	}
}

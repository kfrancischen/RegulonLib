import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
public class test {
	/*
	public static void main(String argv[]){
		ArrayList array = new ArrayList();
		System.out.println("input the array size:\n");
		int num = Integer.parseInt(input());
		for(int i = 0;i < num; i++){
			array.add(factorial(i));
		}
		for(int i = 0;i < num; i++){
			System.out.println(array.get(i)+"\n");
		}	
	}

static String input(){
	String s="";
	try{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		s = in.readLine();
	}
	catch(IOException e){};
	return s;
}
static int factorial(int n){
	if (n==0 ) return 1;
	else return factorial(n-1)*n ;
}
*/	
	public static final String PATH = "D:/iGEM/Clotho/USTC_Clotho_App/src/";
	public static void main(String argv[]) throws IOException{
		//read file
		String filePath = PATH + "USTC_SOFTWARE_PARTS_DATA.txt";
		FileReader infile = new FileReader(filePath);
		BufferedReader in = new BufferedReader(infile);
		
		// initializing numOfGenes and numOfPromoters
		int numOfOperons = Integer.parseInt(in.readLine());
		System.out.print(numOfOperons);
		//initializing geneNames
		Vector<String> operonNames = new Vector<String>();
		for(int i = 0;i < numOfOperons; i++){
			operonNames.add(in.readLine());
		}
		
		for(int i = 0; i < numOfOperons; i++){
		System.out.print(operonNames.get(i)+"\n");
	}
		int[][] database = new int[numOfOperons][numOfOperons];
		for(int i = 0;i < numOfOperons; i++){
			for(int j = 0;j < numOfOperons;j++){
				database[i][j] = Integer.parseInt(in.readLine());
			}
		}
		/*generate database
		int[][] database = new int[numOfGenes][numOfPromoters];
		for(int i = 0;i < numOfGenes; i++){
			for(int j = 0;j < numOfGenes;j++){
				database[i][j] = Integer.parseInt(in.readLine());
			}
		}
		*/
	}
}

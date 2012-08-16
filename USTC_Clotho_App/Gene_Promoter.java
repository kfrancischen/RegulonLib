package USTC_Clotho_App;
import java.io.*;
import java.util.Collection;
import java.util.Vector;


public class Gene_Promoter {
	//Database path
	public static final String PATH = "F:/programs/java/";
	//user's matrix size
	public static final int matrixSize = 3;
    //static integer 
	public static int numberPossibleChoices = 0;
	// main function of the class Gene_Promoter
	public static void main(String args[]) throws IOException{
		
		/*-----------------read file-----------------------*/
		String filePath = PATH + "USTC_SOFTWARE_BIOBRICKS_DATA.txt";
		FileReader infile = new FileReader(filePath);
		BufferedReader in = new BufferedReader(infile);
		
		
		/*------------- initializing numOfGenes and numOfPromoters-------*/
		int numOfGenes = Integer.parseInt(in.readLine());
		int numOfPromoters = Integer.parseInt(in.readLine());
		
		
		/*------------------initializing geneNames--------------*/
		Vector<String> geneNames = new Vector<String>();
		for(int i = 0;i < numOfGenes; i++){
			geneNames.add(in.readLine());
		}
		
		
		/*------------------initializing promoterNames-------------*/
		Vector<String> promoterNames = new Vector<String>();
		for(int i = 0;i < numOfPromoters;i++){
			promoterNames.add(in.readLine());
		}
		
		
		/*---------------------generate database------------------/
		int[][] database = new int[numOfGenes][numOfPromoters];
		for(int i = 0;i < numOfGenes; i++){
			for(int j = 0;j < numOfPromoters;j++){
				database[i][j] = Integer.parseInt(in.readLine());
			}
		}
		*/
		int[][] database = new int[5][5];
		for(int i = 0;i < 5; i++){
			for(int j = 0;j < 5;j++){
				database[i][j] = ((int)(10*Math.random()))%3-1;
				System.out.print(database[i][j]+"\t");
			}
			System.out.print("\n");
		}
		
		/*---------initializing choicesPoolOfRows and choicesPoolOfColumns--*/
		int[] choicesPoolOfRows = new int[numOfGenes];
		for(int i = 0;i < numOfGenes;i++){
			choicesPoolOfRows[i] = i;
		}
		int[] choicesPoolOfColumns = new int[numOfPromoters];
		for(int i = 0; i < numOfPromoters; i++){
			choicesPoolOfColumns[i] = i;
		}
	
		
		/*------------initializing some parameters------------------*/		
		int numberOfRowChoicesInPool = 5; 
		int numberOfColmunsChoicesInPool = 5;
		int numberOfChoicesToBeChosen = matrixSize;
		int[] numberOfPossibleChoices = new int[1];
		numberOfPossibleChoices[0] = 0;
		
		
		/*------------initializing targetMatrix------------------*/
		int[][] targetMatrix = new int[matrixSize][matrixSize];
		for(int i = 0;i < matrixSize;i++){
			for(int j = 0;j < matrixSize; j++){
				targetMatrix[i][j] =  ((int)(10*Math.random()))%3-1;
				System.out.print(targetMatrix[i][j]+"\t");
			}
			System.out.print("\n");
		}

		
		/*----------------------generate result matrix------------------*/
		int[][][] result = findMatrixRecursion2(database,targetMatrix,choicesPoolOfRows,choicesPoolOfColumns,numberOfRowChoicesInPool,numberOfColmunsChoicesInPool,numberOfChoicesToBeChosen,numberOfPossibleChoices);
		System.out.print(numberPossibleChoices+"\n\n");
		for(int i = 0;i < numberPossibleChoices; i++){
			System.out.print(i+"\n");
			for(int j = 0;j < matrixSize ; j++){
				System.out.print(result[i][0][j]+"\t");
			}
			System.out.print("\n");
			for(int j = 0;j < matrixSize;j++){
				System.out.print(result[i][1][j]+"\t");
			}
			System.out.print("\n\n");
		}
	}


	//==============================================================================//
    //                                                                              //
    //  findMatrixRecurssion2 method                                                //
    //  purpose: find small matrix in a big matrix                                  //
    //  it uses recursion algorithm to find MxM matrix in NxN matrix, based on      //
    //      recursion of finding (M-1)x(M-1) matrix in (N-1)x(N-1) matrix           //
    //  parameters:                                                                 //
    //          databaseMatrix is the global giant matrix                           //
    //          targetMatrix is the current matrix to be find						//
	//																				//
    //          choicesPoolOfRows contains the indices of rows can be chosen		//
	//			choicesPoolOfColumns contains the indices of columns can be chosen	//	
    //																				//
	//			numberOfRowChoicesInPool is the size of choicesPool of rows			//
	//			numberOfColumnChoicesInPool is the size of choicesPool of columns	//
    //																				//
	//			numberOfChoicesToBeChosen is the size of targetMatrix               //
    //          numberOfPossibleChoicesSets is the number of possible solutions     //
    //  return value:                                                               //
    //          return value is the 2-d array that contains the possible solutions  //
    //                                                                              //
    //==============================================================================//
	public static int[][][] findMatrixRecursion2(
			int[][] databaseMatrix,
			int[][] targetMatrix,
			int[] choicesPoolOfRows,
			int[] choicesPoolOfColumns,
			int numberOfRowChoicesInPool,
			int numberOfColmunsChoicesInPool,
			int numberOfChoicesToBeChosen,
			int[] numberPossibleChoices
			){
		
		if(numberOfChoicesToBeChosen == 1){
			int numPossible = 0;
			Vector<int[][]> choicesVector = new Vector<int[][]>();
			for(int i = 0;i < numberOfRowChoicesInPool;i++){
				for(int j = 0;j<numberOfColmunsChoicesInPool;j++){
					if(databaseMatrix[choicesPoolOfRows[i]][choicesPoolOfColumns[j]] == 2 ||
					   databaseMatrix[choicesPoolOfRows[i]][choicesPoolOfColumns[j]] ==targetMatrix[0][0] ){
						numPossible ++;
						int[][] aChoice = new int[2][1];
						aChoice[0][0] = choicesPoolOfRows[i];
						aChoice[1][0] = choicesPoolOfColumns[j];
						choicesVector.add(aChoice);
					}
					
				}
			}
			int[][][] choices = new int[numPossible][2][1];
			for(int i = 0;i < numPossible; i++){
				choices[i] =(int[][]) (choicesVector.toArray())[i];
			}
			numberPossibleChoices[0] = numPossible;
			return choices;
		}
		
		
	int[][] newTargetMatrix = new int[numberOfChoicesToBeChosen-1][numberOfChoicesToBeChosen-1];
	int numOfWorkingChoices = 0;
	Vector<int[][]> choicesVector = new Vector<int[][]>();
	
	for(int i = 0;i < numberOfChoicesToBeChosen - 1;i++){
		for(int j = 0;j < numberOfChoicesToBeChosen - 1;j++){
			newTargetMatrix[i][j] = targetMatrix[i+1][j+1];
		}
	}
	
	int[][] firstElement = new int[2][1];
	for(int i = 0;i < numberOfRowChoicesInPool; i++){
		for(int j = 0;j < numberOfColmunsChoicesInPool;j++){
			if(databaseMatrix[choicesPoolOfRows[i]][choicesPoolOfColumns[j]] != 2&&
			   databaseMatrix[choicesPoolOfRows[i]][choicesPoolOfColumns[j]] != targetMatrix[0][0]){
			continue;}
		firstElement[0][0] = choicesPoolOfRows[i];
		firstElement[1][0] = choicesPoolOfColumns[j];	
		
		int[] newPoolOfRow = new int[numberOfRowChoicesInPool-1];
		for(int h = 0, k = 0; h < numberOfRowChoicesInPool; h ++){
			if( i != h){
				newPoolOfRow[k] = choicesPoolOfRows[h];
				k++;
			}
		}
		
		int[] newPoolOfColumn = new int[numberOfColmunsChoicesInPool - 1];
		for(int g = 0, k = 0; g < numberOfColmunsChoicesInPool; g ++){
			if( j != g){
				newPoolOfColumn[k] = choicesPoolOfColumns[g];
				k++;
			}
		}
		int[] numberOfNewSets = new int[1];
		numberOfNewSets[0] = 0; 
		int[][][]possibleSolutions = findMatrixRecursion2(databaseMatrix,newTargetMatrix,newPoolOfRow,newPoolOfColumn,numberOfRowChoicesInPool-1,numberOfColmunsChoicesInPool-1,numberOfChoicesToBeChosen-1,numberOfNewSets);
		
		for(int k = 0;k < numberOfNewSets[0];k++){
			boolean work = true;
			for(int m = 0; m < numberOfChoicesToBeChosen-1;m++){
				if((databaseMatrix[firstElement[0][0]][possibleSolutions[k][1][m]] != 2 &&
	               targetMatrix[0][m + 1] != databaseMatrix[firstElement[0][0]][possibleSolutions[k][1][m]]) ||
	               (databaseMatrix[possibleSolutions[k][0][m]][firstElement[1][0]] != 2 &&
	               targetMatrix[m + 1][0] != databaseMatrix[possibleSolutions[k][0][m]][firstElement[1][0]]) )
				{
					work = false;
					break;
				}
			}
			if(work){
				numOfWorkingChoices ++;
				int[][] aChoice = new int[2][numberOfChoicesToBeChosen];
				aChoice[0][0] = firstElement[0][0];
				aChoice[1][0] = firstElement[1][0];
				for(int n = 1; n < numberOfChoicesToBeChosen; n++ ){
						aChoice[0][n] = possibleSolutions[k][0][n - 1];
						aChoice[1][n] = possibleSolutions[k][1][n - 1];
					}
					choicesVector.add(aChoice);
				}
				
			}
		}
	}
		
	numberPossibleChoices[0] = numOfWorkingChoices;
	int[][][] choices = new int[numOfWorkingChoices][2][numberOfChoicesToBeChosen];
	for(int i =  0;i < numOfWorkingChoices;i++){
		choices[i] = (int[][])choicesVector.toArray()[i];
	}
	return choices;
	}
}
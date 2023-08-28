
public class Tesing_Class {

	public static void main(String[] args)  {
	
	try {
		MethodsAndRead x = new MethodsAndRead("C:\\Users\\aa\\Desktop\\docu.txt");
		
	
		x.printArr();
		System.out.println("OP1: file length " + x.documentLength());
		System.out.println("OP2: Unique length " + x.uniqueWords());
		System.out.println("OP3: total occ of word the: " + x.totalOccurrancesForWord("the")  );
		System.out.println("total number of words with length 2: " + x.totalWordsForLength(2))  ;
		x.displayUniqueWords();
		
		LinkedList<WordOccurrence> LO = x.occurrence("data");
		LO.findFirst();
		while(!LO.last()) {
			System.out.print("(" + LO.retrieve().lineNo + ", " + LO.retrieve().position +"), ");
			
			LO.findNext();
		}
		System.out.println();
		
		LinkedList<WordOccurrence> LO2 = x.occurrence("the");
		LO2.findFirst();
		while(!LO2.last()) {
			System.out.print("(" + LO2.retrieve().lineNo + ", " + LO2.retrieve().position +"), ");
			
			LO2.findNext();
		}
		System.out.println();
		
		
		
		if(x.checkAdjacent("data", "the") ) {
			System.out.println(" true");
		}
		else  System.out.println("false");
		
	
		
		
	}catch (Exception e){
		e.printStackTrace();
		
	}
	
		
	
	
	
	
	
					
					
			
				
				
				
	}
}//class



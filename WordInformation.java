
public class WordInformation {

	public String word;
	LinkedList<WordOccurrence> occList;
	int size;
	
	public WordInformation(String w, int l, int pos) {
		
		word = w;
		
			occList = new LinkedList<WordOccurrence>();
			
			occList.insert(  new WordOccurrence(l,pos)	);
		
		
		size++;
		
	}
	
	
	public void insertWO( WordOccurrence e) {
		
		occList.insert( e );
		size++;
	}
	
	public void printOccurrences() {
		occList.findFirst();
		while( !occList.last()) {
		
			System.out.println("line number: " +occList.retrieve().lineNo + " Pos:" + occList.retrieve().position);
			occList.findNext();
				
		}
		
		
	}
	
	
}

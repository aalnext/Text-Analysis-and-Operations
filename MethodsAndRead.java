import java.io.*;
import java.util.Scanner;

public class MethodsAndRead {

	public LinkedList<WordInformation>[] arr;
	public int count, countU;
	public WordInformation sortedArray[];

	public MethodsAndRead(String filename) throws Exception {
		try {

			File f = new File(filename);
			Scanner sc = new Scanner(f);
			int line = 0, pos = 0;

			int k = 0, len = 0;

			while (sc.hasNext()) {

				String w = sc.next().replaceAll("(?!['-])\\p{Punct}", "");

				len = w.length();
				if (k < len)

					k = len;
				count++;

			} // while read loop
			sc.close();
			sc = null;
			sc = new Scanner(f);

		//	System.out.println(count); // remove later
		//	System.out.println(k); // remove later

			arr = (LinkedList<WordInformation>[]) new LinkedList[k + 1];

			for (int i = 1; i < arr.length; i++)
				arr[i] = new LinkedList<WordInformation>(); // Figure it out later

			while (sc.hasNextLine()) {

				String Line = sc.nextLine();

				pos = 0;
			//	System.out.println(Line); // remove later
				line++;

				String words[] = Line.split("\\s+");

				for (int i = 0; i < words.length; i++) {

					String cleanWord = words[i].replaceAll("(?!['-])\\p{Punct}", "");

					int wL = cleanWord.length();
					pos++;

					WordInformation WordInfo = new WordInformation(cleanWord, line, pos);

					if (arr[wL] != null) { // if there is empty lines

						if (arr[wL].empty()) { // if LL is empty
							arr[wL].insert(WordInfo);
							countU++;
							arr[wL].sizeLL++;
						}

						else { // if LL is not empty

							boolean flag = false;

							arr[wL].findFirst();

							while (!arr[wL].last()) {

								if (arr[wL].retrieve().word.equalsIgnoreCase(cleanWord)) { // if word is found
									
									arr[wL].retrieve().insertWO(new WordOccurrence(line, pos));
									flag = true;
									arr[wL].sizeLL++;
								
								} // if

								arr[wL].findNext();
							} // while

							if (!flag) { // if word is not found
							
								arr[wL].insert(WordInfo);
								countU++;
								arr[wL].sizeLL++;

							}
						}

					}

				}

			}

			// CHECKING SORTED ARRAY
			System.out.println("unique words : " + countU); // remove later

			sortedArray = new WordInformation[countU];

			int nbS = 0;
			for (int i = 1; i < arr.length; i++) {

				if (arr[i] != null) {
					arr[i].findFirst();

					while (!arr[i].last()) {
						sortedArray[nbS++] = arr[i].retrieve();
						arr[i].findNext();

					}
				}
			}

			for (int i = 0; i < sortedArray.length -1; i++) {
				for (int j = 0; j < sortedArray.length -1 - i; j++) {
					if (sortedArray[j].size < sortedArray[j + 1].size) {
						WordInformation tmp = sortedArray[j];
						sortedArray[j] = sortedArray[j + 1];
						sortedArray[j + 1] = tmp;
					}
				}
			}

			 for (int i = 0; i < sortedArray.length; i++) {   // remove later
				WordInformation testW = sortedArray[i];
				System.out.println(testW.word + " " + testW.size);
			}

			// CHECKING SORTED ARRAY

			sc.close();

		} catch (Exception e) {
		}

	}

	 public void printArr() {  //remove later

		System.out.println("in printArr() ==============");

		for (int i = 1; i < arr.length; i++)
			if (!arr[i].empty()) {
				arr[i].findFirst();
				while (!arr[i].last()) {

					System.out.println(arr[i].retrieve().word);
					arr[i].retrieve().printOccurrences();
					arr[i].findNext();

				}
			}

	}

	/*public static void readFileAndAnalyse(String filename) {
		try {
			MethodsAndRead x = new MethodsAndRead(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	public int documentLength() {
		return count;
	}

	public int uniqueWords() {
		return countU;
	}

	public int totalOccurrancesForWord(String w) {
			int t=0;
			
		for(int i =0; i< sortedArray.length; i++) {
			
			if(sortedArray[i].word.equalsIgnoreCase(w)) {
				t = sortedArray[i].size;
				break;
			}//if
		}//for
		
		return t;
	
	}
	
	public int totalWordsForLength(int l) {
		
	if(arr[l].empty())
		return -1;
		
		return arr[l].sizeLL;
	}
	
	
	
	public void displayUniqueWords() {
		for (int i = 0; i < sortedArray.length; i++)
			System.out.print("(" + sortedArray[i].word + ", " + sortedArray[i].size + "), ");
		System.out.println();
		
	}

	public LinkedList<WordOccurrence> occurrence(String w) {
		int length = w.length();
		arr[length].findFirst();

		for (int i = 1; i < arr[length].sizeLL; i++) {
			
			if (w.equalsIgnoreCase(arr[length].retrieve().word)) {
				return arr[length].retrieve().occList;
			} else
				arr[length].findNext();
		}
		return null;
	}
	

	public boolean checkAdjacent(String w1, String w2) {
		
		LinkedList<WordOccurrence> w1o = occurrence(w1);
		
		
		if(w1o==null) {
			System.out.println("w1o is null");
			return false;
		}
		
		LinkedList<WordOccurrence> w2o = occurrence(w2);
		
		if(w2o==null) {
			System.out.println("w2o is null");
			return false;
		}
	
		
		w1o.findFirst();
		w2o.findFirst();
		
		int pos1, pos2;
	
		while(!w1o.last() )
		{
			
			if(w1o.retrieve().lineNo == w2o.retrieve().lineNo	) {
					pos1= w1o.retrieve().position;
					pos2= w2o.retrieve().position;
					
				if( pos1-pos2 == 1  )
						return true;
				 if( pos2-pos1 ==1)
						return true;
				
				 w2o.findNext();
			} 
						
							
						if( w2o.last() ) {
							System.out.println("here");
							w1o.findNext();
							w2o.findFirst();
						}	
		}//while
		
		return false;
	}

	
	public void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;
 
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
	
	public void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
	
	
	
	

}// class

import java.util.Random;


public class tester {

	
	
	
	
	
	public static void main(String[] args) {
		int m = 100000;
		int[] intarr = new int[m];
		Random rnd = new Random();
		for(int j=0 ; j<m;j++){
			intarr[j] = rnd.nextInt(1000);
		}
		DHeap_Item[] arr= new DHeap_Item[100000];
		for(int k =0 ; k< m;k++){
			arr[k] = new DHeap_Item(Integer.toString(intarr[k]), intarr[k]);
		}
		DHeap heap = new DHeap(4, m);
		heap.arrayToHeap(arr);
		for(int g =0 ; g < m;g++){
			heap.Decrease_Key(arr[g], 1000);
		}
	}
}

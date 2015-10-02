
/**
 * D-Heap
 */



/*
 * Name:Boris Morozov
 * 
 * ID:314396177
 * 
 */


public class DHeap
{
	
    private int size, max_size, d;
    private DHeap_Item[] array;

	// Constructor
	// m_d >= 2, m_size > 0
       DHeap(int m_d, int m_size) {
               max_size = m_size;
			   d = m_d;
               array = new DHeap_Item[max_size];
               size = 0;
       }
	
	// Getter for size
	public int getSize() {
		return size;
	}
	
  /**
     * public void arrayToHeap()
     *
     * The function builds a new heap from the given array.
     * Previous data of the heap should be erased.
     * preconidtion: array1.length() <= max_size
     *   postcondition: isHeap()
     *                            size = array.length()
     *                            
     *                            
     */
	
	
	
	/*
	 * Overwrites the old array updates the fields
	 * 
	 * complexity O(n) we saw it in class for d=2 the same summation will yield O(n) 
	 */
	
	
    public void arrayToHeap(DHeap_Item[] array1) {
    	this.size = array1.length;
    	this.array = array1.clone();
    	for(int k=0;k<this.size;k++){
    		this.array[k].setPos(k);
    	}
        int first_parant_index = this.firstNonLeaf();
        for(int i = first_parant_index;i >= 0 ;i--){
        	this.Heapify_down(i);
        }
    }

    /**
     * public boolean isHeap()
     *
     * The function returns true if and only if the D-ary tree rooted at array[0]
     * satisfies the heap property or size == 0.
     *   
     */
    
    
    /*
     * return true if the items in the array representing the heap "obey" the heap order .  
     * complexity O(n)  
     */
    
    
    public boolean isHeap() {
        for(int i = this.size -1; i > 0;i--){
        	if(this.array[i].getKey() < this.array[this.parent(i)].getKey()){
        		return false;
        	}
        }
        return true;
    }


 /**
     * public int parent(i), child(i,k)
     * (2 methods)
     *
     * precondition: i >= 0
     *
     * The methods compute the index of the parent and the k-th child of 
     * vertex i in a complete D-ary tree stored in an array. 1 <= k <= d.
     * Note that indices of arrays in Java start from 0.
     */
    
    
    
    /*
     * 
     * complexity for both is O(1) (find on array)
     * 
     */
    
    public int parent(int i) { 
    	return (i-1)/this.d;} 
    
    
    public int child (int i, int k) { 
    	if(d*i+k < this.max_size  && this.array[d*i + k] == null){
    		return -1;
    	}
    	else{
    		return d*i + k;
    	}
    }
    
    
    /**
    * public void Insert(DHeap_Item item)
    *
    * precondition: item != null
    *               isHeap()
    *               size < max_size
    * 
    * postcondition: isHeap()
    */
    
    
    
    
    /*
     * 
     * adds the element at the end of the array then call Heapify_up on it 
     * 
     * complexity O(Logd(a))
     */
    
    public void Insert(DHeap_Item item) {        
    	this.array[this.size] = item;
    	this.array[this.size].setPos(this.size);
    	this.Heapify_up(this.size);
    	this.size++;
    }

 /**
    * public void Delete_Min()
    *
    * precondition: size > 0
    *               isHeap()
    * 
    * postcondition: isHeap()
    */
    
    
    /*
     * switch places between the last and the first elements in the array . than delete last item . and Heapify_down on new root . 
     * 
     * Complexity O(Logd(n))
     * 
     * 
     */
    
    
    public void Delete_Min(){
    	this.size--;
     	this.Switcheroo(0, this.size);
     	this.array[size] = null;
     	this.Heapify_down(0);
    }


    /**
     * public String Get_Min()
     *
     * precondition: heapsize > 0
     *               isHeap()
     *		size > 0
     * 
     * postcondition: isHeap()
     */
    
    
    /*
     * return the first element in the array 
     * 
     * comoplexity O(1)
     */
    
    
    public DHeap_Item Get_Min(){return this.array[0];}
	
  /**
     * public void Decrease_Key(DHeap_Item item, int delta)
     *
     * precondition: item.pos < size;
     *               item != null
     *               isHeap()
     * 
     * postcondition: isHeap()
     */
    
    
    
    /*
     * lower the key of a given node than call for Heapify_up to fix the invariants 
     * 
     * complexity O(Logd(n))
     * 
     */
    
    
    public void Decrease_Key(DHeap_Item item, int delta){
    	item.setKey(item.getKey() - delta);
    	this.Heapify_up(item.getPos());
    }
	
	  /**
     * public void Delete(DHeap_Item item)
     *
     * precondition: item.pos < size;
     *               item != null
     *               isHeap()
     * 
     * postcondition: isHeap()
     */
    
    
    
    /*
     * switch places between the last node and the given one , delete last node , than Heapify_down 
     * 
     * complexity O(Logd(n))
     */
    
    
    
    public void Delete(DHeap_Item item){
    	this.size--;
    	int temp = item.getPos();
    	this.Switcheroo(temp, this.size);
    	this.array[this.size] = null;
    	this.Heapify_down(temp);
    }
	
	/**
	Return a sorted array containing the same integers in the input array.
	Sorting should be done using the DHeap.
	*
	*/
    
    
    
    /*
     * build heap than do n times delete min . 
     * 
     * 
     * 
     * complexity O(n*Log(n))   building heap takes O(n) and n times delete-min takes O(n*Logn)  
     */
    
    
	public static int[] DHeapSort(int[] array) {
		DHeap heap = new DHeap(2, array.length);
		heap.arrayToHeap(DheapArrayFromInt(array));
		int[] output = new int[array.length];
		for(int i =0;i< array.length;i++){
			output[i] = heap.array[0].getKey();
			heap.Delete_Min();
		}
		return output;
	}
	
	
	
	/*
	 * lowers the node location in the tree if needed . and recursively calls for itself 
	 * 
	 * at most Logd(n) calls constant time each one so in totall O(Logd(n))
	 */
	
	private void Heapify_down(int k){
		if(this.min_Child(k) == -1){
			return;
		}
		if(this.array[k].getKey() > this.array[this.min_Child(k)].getKey()){
			int temp = this.min_Child(k);
			this.Switcheroo(k, temp);
			Heapify_down(temp);
		}
	}
	
	
	/*
	 * 
	 * Symmetric to Heapify_down
	 * 
	 */
	
	private void Heapify_up(int k){
		if(this.array[k].getKey() < this.array[this.parent(k)].getKey() && k != 0){
			this.Switcheroo(k, this.parent(k));
			Heapify_up(this.parent(k));
		}
	}
	
	
	/*
	 * switches places for heap item that is in the i'th and j'th index in the array . 
	 * 
	 * complexity O(1)
	 */
	
	
	public void Switcheroo(int i,int j){
		DHeap_Item temp1 = this.array[i];
		DHeap_Item temp2 = this.array[j];
		this.array[i] = temp2;
		this.array[i].setPos(i);
		this.array[j] = temp1;
		this.array[j].setPos(j);
	}
	
	/*
	 * returns the index of the child with the min key if none return -1
	 * 
	 * complexity O(d) (number of potential candidates)
	 */
	
	private int min_Child(int i){
		int[] temp = new int[this.d];
		if(i*this.d +1 >= this.size || this.array[i*this.d + 1] == null ){
			return -1;
		}
		for(int j =0; j < this.d; j++){
			temp[j] = i*this.d+j;
		}
		int output = temp[0];
		for(int k = 0 ;k<this.d;k++){
			if(temp[k] >= this.array.length || this.array[temp[k]] == null){
				break;
			}
			if(this.array[temp[k]].getKey() < this.array[output].getKey()){
				output = temp[k];
			}
		}
		return output;
	}
	
	
	/*
	 * return true if node have no children 
	 * else return false 
	 * 
	 * complexity O(1)
	 */
	
	public boolean isLeaf(int i){
		if(i*this.d + 1 >=  this.size){
			return true;
		}
		return false;
	}
	
	/*
	 * find the last node in the array that have children  
	 * 
	 * complexity O(n)
	 */
	
	public int firstNonLeaf(){
		int i = this.size - 1;
		while(this.isLeaf(i)){
			i--;
		}
		return i;
	}
	
	public static DHeap_Item[] DheapArrayFromInt(int[] array){
		DHeap_Item[] output = new DHeap_Item[array.length];
		for(int i=0;i< array.length;i++){
			output[i] = new DHeap_Item(Integer.toString(i), array[i]);
			output[i].setPos(i);
		}
		return output;
	}
}
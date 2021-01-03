package dhd170001;

import java.util.*;

/** Starter code for P3
 *  @author
 *  Danny Duong, dhd170001
 */

// Change to your net id

// If you want to create additional classes, place them in this file as subclasses of MDS

public class MDS {
    // Add fields of MDS here
	TreeMap<Integer, Item> tree; // ID to item
	HashMap <Integer, HashSet<Item>> table; // ID 

    // Constructors
    public MDS() 
    {
    	tree = new TreeMap<>();
    	table = new HashMap<>();
    }
    
    /*
     * Item class is created under MDS. Purpose of this class neatly hold multiple attributes that an item contains
     * which is an id and a list of integers that serve as the item's description
     * and its price into one object
     * */
    public class Item {

    	int id;  
		ArrayList<Integer> description;
		int price;
		
		public Item() { //default constructor
			id = 0;
			description = new ArrayList<Integer>(); 
			price = 0;
		}
		
		public Item(int id, int price, List<Integer> list) {
			this.id = id;
			this.description = new ArrayList<Integer>();
			this.price = price;
			
			for (int i: list) {
				this.description.add(i); 
			}
		}
    }

    /* Public methods of MDS. Do not change their signatures.
       __________________________________________________________________
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated. 
       Returns 1 if the item is new, and 0 otherwise.
    */
    public int insert(int id, int price, java.util.List<Integer> list) {
    	
		if (tree.containsKey(id)) {
					
			if (!(list == null || list.isEmpty())) { //check for list empty
				
				helpDelete(id);
				
				tree.get(id).description.clear();
				
				for (int i: list) { 
					tree.get(id).description.add(i); 
				}
				
				helpInsert(id);
				
			}
			tree.get(id).price = price;
			
			return 0;
		}
	
		Item newItem = new Item(id, price, list);
		
		tree.put(id, newItem);
		
		helpInsert(id);

		return 1;
    }
    
    /*Helper Method
     * this delete method is to serve for the insert function which deletes the item based off its id
     * which is done through finding the ID and then removing the the object/set in the list
     * */
    private void helpDelete(int id) {
		
		Item remove = tree.get(id); 
		
		for (Integer i: remove.description) {
			
			table.get(i).remove(remove);
			
			if (table.get(i).isEmpty()) {table.remove(i);} 
		}
	}
    
    /*Helper function
     * This insert method is for helping the insert method add objects into a new set
     * this is done through iterating the object that is found and after removing the descriptions that were there initially
     * the new set of numbers from the object is put in
     * */
    private void helpInsert(int id) {
		
		Item inItem = tree.get(id);
		
		for (int i: inItem.description) {
			
			if (table.containsKey(i)) {
				table.get(i).add(inItem); 
			}
			
			else {
				HashSet<Item> newSet = new HashSet<Item>();
				newSet.add(inItem); 
				table.put(i, newSet); 
			}
		}
	}

    // b. Find(id): return price of item with given id (or 0, if not found).
    public int find(int id) {
    	
    	if (tree.containsKey(id)) {return tree.get(id).price;}

		return 0;
    }

    /* 
       c. Delete(id): delete item from storage.  Returns the sum of the
       ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public int delete(int id) {
    	
		if (!tree.containsKey(id)) {return 0;}

		Item remove = tree.get(id);
		
		int sum = 0; 
		
		for (int i: remove.description) {
			
			table.get(i).remove(remove); 
			
			if (table.get(i).isEmpty()) {table.remove(i);}
			
			sum += i; 
		}

		tree.remove(id);
		
		return sum;	
    }

    /* 
       d. FindMinPrice(n): given an integer, find items whose description
       contains that number (exact match with one of the ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public int findMinPrice(int n) {
    	
    	if (!table.containsKey(n)) {return 0;}
		
		HashSet<Item> set = table.get(n); // temporary HashSet
		int min = Integer.MAX_VALUE;
		
		for (Item i: set) {
			if (i.price < min) 
				min = i.price;
		}
		return min;
    }

    /* 
       e. FindMaxPrice(n): given an integer, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public int findMaxPrice(int n) {
    	
    	if (!table.containsKey(n)) {return 0;}
		
		HashSet<Item> set = table.get(n);
		int max = 0;
		
		for (Item i: set) {
			if (i.price > max) 
				max = i.price;
		}
		return max;

    }

    /* 
       f. FindPriceRange(n,low,high): given int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(int n, int low, int high) {
    	
    	if (low > high) {
			return 0;
		}
		
		HashSet<Item> tempSet = table.get(n);
		int count = 0; 		
		
		for (Item i: tempSet) {
			if (! (i.price < low || i.price > high))
				{count++;}
		}
		
		return count;
    }

    /*
      g. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public int removeNames(int id, java.util.List<Integer> list) {
	
    	if (list == null || list.isEmpty()) {return 0;} 
    	
		Item toBeRemoved = tree.get(id);
		
		int sum = 0;
		
		for (int i: list) {
			
			if (toBeRemoved.description.contains(i)) {
				
				sum += i; 
				
				table.get(i).remove(toBeRemoved); 
				
				if (table.get(i).isEmpty()) 
					table.remove(i); 
			}
			//System.out.println(pTree.get(id).description.size());
			//System.out.println(d);
			//pTree.get(id).description.; This is for the description being a set rather than a list
		}
		
		return sum;
	}
}

/******************************************************************
 *
 *   Adrian Mleczek/001
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     *    If x == y, both boulders are destroyed, and
     *    If x != y, the boulder of weight x is destroyed, and the boulder of
     *               weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

  public static int lastBoulder(int[] boulders) {

      //
      // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME / SECTION # ABOVE
      //
      //use comparator to intialize in reverse order
      PriorityQueue<Integer> reverse = new PriorityQueue<>(Comparator.reverseOrder());
      //populate the queue
      for (int i: boulders) {
            reverse.offer(i);
        }
      while (reverse.size() > 1) {
           //record two largest boulders
            int x = reverse.poll();  
            int y = reverse.poll();
            //compare the two largest
            if (x != y) {
                //return new boulder to queue
                reverse.offer(x-y); 
            }
      }
      if(reverse.isEmpty()){
          return 0;
      }
      else
      {
         return reverse.peek();
      }
  }


    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param  input an ArrayList<String>
     * @return       an ArrayList<String> containing only unique strings that appear
     *               more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {
        
        //hashmap allows us to efficently store a string and the # of times it repeats
        HashMap<String, Integer> dupNum = new HashMap<>();
        
        
        //populates the hashmap 
        for (String inp : input) {
            dupNum.put(inp, dupNum.getOrDefault(inp, 0) + 1);
        }

        //create priorityQueue to store strings with priority
        PriorityQueue<String> temp = new PriorityQueue<>();

        //populates queue
        for (Map.Entry<String, Integer> entry : dupNum.entrySet()) {
            if (entry.getValue() > 1) {
                temp.offer(entry.getKey());
            }
        }

        //create and populate an arraylist to return
        ArrayList<String> result = new ArrayList<>();
        while (temp.isEmpty()==false) {
            result.add(temp.poll()); 
        }

        return result;
        

    }


    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input   Array of integers
     * @param k       The sum to find pairs for

     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *        of strings needs to be ordered both within a pair, and
     *        between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *            A string is a pair in the format "(a, b)", where a and b are
     *            ordered lowest to highest, e.g., if a pair was the numbers
     *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *            The ordering of strings of pairs should be sorted in lowest to
     *            highest pairs. E.g., if the following two string pairs within
     *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *  HINT: Considering using any Java Collection Framework ADT that we have used
     *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
     *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
     *  Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {

        //
        //  YOUR CODE GOES HERE
        //
        //hashset to store value already iterated through
        HashSet<Integer> store = new HashSet<>();
        //arraylist to store pairs
        ArrayList<String> result = new ArrayList<>();
        //integers to store members of each pair
        int sma,lrg;
        //iterate through given array
        for(int i :input)
        {
            //we know that k = i + remainder
            int remainder = k - i;
            //iterates to see if such a remainder exists
            if(store.contains(remainder))
            {
                //determines order within the pair
                if(i>remainder)
                {
                    lrg = i;
                    sma = remainder;
                }
                else
                {
                   sma = i;
                   lrg = remainder; 
                }
                //adds pair to set while checking to see if the pair already exists
                if(!result.contains("(" +sma+", "+ lrg+")"))
                {
                result.add("(" +sma+", "+ lrg+")");
                }
            }
            store.add(i);
        }
        //sorts and returns
        Collections.sort(result);
        return result;  // Make sure returned lists is sorted as indicated above
    }
}

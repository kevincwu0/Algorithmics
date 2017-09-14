
import java.util.ArrayList;
import java.lang.Math;

public class MaximizingStockPrice {

  // O(n^2) time => looks for price after the outer loop
  static int getFirstAttemptMaxProfit(int[] stockPricesYesterday) {
    int maxProfit = 0;
    for(int i = 0; i < stockPricesYesterday.length; i++) {
      for(int j = i+1; j < stockPricesYesterday.length-1; j++) {
        int currentProfit = stockPricesYesterday[j] - stockPricesYesterday[i];
        System.out.println("Current Profit " + currentProfit);
        if (currentProfit > maxProfit) {
          maxProfit = currentProfit;
        }
      }
    }
    return maxProfit;
  }

  // O(n^2) time => leads to negative profits...plus going through every other time
  static int getBruteForceMaxProfit(int[] stockPricesYesterday) {
    int maxProfit = 0;
    for (int outerTime = 0; outerTime < stockPricesYesterday.length; outerTime++) {
      for (int innerTime = 0; innerTime < stockPricesYesterday.length; innerTime++) {
        int earlierTime = Math.min(outerTime, innerTime);
        int laterTime = Math.max(outerTime, innerTime);

        int earlierPrice = stockPricesYesterday[earlierTime];
        int laterPrice = stockPricesYesterday[laterTime];

        int potentialProfit = laterPrice - earlierPrice;
        maxProfit = Math.max(maxProfit, potentialProfit);
      }
    }
    return maxProfit;
  }

  // Greedy approach, one iteration
  // minPrice, update when the difference between of minPrice and currentPrice > maxProfit
  // One pass and constant space! But what about edge cases?

  // What if the stock value stays the same? What if the stock value goes down all day?
  // Max profit is 0 if it doesn't change, so we're good.
  // If the value goes down all day, we're in trouble as it would return 0 but no way we could break even

  // Two possibilities:
  // 1. return a negative profit - "least badly we could've done"
  // 2. throw an exception - "should not have purchased stocks yesterday!"
  // Option (1) is better to more accurately answer the challenge
  // Option (1) is less opionated - and allow us to collect better data
  // never have a case where both buying and selling stocks at currentPrice
  static int getFirstAttemptGreedyProfit(int[] stockPricesYesterday) {
    int maxProfit = 0;
    int minPrice = stockPricesYesterday[0];

    for (int currentPrice: stockPricesYesterday) {
      minPrice = Math.min(minPrice, currentPrice);
      int potentialProfit = currentPrice - minPrice;
      maxProfit = Math.max(maxProfit, potentialProfit);
    }
    return maxProfit;
  }

  // O(n) time and O(1) space - looping through the array once
  // Trying out greedy apraoch should be one of the first ways to break down a new problem
  // What additional values would we need to keep updated to update the best answer so far
  static int getGreedyMaxProfit(int[] stockPricesYesterday) {
    if (stockPricesYesterday.length < 2) {
      throw new IllegalArgumentException("Getting a profit requires at least 2 prices");
    }
    // greedily update minPrice and maxProfit, so we initialize them
    // to the first price and first possible profit
    int minPrice = stockPricesYesterday[0];
    int maxProfit = stockPricesYesterday[1] - stockPricesYesterday[0];

    // start at the second (index 1) time
    // we can't sell at the first time, since we must buy first,
    // and we can't buy and sell at the same time!
    // if we started at index 0, we'd try to buy *and* sell at time 0.
    // this would give a profit of 0, which is a problem if our
    // maxProfit is supposed to be *negative*--we'd return 0.
    for (int i = 1; i < stockPricesYesterday.length; i++) {
       int currentPrice = stockPricesYesterday[i];

       // see what our profit would be if we bought at the
       // min price and sold at the current price
       int potentialProfit = currentPrice - minPrice;

       // update maxProfit if we can do better
       maxProfit = Math.max(maxProfit, potentialProfit);

       // update minPrice so it's always
       // the lowest price we've seen so far
       minPrice = Math.min(minPrice, currentPrice);
   }

   return maxProfit;
  }

  public static void main(String args[]) {
    int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};
    System.out.println("First attempt Max Profit: " + getFirstAttemptMaxProfit(stockPricesYesterday));
    System.out.println("Brute force Max Profit: " + getBruteForceMaxProfit(stockPricesYesterday));
    System.out.println("Greedy Approach first attempt profit: " + getFirstAttemptGreedyProfit(stockPricesYesterday));
    // Results are all 6 => Buy at 5 sell at 11

    // Testing edge cases

    // #1 Negative profit - goes down all day
    int[] stockPricesYesterdayEdge1 = new int[]{14, 7, 5, 8, 11, 9};
    System.out.println("Negative profit, stock down all day " + getGreedyMaxProfit(stockPricesYesterdayEdge1));

    // #2 Larger Array
    int[] stockPricesYesterdayEdge2 = new int[]{4, 7, 12, 15, 18, 1, 5, 11, 14, 20, 1, 10, 15, 16, 17, 3, 5, 12, 14, 20};
    System.out.println("Greedy Approach max profit larger array: " + getGreedyMaxProfit(stockPricesYesterdayEdge2));
  }
}

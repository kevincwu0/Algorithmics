
import java.util.ArrayList;
import java.lang.Math;

public class MaximizingStockPrice {

  // O(n^2) time
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

  // O(n^2) time
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

  public static void main(String args[]) {
    int[] stockPricesYesterday = new int[]{10, 7, 5, 8, 11, 9};
    System.out.println("First attempt Max Profit: " + getFirstAttemptMaxProfit(stockPricesYesterday));
    System.out.println("Brute force Max Profit: " + getBruteForceMaxProfit(stockPricesYesterday));
  }
}

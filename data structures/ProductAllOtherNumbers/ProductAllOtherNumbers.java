
// For each number in an array, find the product of all the other numbers
// product of every integer except the integer at that index

import java.util.ArrayList;

public class ProductAllOtherNumbers {

  // O(n^2)
  static int[] getNaiveProductsOfAllIntsExceptAtIndex(int[] array) {
    int[] arr = new int[4];
    int currentProduct = 1;
    for(int i = 0; i < array.length; i++) {
      for(int j = 0; j < array.length; j++) {
        if (i != j) {
          currentProduct = currentProduct * array[j];
        }
      }
      arr[i] = currentProduct;
      currentProduct = 1;
    }
    return arr;
  }


  // O(n) time and O(n) space
  // Start with a brute force solution, look for repeat work in that solution, and modify it to only do that work once.
  public static int[] getProductsOfAllIntsExceptAtIndex(int[] intArray) {

    if (intArray.length < 2) {
        throw new IllegalArgumentException("Getting the product of numbers at other indices requires at least 2 numbers");
    }

    // we make an array with the length of the input array to
    // hold our products
    int[] productsOfAllIntsExceptAtIndex = new int[intArray.length];

    // for each integer, we find the product of all the integers
    // before it, storing the total product so far each time
    int productSoFar = 1;
    for (int i = 0; i < intArray.length; i++) {
        productsOfAllIntsExceptAtIndex[i] = productSoFar;
        productSoFar *= intArray[i];
    }

    // for each integer, we find the product of all the integers
    // after it. since each index in products already has the
    // product of all the integers before it, now we're storing
    // the total product of all other integers
    productSoFar = 1;
    for (int i = intArray.length - 1; i >= 0; i--) {
        productsOfAllIntsExceptAtIndex[i] *= productSoFar;
        productSoFar *= intArray[i];
    }

    return productsOfAllIntsExceptAtIndex;
  }

  public static void main(String[] args) {
      int[] array = new int[]{ 1,7,3,4 };
      int[] finalValue = getProductsOfAllIntsExceptAtIndex(array);
      System.out.println("-------------");
      for(int val : finalValue) {
        System.out.print(val + ", ");
      }
  }
}

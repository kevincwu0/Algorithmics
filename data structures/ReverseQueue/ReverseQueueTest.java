import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseQueueTest {
  // 1. Create the Queue
  // 2. Call a reverse function
  // 3. peek everything from Queue (FIFO) to Stack (LIFO)
  // 4. peek everything from Stack to Queue

  static Queue<Integer> queue;

  static void reverseQueue() {
    Stack<Integer> stack = new Stack<Integer>();
    while(!queue.isEmpty()) {
      stack.add(queue.peek());
      queue.remove();
    }

    while(!stack.isEmpty()) {
      queue.add(stack.peek());
      stack.pop();
    }
  }

  public static void main(String args[]) {
      queue = new LinkedList<Integer>();
      queue.add(10);
      queue.add(20);
      queue.add(30);
      queue.add(40);
      queue.add(50);
      queue.add(60);
      queue.add(70);
      queue.add(80);
      queue.add(90);
      queue.add(100);

      reverseQueue();
      while(!queue.isEmpty()) {
        System.out.println(queue.peek() + ", ");
        queue.remove();
      }
  }
}

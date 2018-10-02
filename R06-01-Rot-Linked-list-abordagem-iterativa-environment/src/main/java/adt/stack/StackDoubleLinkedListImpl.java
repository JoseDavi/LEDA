package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

   protected DoubleLinkedList<T> top;
   protected int size;

   public StackDoubleLinkedListImpl(int size) {
      this.size = size;
      this.top = new DoubleLinkedListImpl<T>();
   }

   @Override
   public void push(T element) throws StackOverflowException {
      if (top.size() == size) {
         throw new StackOverflowException();
      } else {
         top.insert(element);
      }
   }

   @Override
   public T pop() throws StackUnderflowException {
      if (top.size() == 0) {
         throw new StackUnderflowException();
      } else {
         T element = ((DoubleLinkedListImpl<T>) top).getLast().getData();
         top.removeLast();
         return element;
      }
   }

   @Override
   public T top() {
      T element = ((DoubleLinkedListImpl<T>) top).getLast().getData();
      return element;
   }

   @Override
   public boolean isEmpty() {
      if (top.size() == 0) {
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean isFull() {
      if (top.size() == size) {
         return true;
      } else {
         return false;
      }
   }

}

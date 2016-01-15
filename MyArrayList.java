/*
* File: MyArrayList.java
* Author: Vianny C. Lugo Aracena
* Date: November 28th, 2015
*/

import java.util.Arrays;

/*
* Creates an arrayList using arrays.
 */
public class MyArrayList {

    private Object[] array;
    private int size = 0;

    /*
    * Constructor
     */
    public MyArrayList() {
        this.array = new Object[10];
    }

    /*
    * Returns element at given index.
     */
    public Object get(int index){
        if(index < this.size){
            return this.array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /*
    * Adds element to the list.
     */
    public void add(Object obj){
        this.array[this.size++] = obj;
    }

    public Object remove(int index){
        if(index < this.size){
            Object obj = this.array[index];
            this.array[index] = null;
            int tmp = index;
            while(tmp < this.size){
                this.array[tmp] = this.array[tmp+1];
                this.array[tmp+1] = null;
                tmp++;
            }
            this.size--;
            return obj;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }
    /*
    * Returns the size of the list.
     */
    public int size(){
        return this.size;
    }

    /*
    * Testing unit.
     */
    public static void main(String a[]){
        MyArrayList ls = new MyArrayList();
        ls.add(5);
        ls.add(3);
        ls.add(4);

        for (int i = 0;i< ls.size(); i++) {
            System.out.print(ls.get(i)+ " ");
        }
        ls.add(9);
        System.out.println("Element at Index 5: " + ls.get(1));
        System.out.println("List size: " + ls.size());
        System.out.println("Removing at index 2: " + ls.remove(2));
        for (int i = 0; i < ls.size(); i++){
            System.out.print(ls.get(i)+" ");
        }
    }
}

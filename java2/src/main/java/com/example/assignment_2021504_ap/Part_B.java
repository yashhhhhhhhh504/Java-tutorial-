package com.example.assignment_2021504_ap;
import java.util.*;
class Balancedtree{
    private int max;
    private int min;
    private int size;
    TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
    TreeMap<Integer,Integer>map2 = new TreeMap<Integer,Integer>();
    public void getSize(int size){
        this.size = size;
    }
    public int getSize(){
        return size;
    }
    public void setMax(){
        this.max = 1000000000;
    }
    public int getMax(){
        return max;
    }
    public void setMin(){
        this.min = -1000000000;;
    }
    public int getMin(){
        return min;
    }
    public void insert() {
        for (int i = 0; i < getSize(); i++) {
            int random = (int) (Math.random() * (getMax() - getMin() + 1) + getMin());
            if (map.containsKey(random)) {
                map.put(random, map.get(random) + 1);
            } else {
                map.put(random, 1);
            }
        }
    }
    public void insert2(){
        for (int i = 0; i < getSize(); i++) {
            int random = (int) (Math.random() * (getMax() - getMin() + 1) + getMin());
            if (map2.containsKey(random)) {
                map2.put(random, map2.get(random) + 1);
            } else {
                map2.put(random, 1);
            }
        }
    }
    public void search(int val){
        if(map.containsKey(val)){
            System.out.println("Found");
        }
        else{
            System.out.println("Not Found");
        }
    }
    public void search2(int val){
        if(map2.containsKey(val)){
            System.out.println("Found");
        }
        else{
            System.out.println("Not Found");
        }
    }
    public void heigh(){
        int height = (int)(Math.log(getSize()+1)/Math.log(2));
        System.out.println("Height of the tree is "+height);

    }
}
class creation extends Thread implements Runnable{
    Balancedtree tree;
    int size;
    public creation(Balancedtree tree,int size){
        this.tree = tree;
        this.size = size;
    }
    public void run(){
        tree.insert2();
    }
}
class search extends Thread implements Runnable{
    Balancedtree tree;
    int val;
    public search(int val){
        this.val = val;
    }
    public search(Balancedtree tree) {
        this.tree = tree;
    }
    public void run(){
        tree.search2(val);
    }
}
class Part_B {
    public static void main(String[] args) {
        Balancedtree tree = new Balancedtree();
        System.out.println("------for 10 inputs----------");
        System.out.println("Without threads");
        tree.getSize(10);
        tree.setMax();
        tree.setMin();
        long start = System.currentTimeMillis();
        tree.insert();
        long end = System.currentTimeMillis();
        System.out.println("Time taken to insert 10 inputs is "+(end-start)+"ms");
        tree.search(10);
        tree.heigh();
        System.out.println("With threads");
        creation thread1 = new creation(tree,10);
        search thread2 = new search(tree);
        long start1 = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Time taken to insert 10 inputs is "+(end1-start1)+" ms");
        tree.search2(10);
        tree.heigh();
        System.out.println("------for 100 inputs----------");
        System.out.println("Without threads");
        tree.getSize(100);
        tree.setMax();
        tree.setMin();
        long start2 = System.currentTimeMillis();
        tree.insert();
        long end2 = System.currentTimeMillis();
        System.out.println("Time taken to insert 100 inputs is "+(end2-start2)+"ms");
        tree.search(10);
        tree.heigh();
        System.out.println("With threads");
        creation thread3 = new creation(tree,100);
        search thread4 = new search(tree);
        long start3 = System.currentTimeMillis();
        thread3.start();
        thread4.start();
        try {
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Time taken to insert 100 inputs is "+(end3-start3)+" ms");
        tree.search2(10);
        tree.heigh();
        System.out.println("------for 1000 inputs----------");
        System.out.println("Without threads");
        tree.getSize(1000);
        tree.setMax();
        tree.setMin();
        long start4 = System.currentTimeMillis();
        tree.insert();
        long end4 = System.currentTimeMillis();
        System.out.println("Time taken to insert 1000 inputs is "+(end4-start4)+"ms");
        tree.search(10);
        tree.heigh();
        System.out.println("With threads");
        creation thread5 = new creation(tree,1000);
        search thread6 = new search(tree);
        long start5 = System.currentTimeMillis();
        thread5.start();
        thread6.start();
        try {
            thread5.join();
            thread6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end5 = System.currentTimeMillis();
        System.out.println("Time taken to insert 1000 inputs is "+(end5-start5)+" ms");
        tree.search2(10);
        tree.heigh();
        System.out.println("------for 1000000 inputs----------");
        System.out.println("Without threads");
        tree.getSize(1000000);
        tree.setMax();
        tree.setMin();
        long start6 = System.currentTimeMillis();
        tree.insert();
        long end6 = System.currentTimeMillis();
        System.out.println("Time taken to insert 1000000 inputs is "+(end6-start6)+"ms");
        tree.search(10);
        tree.heigh();
        System.out.println("With threads");
        creation thread7 = new creation(tree,1000000);
        search thread8 = new search(tree);
        long start7 = System.currentTimeMillis();
        thread7.start();
        thread8.start();
        try {
            thread7.join();
            thread8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end7 = System.currentTimeMillis();
        System.out.println("Time taken to insert 1000000 inputs is "+(end7-start7)+" ms");
        tree.search2(10);
        tree.heigh();
    }
}
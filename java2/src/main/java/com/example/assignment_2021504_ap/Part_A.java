package com.example.assignment_2021504_ap;
import java.util.*;
class without_thread{
    private int max;
    private int min;
    private int size;
    private ArrayList<Float>Cgpa = new ArrayList<Float>();
    public void setMax(int max){
        this.max = max;
    }
    public int getMax(){
        return max;
    }
    public void setMin(int min){
        this.min = min;
    }
    public int getMin(){
        return min;
    }
    public void setsize(int size){
        this.size = size;
    }
    public int getSize(){
        return size;
    }
    public void addcgpa(){
        for (int i = 0; i < getSize(); i++){
            float random = (float) (Math.random() * (getMax() - getMin() + 1) + getMin());
            while (random > 10){
                random = (float) (Math.random() * (getMax() - getMin() + 1) + getMin());
            }
            Cgpa.add(Float.valueOf(String.format("%.3f", random)));
        }
    }
    public void Odd_Even_sort (){
        boolean isSorted = false;
        while(!isSorted){
            isSorted = true;
            for(int i=1;i<=size-2;i+=2){
                if(Cgpa.get(i)<Cgpa.get(i+1)){
                    float temp = Cgpa.get(i);
                    Cgpa.set(i,Cgpa.get(i+1));
                    Cgpa.set(i+1,temp);
                    isSorted = false;
                }
            }
            for(int i=0;i<=size-2;i+=2){
                if(Cgpa.get(i)<Cgpa.get(i+1)){
                    float temp = Cgpa.get(i);
                    Cgpa.set(i,Cgpa.get(i+1));
                    Cgpa.set(i+1,temp);
                    isSorted = false;
                }
            }
        }
    }

}
class With_thread{
    private int max;
    private int min;
    private int size;
    ArrayList<Float>Cgpa2 = new ArrayList<Float>();
    ArrayList<Float>first = new ArrayList<Float>();
    ArrayList<Float>second = new ArrayList<Float>();
    public void setMax(int max){
        this.max = max;
    }
    public int getMax(){
        return max;
    }
    public void setMin(int min){
        this.min = min;
    }
    public int getMin(){
        return min;
    }
    public void setsize(int size){
        this.size = size;
    }
    public int getSize(){
        return size;
    }
    public void addcgpa(){
        for (int i = 0; i < getSize(); i++){
            float random = (float) (Math.random() * (getMax() - getMin() + 1) + getMin());
            while (random > 10){
                random = (float) (Math.random() * (getMax() - getMin() + 1) + getMin());
            }
            Cgpa2.add(Float.valueOf(String.format("%.3f", random)));
        }
    }
    public void add_first(){
        for (int i = 0; i < getSize()/2; i++){
            first.add(Cgpa2.get(i));
        }
    }
    public void add_second(){
        for (int i = getSize()/2; i < getSize(); i++){
            second.add(Cgpa2.get(i));
        }
    }
    public void merge(){
        int i = 0, j = 0, k = 0;
        while (i < first.size() && j < second.size()) {
            if (first.get(i) > second.get(j)) {
                Cgpa2.set(k, first.get(i));
                i++;
            }
            else {
                Cgpa2.set(k, second.get(j));
                j++;
            }
            k++;
        }
        while (i < first.size()) {
            Cgpa2.set(k, first.get(i));
            i++;
            k++;
        }
        while (j < second.size()) {
            Cgpa2.set(k, second.get(j));
            j++;
            k++;
        }
    }

}
class Sort1 extends Thread implements Runnable{
    With_thread w1;
    public Sort1(With_thread w1){
        this.w1 = w1;
    }
    public void sort_first(){
        boolean isSorted = false;
        while(!isSorted){
            isSorted = true;
            for(int i=1;i<=w1.getSize()/2-2;i+=2){
                if(w1.first.get(i)>w1.first.get(i+1)){
                    float temp = w1.first.get(i);
                    w1.first.set(i,w1.first.get(i+1));
                    w1.first.set(i+1,temp);
                    isSorted = false;
                }
            }
            for(int i=0;i<=w1.getSize()/2-2;i+=2){
                if(w1.first.get(i)>w1.first.get(i+1)){
                    float temp = w1.first.get(i);
                    w1.first.set(i,w1.first.get(i+1));
                    w1.first.set(i+1,temp);
                    isSorted = false;
                }
            }
        }
    }
}
class sort_2 extends Thread implements Runnable{
    With_thread w2;
    public sort_2(With_thread w2){
        this.w2 = w2;
    }
    public void sort_second(){
        boolean isSorted = false;
        while(!isSorted){
            isSorted = true;
            for(int i=1;i<=w2.getSize()/2-2;i+=2){
                if(w2.second.get(i)>w2.second.get(i+1)){
                    float temp = w2.second.get(i);
                    w2.second.set(i,w2.second.get(i+1));
                    w2.second.set(i+1,temp);
                    isSorted = false;
                }
            }
            for(int i=0;i<=w2.getSize()/2-2;i+=2){
                if(w2.second.get(i)>w2.second.get(i+1)){
                    float temp = w2.second.get(i);
                    w2.second.set(i,w2.second.get(i+1));
                    w2.second.set(i+1,temp);
                    isSorted = false;
                }
            }
        }
    }
}
public class Part_A {
    public static void main(String[] args) {
        System.out.println("***Part A***");
        System.out.println("Without Thread( for 10) ");
        without_thread w = new without_thread();
        w.setMax(10);
        w.setMin(0);
        w.setsize(10);
        long start = System.currentTimeMillis();
        w.addcgpa();
        w.Odd_Even_sort();
        long end = System.currentTimeMillis();
        System.out.println("Time taken to sort without thread: "+(end-start)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("With Thread (for 10)");
        With_thread w1 = new With_thread();
        w1.setMax(10);
        w1.setMin(0);
        w1.setsize(10);
        long start1 = System.currentTimeMillis();
        w1.addcgpa();
        w1.add_first();
        w1.add_second();
        Sort1 s1 = new Sort1(w1);
        sort_2 s2 = new sort_2(w1);
        s1.sort_first();
        s2.sort_second();
        w1.merge();
        long end1 = System.currentTimeMillis();
        System.out.println("Time taken to sort with thread: "+(end1-start1)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("Without Thread( for 100) ");
        without_thread w2 = new without_thread();
        w2.setMax(10);
        w2.setMin(0);
        w2.setsize(100);
        long start2 = System.currentTimeMillis();
        w2.addcgpa();
        w2.Odd_Even_sort();
        long end2 = System.currentTimeMillis();
        System.out.println("Time taken to sort without thread: "+(end2-start2)+"ms");

        System.out.println("_____________________________________________________________");
        System.out.println("With thread (for 100)");
        With_thread w3 = new With_thread();
        w3.setMax(10);
        w3.setMin(0);
        w3.setsize(100);
        long start3 = System.currentTimeMillis();
        w3.addcgpa();
        w3.add_first();
        w3.add_second();
        Sort1 s3 = new Sort1(w3);
        sort_2 s4 = new sort_2(w3);
        s3.sort_first();
        s4.sort_second();
        w3.merge();
        long end3 = System.currentTimeMillis();
        System.out.println("Time taken to sort with thread: "+(end3-start3)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("With thread (for 1000)");
        With_thread w4 = new With_thread();
        w4.setMax(10);
        w4.setMin(0);
        w4.setsize(1000);
        long start4 = System.currentTimeMillis();
        w4.addcgpa();
        w4.add_first();
        w4.add_second();
        Sort1 s5 = new Sort1(w4);
        sort_2 s6 = new sort_2(w4);
        s5.sort_first();
        s6.sort_second();
        w4.merge();
        long end4 = System.currentTimeMillis();
        System.out.println("Time taken to sort with thread: "+(end4-start4)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("Without Thread( for 1000) ");
        without_thread w5 = new without_thread();
        w5.setMax(10);
        w5.setMin(0);
        w5.setsize(1000);
        long start5 = System.currentTimeMillis();
        w5.addcgpa();
        w5.Odd_Even_sort();
        long end5 = System.currentTimeMillis();
        System.out.println("Time taken to sort without thread: "+(end5-start5)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("With thread (for 10000)");
        With_thread w6 = new With_thread();
        w6.setMax(10);
        w6.setMin(0);
        w6.setsize(10000);
        long start6 = System.currentTimeMillis();
        w6.addcgpa();
        w6.add_first();
        w6.add_second();
        Sort1 s7 = new Sort1(w6);
        sort_2 s8 = new sort_2(w6);
        s7.sort_first();
        s8.sort_second();
        w6.merge();
        long end6 = System.currentTimeMillis();
        System.out.println("Time taken to sort with thread: "+(end6-start6)+"ms");
        System.out.println("_____________________________________________________________");


        System.out.println("Without Thread( for 10000) ");
        without_thread w7 = new without_thread();
        w7.setMax(10);
        w7.setMin(0);
        w7.setsize(10000);
        long start7 = System.currentTimeMillis();
        w7.addcgpa();
        w7.Odd_Even_sort();
        long end7 = System.currentTimeMillis();
        System.out.println("Time taken to sort without thread: "+(end7-start7)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("With thread (for 100000)");
        With_thread w8 = new With_thread();
        w8.setMax(10);
        w8.setMin(0);
        w8.setsize(100000);
        long start8 = System.currentTimeMillis();
        w8.addcgpa();
        w8.add_first();
        w8.add_second();
        Sort1 s9 = new Sort1(w8);
        sort_2 s10 = new sort_2(w8);
        s9.sort_first();
        s10.sort_second();
        w8.merge();
        long end8 = System.currentTimeMillis();
        System.out.println("Time taken to sort with thread: "+(end8-start8)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("Without Thread( for 100000) ");
        without_thread w9 = new without_thread();
        w9.setMax(10);
        w9.setMin(0);
        w9.setsize(100000);
        long start9 = System.currentTimeMillis();
        w9.addcgpa();
        w9.Odd_Even_sort();
        long end9 = System.currentTimeMillis();
        System.out.println("Time taken to sort without thread: "+(end9-start9)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("With thread (for 1)");
        With_thread w10 = new With_thread();
        w10.setMax(10);
        w10.setMin(0);
        w10.setsize(1);
        long start10 = System.currentTimeMillis();
        w10.addcgpa();
        w10.add_first();
        w10.add_second();
        Sort1 s11 = new Sort1(w10);
        sort_2 s12 = new sort_2(w10);
        s11.sort_first();
        s12.sort_second();
        w10.merge();
        long end10 = System.currentTimeMillis();
        System.out.println("Time taken to sort with thread: "+(end10-start10)+"ms");
        System.out.println("_____________________________________________________________");
        System.out.println("Without Thread( for 1) ");
        without_thread w11 = new without_thread();
        w11.setMax(10);
        w11.setMin(0);
        w11.setsize(1);
        long start11 = System.currentTimeMillis();
        w11.addcgpa();
        w11.Odd_Even_sort();
        long end11 = System.currentTimeMillis();
        System.out.println("Time taken to sort without thread: "+(end11-start11)+"ms");
        System.out.println("_____________________________________________________________");
    }
}
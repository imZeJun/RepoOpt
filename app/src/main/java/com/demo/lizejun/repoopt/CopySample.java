package com.demo.lizejun.repoopt;

import android.util.Log;

import java.util.ArrayList;

public class CopySample {

    public static void startRun1() {
        int lNumber = 2;
        int rNumber = lNumber;
        rNumber = 3;
        Log.d("CopySample", "lNumber=" + lNumber + ",rNumber=" + rNumber);
    }

    public static void startRun2() {
        People lPeople = new People();
        lPeople.age = 10;
        People rPeople = lPeople;
        rPeople.age = 20;
        Log.d("CopySample", "lPeople=" + lPeople + ",lPeople.age=" + lPeople.age + ",\n" +
            "rPeople=" + rPeople + ",rPeople.age=" + rPeople.age);
    }

    public static void startRun3() {
        People lPeople = new People();
        lPeople.age = 10;
        lPeople.holder = new People.Holder();
        lPeople.holder.holderValue = 10;
        People rPeople = (People) lPeople.clone();
        rPeople.age = 20;
        rPeople.holder.holderValue = 20;
        Log.d("CopySample", "lPeople=" + lPeople + ",lPeople.age=" + lPeople.age + ",lPeople.holder=" + lPeople.holder + ",\n" +
                "rPeople=" + rPeople + ",rPeople.age=" + rPeople.age + ",rPeople.holder=" + rPeople.holder);
        Log.d("CopySample", "lHolderValue=" + lPeople.holder.holderValue + ",rHolderValue=" + rPeople.holder.holderValue);
    }

    public static void methodBasic() {
        int lNum = 3;
        methodRunInner(lNum);
        Log.d("CopySample", "After methodRunInner, lNum=" + lNum);
    }

    private static void methodRunInner(int lNum) {
        lNum++;
        Log.d("CopySample", "methodRunInner, lNum=" + lNum);
    }

    public static void methodRef() {
        NumHolder holder = new NumHolder();
        holder.num = 3;
        methodRunInner(holder);
        Log.d("CopySample", "After methodRunInner, holder.num=" + holder.num);
    }

    private static void methodRunInner(NumHolder holder) {
        holder.num++;
        Log.d("CopySample", "methodRunInner, holder.num=" + holder.num);
    }

    public static void methodSwapRef() {
        NumHolder lHolder = new NumHolder();
        NumHolder rHolder = new NumHolder();
        lHolder.num = 3;
        rHolder.num = 4;
        methodRunInner(lHolder, rHolder);
        Log.d("CopySample", "methodSwapRef, lHolder.num=" + lHolder.num  + ", rHolder.num=" + rHolder.num);
    }

    private static void methodRunInner(NumHolder lHolder, NumHolder rHolder) {
        NumHolder temp = lHolder;
        lHolder = rHolder;
        rHolder = temp;
        Log.d("CopySample", "methodRunInner, lHolder.num=" + lHolder.num  + ", rHolder.num=" + rHolder.num);
    }

    private static class NumHolder {
        int num;
    }

    public static void listShallowRun() {
        ArrayList<People> lPeoples = new ArrayList<>();
        People people1 = new People();
        lPeoples.add(people1);
        Log.d("CopySample", "listShallowRun, lPeoples[0]=" + lPeoples.get(0));
        ArrayList<People> rPeoples = (ArrayList<People>) lPeoples.clone();
        Log.d("CopySample", "listShallowRun, rPeoples[0]=" + rPeoples.get(0));
    }

    public static void listDeepRun() {
        ArrayList<People> lPeoples = new ArrayList<>();
        People people1 = new People();
        people1.holder = new People.Holder();
        lPeoples.add(people1);
        Log.d("CopySample", "listShallowRun, lPeoples[0]=" + lPeoples.get(0));
        ArrayList<People> rPeoples = new ArrayList<>();
        for (People people : lPeoples) {
            rPeoples.add((People) people.clone());
        }
        Log.d("CopySample", "listShallowRun, rPeoples[0]=" + rPeoples.get(0));
    }
    
    public static class People implements Cloneable {

        int age;
        Holder holder;

        @Override
        protected Object clone() {
            try {
                People people = (People) super.clone();
                people.holder = (People.Holder) this.holder.clone();
                return people;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static class Holder implements Cloneable {

            int holderValue;

            @Override
            protected Object clone() {
                try {
                    return super.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }
}

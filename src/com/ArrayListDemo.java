package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        int nStartIdx = 11;
//        int j = 15;
//        ArrayList<Integer> arrList = new ArrayList<Integer>();
//        for (int m = nStartIdx; m < j; m++) {
//            arrList.add(m);
//        }
//        Integer[] cells =(Integer[]) arrList.toArray(new Integer[0]);
//        System.out.println(Arrays.toString(cells));
        
        int nStartRow = 11;
        int nCurRow = 15;
        int[] cellVec = new int[nCurRow-nStartRow];  
        for (int m = nStartRow; m < nCurRow; m++) {
            cellVec[m - nStartRow] = m; 
        }
        System.out.println(Arrays.toString(cellVec));
        
        
        
//        long [] temp = new long[arrList.size()];
//        long [] l = (long[]) arrList.toArray(new long[0]);
//        long [] l = (long[]) arrList.toArray(temp);
        
//        Integer [] array =new Integer[arrList.size()];
//        arrList.toArray(array);
        
//        List list =new ArrayList();
//        list.add(new Long(1));
//        list.add(new Long(2));
//        list.add(new Long(3));
//        list.add(new Long(4));
////        Long[] l =(Long[]) list.toArray();
//     Long[] l =(Long[]) list.toArray(new Long[0]);
// //   这句有返回类型的就不会报错了。
//        for (int i =0; i < l.length; i++)
//          System.out.println(l[i].longValue());
//     }
        
//        List list =new ArrayList();
//        list.add(new Integer(1));
//        list.add(new Integer(2));
//        list.add(new Integer(3));
//        list.add(new Integer(4));
////        Long[] l =(Long[]) list.toArray();
//        Integer[] ll =(Integer[]) list.toArray(new Integer[0]);
// //   这句有返回类型的就不会报错了。
//        for (int i =0; i < ll.length; i++)
//          System.out.println(l[i].longValue());
    }
}

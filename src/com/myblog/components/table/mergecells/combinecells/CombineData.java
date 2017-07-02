package com.myblog.components.table.mergecells.combinecells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
public class CombineData {
 
    public ArrayList<Integer> combineColumns = new ArrayList<Integer>();//用于保存需要合并的列号
    private String[][] datas;//table的数据，用来计算合并的单元格
    private ArrayList<HashMap<Integer, Integer>> rowPoss;
    private ArrayList<HashMap<Integer, Integer>> rowCounts;
 
    public CombineData() {
    }
 
    public CombineData(String[][] datas, int... combineColumns) {
        this.datas = datas;
        for (int i = 0; i < combineColumns.length; i++) {
            if (combineColumns[i] < 0) {
                continue;
            }
            this.combineColumns.add(combineColumns[i]);
        }
 
        process();
    }
 
    public CombineData(String[][] datas, List<Integer> combineColumns) {
        this.datas = datas;
        for (Integer column : combineColumns) {
            if (column < 0) {
                continue;
            }
            this.combineColumns.add(column);
        }
 
        process();
    }
 
    public void initData(String[][] datas, int... combineColumns) {
        this.datas = datas;
        this.combineColumns.clear();
        for (int i = 0; i < combineColumns.length; i++) {
            if (combineColumns[i] < 0) {
                continue;
            }
            this.combineColumns.add(combineColumns[i]);
        }
 
        process();
    }
 
    public void initData(String[][] datas, List<Integer> combineColumns) {
        this.datas = datas;
        this.combineColumns.clear();
        for (Integer column : combineColumns) {
            if (column < 0) {
                continue;
            }
            this.combineColumns.add(column);
        }
 
        process();
    }
 
    private void process() {
        rowPoss = new ArrayList<HashMap<Integer, Integer>>();
        rowCounts = new ArrayList<HashMap<Integer, Integer>>();
 
        for (Integer integer : combineColumns) {
            HashMap<Integer, Integer> rowPos = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> rowCount = new HashMap<Integer, Integer>();
 
            String pre = "";
            int count = 0;
            int start = 0;
            for (int i = 0; i < datas.length; i++) {
                String[] data = datas[i];
                if (pre.equals(data[integer])) {
                    count++;
                } else {
                    rowCount.put(start, count);
                    pre = data[integer];
                    count = 1;
                    start = i;
                }
                rowPos.put(i, start);
            }
            rowCount.put(start, count);
 
            rowPoss.add(rowPos);
            rowCounts.add(rowCount);
        }
    }
 
    /**
     * 返回table中row行column列单元格所跨行数
     */
    public int span(int row, int column) {
        int index = combineColumns.lastIndexOf(column);
        if (index != -1) {
            return rowCounts.get(index).get(rowPoss.get(index).get(row));
        } else {
            return 1;
        }
    }
 
    /**
     * 返回table中row行column列单元格所在的合并单元格的起始行位置
     */
    public int visibleCell(int row, int column) {
        int index = combineColumns.lastIndexOf(column);
        if ((index != -1) && row > -1) {
            return rowPoss.get(index).get(row);
        } else {
            return row;
        }
    }
}
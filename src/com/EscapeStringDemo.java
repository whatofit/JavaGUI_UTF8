package com;
//转义字符串(Escape String),
public class EscapeStringDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * \n : 回车
         * \t : 水平制表符
         * \r : 换行
         * \f : 换页
         * \' ：单引号
         * \'' : 双引号
         * \\ ： 反斜杠
         * 字符串
         */
        //String str = "tg\nhj\thuy\riu7\f878\'76$98\''9kjs\\udjiddjkfhdsuifkhjdkhuwirohlfkf";
        String str = "&#39;";
        /**
         * 打印字符串
         */
        System.out.println("字符串：" + str);
    }

}

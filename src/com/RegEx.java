package com;


public class RegEx {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String regex = "[；;]";
        //String meanings = "采用（某种方式）；穿着，带着；（表示位置）在…里面，（表示领域，范围）在…以内；（表示品质、能力等）在…之中；";
        String meanings = "采用（某种方式）；穿着，带着；（表示位置）在…里面;（表示领域，范围）在…以内；（表示品质、能力等）在…之中；";
        String []acceptation = meanings.split(regex);
        for (int i=0; i < acceptation.length; i++) {
            //System.out.println("meanings,regex="+ Arrays.toString(acceptation));
            System.out.println("meanings,regex="+ acceptation[i]);
        }
    }
}

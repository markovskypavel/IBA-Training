package by.iba.markovsky.festivalorganisation.infrastructure.classwork;

import java.util.Date;

public class StringTest {

    private static final int OPERATIONS = 1000000;

    public static void main(String[] args) {
        Date dateIn = new Date();
        StringBuilder stringBuilder = new StringBuilder("Hello ");
        for (int i = 0; i < OPERATIONS; i++) {
            stringBuilder.append("world!");
        }
        Date dateOut = new Date();
        long time = dateOut.getTime() - dateIn.getTime();
        System.out.println(time);
    }

}

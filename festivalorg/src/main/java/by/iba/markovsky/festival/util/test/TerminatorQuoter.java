package by.iba.markovsky.festival.util.test;

import javax.annotation.PostConstruct;

@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    public TerminatorQuoter() {
        System.out.println("phase 1");
    }

    @PostConstruct
    public void init(){
        System.out.println("phase 2");
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @PostProxy
    @Override
    public void sayQuote() {
        System.out.println("phase 3");
        for (int i = 0; i < repeat; i++) {
            System.out.println(message);
        }
    }

}

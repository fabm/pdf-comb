package pt.fabm;

import java.util.Iterator;

public class Bet implements Iterator<int[]>, Iterable<int[]> {

    private int[] content;
    private final int max;
    private final int betNumber;

    public Bet(int max, int betNumber) {
        this.max = max;
        this.betNumber = betNumber;
        content = new int[this.betNumber];
        for (int i = 0; i < this.betNumber - 1; i++) {
            content[i] = i + 1;
        }
        content[this.betNumber - 1] = this.betNumber - 1;
    }

    private int maxAllowed(int index){
        int maxIndex = betNumber - 1;
        return max - (maxIndex - index);
    }

    @Override
    public boolean hasNext() {
        int maxIndex = betNumber - 1;
        for (int i = maxIndex; i > 0; i--) {
            if (content[i] < max - (maxIndex - i)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int[] next() {
        int maxIndex = betNumber - 1;
        for (int i = maxIndex; i > 0; i--) {
            if (content[i] < max - (maxIndex - i)) {
                content[i]++;
                return content;
            }else{

                for (int j = i; j < betNumber; j++) {
                    content[j] = content[j-1]+2;
                }
            }
        }

        return content;
    }

    private boolean incrementBeforeLast(){
        int maxIndex = betNumber - 1;
        for (int i = maxIndex-1; i > 0; i--) {
            if(content[i] < maxAllowed(i)){
                incrementRight(content[i]);
                return true;
            }
        }
        return false;
    }

    private void incrementRight(int index){
        int maxIndex = betNumber - 1;
        for (int i = index; i < maxIndex; i++) {
        }
    }

    @Override
    public Iterator<int[]> iterator() {
        return this;
    }
}

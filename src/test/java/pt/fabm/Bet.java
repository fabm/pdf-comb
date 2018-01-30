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

    private int maxAllowed(int index) {
        int maxIndex = betNumber - 1;
        return max - (maxIndex - index);
    }

    @Override
    public boolean hasNext() {
        int maxIndex = betNumber - 1;
        for (int i = maxIndex; i > -1; i--) {
            if (content[i] < max - (maxIndex - i)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int[] next() {
        int maxIndex = betNumber - 1;
        if (content[maxIndex] < max) {
            content[maxIndex]++;
            return content;
        } else {
            if(!increment(maxIndex-1)){
                throw new IllegalStateException("wot");
            }
        }
        return content;
    }

    private boolean increment(int index) {
        int maxIndex = betNumber - 1;
        if (content[index] < maxAllowed(index)) {
            content[index]++;
            for (int i = index+1; i < betNumber; i++) {
                content[i] = content[i-1]+1;
            }
            return true;
        }
        if(index == 0){
            return false;
        }
        return increment(index-1);
    }

    private void incrementRight(int index) {
        int maxIndex = betNumber - 1;
        for (int i = index; i < maxIndex; i++) {

        }
    }

    @Override
    public Iterator<int[]> iterator() {
        return this;
    }
}

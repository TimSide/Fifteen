import javax.swing.*;

class Num {
    private int[] a = new int[16];
    final private int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
    private boolean replace = false;

    int currX;
    int currY;
    int lastX;
    int lastY;

    //    private boolean gameOver = false;
    private static int count = 0;
    private int record;


    // The generator of random numbers that does not repeat
    void newGame() {
//		for(int j=0;j<15;j++) a[j]=j+1;
        countReset();
        boolean badRand;
        int randOne = (int) (Math.random() * 15 + 1);

        a[0] = randOne;
        for (int i = 0; i < 16; i++) {

            badRand = true;

            while (badRand) {
                badRand = false;

                randOne = (int) (Math.random() * 16);
                for (int j = 0; j < i; j++) {
                    if (randOne == a[j]) {
                        badRand = true;
                        break;
                    }
                }
                if (!badRand) a[i] = randOne;
            }

        }
//		a[15] = 0;
    }

    // Move counter - zeroing
    private void countReset() {
        count = 0;
    }

    // Move counter - increment
    private void countUp() {
        count += 1;
    }

    // Move counter - decrement
    private void countDown() {
        count -= 1;
    }

    // Move counter - get number of moves
    int getCount() {
        return count;
    }

    // Get the value of element of array  A
    int getNum(int k) {
        if ((k < 0) || (k > 15))
            return -1;
        else
            return a[k];
    }

    // Get winning element of array B
    int getWinElement(int k) {
        if ((k < 0) || (k > 15))
            return -1;
        else
            return b[k];
    }

    // Swap elements forward
    void swapForward(int i, int j) {
        a[j] = a[i];
        a[i] = 0;
        countUp();
    }

    // Swap elements back
    void swapBack(int j, int i) {
        a[j] = a[i];
        a[i] = 0;
        countDown();
    }

    // Set not replaced
    void setNotReplaced() {
        this.replace = false;
    }

    // Indicator of permutation
    void setNotReplaced(int i, int j, int a, int b) {
        this.replace = true;
        currX = i;
        currY = j;
        lastX = a;
        lastY = b;
    }

    // Indicator of permutation
    boolean getReplace() {
        return replace;
    }

    // Checking the end of the game
    boolean gameOver() {
        boolean gameOver = true;
        for (int i = 0; i < 12; i++) {
            if (a[i] != b[i]) {
                gameOver = false;
                break;
            }
            gameOver = true;
        }
        return gameOver;
    }

    // File operations

    void openFile() {
        setRecord(FileHandler.openFile());
    }

    void writeFile(int a) {
        FileHandler.writeFile(a);
    }

    //=== Setters and Getters ===


    // Set record
    void setRecord(int a) {
        record = a;
    }

    // Get record
    int getRecord() {
        return record;
    }

    // Rules of game
    void showAbout() {
        JOptionPane.showMessageDialog(null,
                "The application was developed by \nTim Kirkicha (TimSide)\n\n" +
                        "https://github.com/TimSide");
        // Show message
    }
}
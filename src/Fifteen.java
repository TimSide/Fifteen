import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fifteen {
    public static void main(String[] args) {

//------------Main Form---------------------------------------------------------

        // Created new form
        final JFrame frame = new JFrame("Game - ''Fifteen v1''");
        frame.setSize(430, 330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(null);

        // Fonts
        Font font1 = new Font("Verdana", Font.BOLD, 14);
        Font font2 = new Font("Verdana", Font.PLAIN, 15);
        Font font3 = new Font("Verdana", Font.PLAIN, 45);
        Font font4 = new Font("Verdana", Font.BOLD, 20);

        // Button "New game"
        JButton btnNewGame = new JButton("New game");
        frame.add(btnNewGame);
        btnNewGame.setBackground(Color.LIGHT_GRAY);
        btnNewGame.setForeground(Color.darkGray);
        btnNewGame.setFont(font2);
        btnNewGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNewGame.setBounds(140, 15, 150, 30);

        // Label "Number of moves:"
        JLabel label1 = new JLabel("Number of moves:");
        frame.add(label1);
        label1.setBounds(10, 25, 100, 100);

        // Label "Record:"
        JLabel label2 = new JLabel("Record:");
        frame.add(label2);
        label2.setBounds(350, 25, 100, 100);

        // Panel "Number of moves"
        final JLabel count = new JLabel(" ");
        frame.add(count);
        count.setFont(font3);
        count.setVerticalAlignment(JLabel.CENTER);
        count.setHorizontalAlignment(JLabel.CENTER);
        count.setBounds(0, 80, 100, 50);
        count.setForeground(Color.gray);

        // Panel "Record"
        final JLabel PanelRecord = new JLabel("0");
        frame.add(PanelRecord);
        PanelRecord.setFont(font3);
        PanelRecord.setVerticalAlignment(JLabel.CENTER);
        PanelRecord.setHorizontalAlignment(JLabel.CENTER);
        PanelRecord.setBounds(320, 60, 100, 100);
        PanelRecord.setForeground(Color.gray);

        // Button "Revert"
        final JButton btnRevert = new JButton("Revert");
        frame.add(btnRevert);
        btnRevert.setBounds(10, 130, 86, 40);
        btnRevert.setBackground(Color.lightGray);
        btnRevert.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRevert.setEnabled(false);

        // Button "About"
        final JButton btnAbout = new JButton("About");
        frame.add(btnAbout);
        btnAbout.setBounds(10, 180, 86, 40);
        btnAbout.setBackground(Color.lightGray);
        btnAbout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Button "Reset"
        final JButton btnResetRecord = new JButton("Reset");
        frame.add(btnResetRecord);
        btnResetRecord.setBounds(335, 180, 80, 40);
        btnResetRecord.setBackground(Color.lightGray);
        btnResetRecord.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Panel of win
        final JLabel win = new JLabel("You win! =)");
        frame.add(win);
        win.setFont(font4);
        win.setForeground(Color.darkGray);
        win.setVerticalAlignment(JLabel.CENTER);
        win.setHorizontalAlignment(JLabel.CENTER);
        win.setBounds(105, 60, 220, 220);
        win.setVisible(false);

        // Adding game-buttons to panel
        final JButton[][] buttons = new JButton[4][4]; //Array of buttons

        int tmp1 = 105, tmp2 = 60;
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                buttons[i][j] = new JButton(); //Creating of buttons
                frame.add(buttons[i][j]);
                buttons[i][j].setEnabled(false);
                buttons[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
                buttons[i][j].setForeground(Color.darkGray);
                buttons[i][j].setFont(font1);
                buttons[i][j].setBackground(Color.LIGHT_GRAY);
                buttons[i][j].setBounds(tmp1, tmp2, 55, 55);
                tmp1 += 55;
            }
            tmp1 = 105;
            tmp2 += 55;
        }

//====================================================================================================

        final Num game = new Num();

        game.openFile(); // Opening file and refresh the record
        PanelRecord.setText(Integer.toString(game.getRecord()));

        // Button-click "New Game"
        btnNewGame.addActionListener(e -> {
            win.setVisible(false);
            btnRevert.setEnabled(false);
            game.openFile();
            PanelRecord.setText(Integer.toString(game.getRecord()));
            game.newGame();
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    buttons[i][j].setEnabled(true);
                    buttons[i][j].setText(Integer.toString(game.getNum(i * 4 + j)));
                    if (game.getWinElement(i * 4 + j) == Integer.parseInt(buttons[i][j].getText()))
                        buttons[i][j].setBackground(Color.gray);
                    else
                        buttons[i][j].setBackground(Color.lightGray);
                    if (game.getNum(i * 4 + j) == 0) {
                        buttons[i][j].setText(null);
                        buttons[i][j].setBackground(Color.white);
                    }
                }
            }
            count.setText(Integer.toString(game.getCount()));
        });

        class FifteenButtonListener implements ActionListener {
            private int i, j;

            private FifteenButtonListener(int i, int j) {
                this.i = i;
                this.j = j;
            }

            //Processing of the event
            public void actionPerformed(ActionEvent e) {

                game.setNotReplaced();
                int a = 0, b = 0;
                if (game.getNum((i - 1) * 4 + j) == 0) { // Move up
                    a = i - 1;
                    b = j;
                    game.setNotReplaced(i, j, a, b);
                }
                if (game.getNum((i + 1) * 4 + j) == 0) { // Move down
                    a = i + 1;
                    b = j;
                    game.setNotReplaced(i, j, a, b);
                }
                if (game.getNum(i * 4 + j - 1) == 0) {    // Move right
                    a = i;
                    b = j - 1;
                    game.setNotReplaced(i, j, a, b);
                }
                if (game.getNum(i * 4 + j + 1) == 0) {    // Move left
                    a = i;
                    b = j + 1;
                    game.setNotReplaced(i, j, a, b);
                }

                if (game.getReplace()) {    // Moving
                    game.swapForward(i * 4 + j, a * 4 + b);
                    buttons[a][b].setText(buttons[i][j].getText());

                    if (game.getWinElement(a * 4 + b) == Integer.parseInt(buttons[a][b].getText()))
                        buttons[a][b].setBackground(Color.gray);
                    else
                        buttons[a][b].setBackground(Color.lightGray);

                    buttons[i][j].setBackground(Color.white);
                    buttons[i][j].setText(null);

                    btnRevert.setEnabled(true);
                }

                if (game.gameOver()) {
                    for (int i = 0; i <= 3; i++)
                        for (int j = 0; j <= 3; j++) {
                            buttons[i][j].setEnabled(false);
                            buttons[i][j].setBackground(Color.gray);
                        }
                    win.setVisible(true);
                    btnRevert.setEnabled(false);
                    if (game.getCount() < game.getRecord()) game.writeFile(game.getCount());
                }
                count.setText(Integer.toString(game.getCount()));
            }
        }

        // Button "Revert" - Revert last change
        btnRevert.addActionListener(e -> {
            int i = game.currX;
            int j = game.currY;
            int a = game.lastX;
            int b = game.lastY;

            game.swapBack(i * 4 + j, a * 4 + b);
            buttons[i][j].setText(buttons[a][b].getText());

            if (game.getWinElement(i * 4 + j) == Integer.parseInt(buttons[i][j].getText()))
                buttons[i][j].setBackground(Color.gray);
            else
                buttons[i][j].setBackground(Color.lightGray);
            buttons[a][b].setBackground(Color.white);
            buttons[a][b].setText(null);

            count.setText(Integer.toString(game.getCount()));
            btnRevert.setEnabled(false);

            game.currX = a;
            game.currY = b;
            game.lastX = i;
            game.lastY = j;
        });

        // Press on game-buttons
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                buttons[i][j].addActionListener(new FifteenButtonListener(i, j));
            }
        }

        btnAbout.addActionListener(e -> game.showAbout());

        btnResetRecord.addActionListener(e -> {
            game.setRecord(300);
            game.writeFile(300);
            game.openFile();
            PanelRecord.setText(Integer.toString(game.getRecord()));
        });
    }
}

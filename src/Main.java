import javax.swing.*;
public class Main {
    public static void main(String[] args) {

        int rawCount = 21;
        int colCount = 19;
        int tileSize = 32;
        int bordarWidth = colCount * tileSize;
        int borderHeigth = rawCount * tileSize;

        JFrame frame = new JFrame();
        frame.setSize(bordarWidth,borderHeigth);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        PacMan pac = new PacMan();
        frame.add(pac);
        frame.pack();
        pac.requestFocus();
        frame.setVisible(true);

        }
    }

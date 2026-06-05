import javax.swing.*;
import java.awt.*;

public class GameImage {

    private Image wallImage;

    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;

    private Image pacmanDownImage;
    private Image pacmanUpImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    public GameImage() {

        wallImage = new ImageIcon(
                getClass().getResource("./images/wall.png")
        ).getImage();

        blueGhostImage = new ImageIcon(
                getClass().getResource("./images/blueGhost.png")
        ).getImage();

        orangeGhostImage = new ImageIcon(
                getClass().getResource("./images/orangeGhost.png")
        ).getImage();

        pinkGhostImage = new ImageIcon(
                getClass().getResource("./images/pinkGhost.png")
        ).getImage();

        redGhostImage = new ImageIcon(
                getClass().getResource("./images/redGhost.png")
        ).getImage();

        pacmanDownImage = new ImageIcon(
                getClass().getResource("./images/pacmanDown.png")
        ).getImage();

        pacmanUpImage = new ImageIcon(
                getClass().getResource("./images/pacmanUp.png")
        ).getImage();

        pacmanLeftImage = new ImageIcon(
                getClass().getResource("./images/pacmanLeft.png")
        ).getImage();

        pacmanRightImage = new ImageIcon(
                getClass().getResource("./images/pacmanRight.png")
        ).getImage();
    }


    public Image getWallImage() {
        return wallImage;
    }

    public Image getBlueGhostImage() {
        return blueGhostImage;
    }

    public Image getOrangeGhostImage() {
        return orangeGhostImage;
    }

    public Image getPinkGhostImage() {
        return pinkGhostImage;
    }

    public Image getRedGhostImage() {
        return redGhostImage;
    }

    public Image getPacmanDownImage() {
        return pacmanDownImage;
    }

    public Image getPacmanUpImage() {
        return pacmanUpImage;
    }

    public Image getPacmanLeftImage() {
        return pacmanLeftImage;
    }

    public Image getPacmanRightImage() {
        return pacmanRightImage;
    }
}
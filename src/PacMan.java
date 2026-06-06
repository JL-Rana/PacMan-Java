import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;
public class PacMan extends JPanel implements ActionListener, KeyListener{

    int rawCount = 21;
    int colCount = 19;
    int tileSize = 32;
    int bordarWidth = colCount * tileSize;
    int borderHeigth = rawCount * tileSize;

    Image wallImage;
    Image blueGhostImage;
    Image orangeGhostImage;
    Image pinkGhostImage;
    Image redGhostImage;

    Image pacmanDownImage;
    Image pacmanUpImage;
    Image pacmanLeftImage;
    Image pacmanRightImage;


    HashSet<GameBlock>walls;
    HashSet<GameBlock>foods;
    HashSet<GameBlock>ghosts;
    GameBlock pacman;

    Timer gameloop;
    char[] directions = {'U', 'D', 'L', 'R'};
    Random random = new Random();

    int score = 0;
    int lives = 3;
    boolean gameOver = false;


    PacMan(){
        setPreferredSize(new Dimension(bordarWidth,borderHeigth));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        GameImage images = new GameImage();

        wallImage = images.getWallImage();

        blueGhostImage = images.getBlueGhostImage();
        orangeGhostImage = images.getOrangeGhostImage();
        pinkGhostImage = images.getPinkGhostImage();
        redGhostImage = images.getRedGhostImage();

        pacmanDownImage = images.getPacmanDownImage();
        pacmanUpImage = images.getPacmanUpImage();
        pacmanLeftImage = images.getPacmanLeftImage();
        pacmanRightImage = images.getPacmanRightImage();

        loadMap();
        for (GameBlock ghost : ghosts) {
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection);
        }
        gameloop = new Timer(50,this);
        gameloop.start();

        //System.out.println(walls.size());
    }


    public void loadMap() {
        walls = new HashSet<GameBlock>();
        foods = new HashSet<GameBlock>();
        ghosts = new HashSet<GameBlock>();

        for (int r = 0; r < rawCount; r++) {
            for (int c = 0; c < colCount; c++) {
                TileMap tileMap = new TileMap();

                String row = tileMap.map[r];
                char tileMapChar = row.charAt(c);

                int x = c*tileSize;
                int y = r*tileSize;

                if (tileMapChar == 'X') { //block wall
                    GameBlock wall = new GameBlock(wallImage, x, y, tileSize, tileSize);
                    walls.add(wall);
                }
                else if (tileMapChar == 'b') { //blue ghost
                    GameBlock ghost = new GameBlock(blueGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == 'o') { //orange ghost
                    GameBlock ghost = new GameBlock(orangeGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == 'p') { //pink ghost
                    GameBlock ghost = new GameBlock(pinkGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == 'r') { //red ghost
                    GameBlock ghost = new GameBlock(redGhostImage, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == 'P') { //pacman
                    pacman = new GameBlock(pacmanRightImage, x, y, tileSize, tileSize);
                }
                else if (tileMapChar == ' ') { //food
                    GameBlock food = new GameBlock(null, x + 14, y + 14, 4, 4);
                    foods.add(food);
                }
            }
        }
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(pacman.image, pacman.x, pacman.y, pacman.width, pacman.height, null);

        for (GameBlock ghost : ghosts) {
            g.drawImage(ghost.image, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }

        for (GameBlock wall : walls) {
            g.drawImage(wall.image, wall.x, wall.y, wall.width, wall.height, null);
        }

        g.setColor(Color.WHITE);
        for (GameBlock food : foods) {
            g.fillRect(food.x, food.y, food.width, food.height);
        }

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf(score), tileSize/2, tileSize/2);
        }
        else {
            g.drawString("x" + String.valueOf(lives) + " Score: " + String.valueOf(score), tileSize/2, tileSize/2);
        }
    }


    public void move() {
        pacman.x += pacman.velocityX;
        pacman.y += pacman.velocityY;

        //check wall collisions
        for (GameBlock wall : walls) {
            if (GameBlock.collision(pacman, wall)) {
                pacman.x -= pacman.velocityX;
                pacman.y -= pacman.velocityY;
                break;
            }
        }

        //check ghost collisions
        for (GameBlock ghost : ghosts) {
            if (GameBlock.collision(ghost, pacman)) {
                lives -= 1;
                if (lives == 0) {
                    gameOver = true;
                    return;
                }
                resetPositions();
            }

            ghost.x += ghost.velocityX;
            ghost.y += ghost.velocityY;
            for (GameBlock wall : walls) {
                if (GameBlock.collision(ghost, wall) || ghost.x <= 0 || ghost.x + ghost.width >= bordarWidth) {
                    ghost.x -= ghost.velocityX;
                    ghost.y -= ghost.velocityY;
                    char newDirection = directions[random.nextInt(4)];
                    ghost.updateDirection(newDirection);
                }
           }
        }

//        //check food collision
        GameBlock foodEaten = null;
        for (GameBlock food : foods) {
            if (GameBlock.collision(pacman, food)) {
                foodEaten = food;
                score += 10;
            }
        }
        foods.remove(foodEaten);
        if (foods.isEmpty()) {
            loadMap();
            resetPositions();
        }
    }


    public void resetPositions() {
        pacman.reset();
        pacman.velocityX = 0;
        pacman.velocityY = 0;
        for (GameBlock ghost : ghosts) {
            ghost.reset();
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        move();
        repaint();
        if(gameOver){
            gameloop.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {

        if (gameOver) {
            loadMap();
            resetPositions();
            lives = 3;
            score = 0;
            gameOver = false;
            gameloop.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pacman.updateDirection('U');
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacman.updateDirection('D');
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pacman.updateDirection('L');
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pacman.updateDirection('R');
        }


        if (pacman.direction == 'U') {
            pacman.image = pacmanUpImage;
        }
        else if (pacman.direction == 'D') {
            pacman.image = pacmanDownImage;
        }
        else if (pacman.direction == 'L') {
            pacman.image = pacmanLeftImage;
        }
        else if (pacman.direction == 'R') {
            pacman.image = pacmanRightImage;
        }
    }
}

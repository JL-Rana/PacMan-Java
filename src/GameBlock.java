import java.awt.*;

public class GameBlock {
        int x;
        int y;
        int width;
        int height;
        Image image;

        int startX;
        int startY;
        char direction = 'U'; // U D L R
        int velocityX = 0;
        int velocityY = 0;

        GameBlock(Image image, int x, int y, int width, int height) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.startX = x;
            this.startY = y;
        }

    void updateDirection(char direction) {
        char prevDirection = this.direction;
        this.direction = direction;
        updateVelocity();

    }

    void updateVelocity() {
        if (this.direction == 'U') {
            this.velocityX = 0;
            this.velocityY = -8;
        }
        else if (this.direction == 'D') {
            this.velocityX = 0;
            this.velocityY = 8;
        }
        else if (this.direction == 'L') {
            this.velocityX = -8;
            this.velocityY = 0;
        }
        else if (this.direction == 'R') {
            this.velocityX = 8;
            this.velocityY = 0;
        }
    }

    void reset(){
            this.x = this.startX;
            this.y = this.startY;
    }

    public static boolean collision(GameBlock a, GameBlock b) {
        return  a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }
}

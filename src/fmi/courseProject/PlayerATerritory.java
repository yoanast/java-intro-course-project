package CourseProject;

import java.awt.*;

public class PlayerATerritory extends Tile{

    /**
     * Конструктор за полетата.
    */
    public PlayerATerritory(int row, int col) {
        super(row, col);
        this.id = "pA";
    }

    @Override
    public void drawTiles(Graphics g) {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;

        drawFirstTiles(g, tileX, tileY);

        if (this.row == 0 && this.col % 2 == 0 || this.row == 1 && this.col % 2 == 1) {
            g.setColor(nGray);
            g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
            drawBorders(g, tileX, tileY);
        } else if(this.row == 0 && this.col % 2 == 1 || this.row == 1 && this.col % 2 == 0) {
            g.setColor(nBlack);
            g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
            drawBorders(g, tileX, tileY);
        }
    }

    public void drawFirstTiles(Graphics g, int tileX, int tileY) {
        if(this.row == 0 && this.col == 0) {
            g.setColor(nGray);
            g.fillRect(tileX,tileY,TILE_SIZE, TILE_SIZE);
            drawBorders(g,tileX,tileY);
        } else if (this.row == 1 && this.col == 0) {
            g.setColor(nBlack);
            g.fillRect(tileX,tileY,TILE_SIZE, TILE_SIZE);
            drawBorders(g,tileX,tileY);
        }
    }
}

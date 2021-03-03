package CourseProject;

import java.awt.*;

public class PlayerBTerritory extends Tile {

    /**
     * Конструктор за полетата.
     */
    public PlayerBTerritory(int row, int col) {
        super(row, col);
        this.id = "pB";
    }

    @Override
    public void drawTiles(Graphics g) {

        int tileX = this.col * this.TILE_SIZE;
        int tileY = this.row * this.TILE_SIZE;

        drawFirstTiles(g, tileX, tileY);

        if (this.row == 6 && this.col % 2 == 0 || this.row == 5 && this.col % 2 == 1) {
            g.setColor(nGray);
            g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
            drawBorders(g, tileX, tileY);
        } else if(this.row == 6 && this.col % 2 == 1 || this.row == 5 && this.col % 2 == 0) {
            g.setColor(nBlack);
            g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
            drawBorders(g, tileX, tileY);
        }
    }

    public void drawFirstTiles(Graphics g, int tileX, int tileY) {
        if(this.row == 6 && this.col == 0) {
            g.setColor(nGray);
            g.fillRect(tileX,tileY,TILE_SIZE, TILE_SIZE);
            drawBorders(g,tileX,tileY);
        } else if (this.row == 5 && this.col == 0) {
            g.setColor(nBlack);
            g.fillRect(tileX,tileY,TILE_SIZE, TILE_SIZE);
            drawBorders(g,tileX,tileY);
        }
    }
}

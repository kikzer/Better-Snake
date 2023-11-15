package GameField;

import GameField.Food.AFood;
import GameField.Food.Apple;
import Management.Interface.GameWindow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Arrays;


public class GameField {
    public static final int SIZE_BLOCK = 25;
    private final int row = GameWindow.WIDTH /getSizeBlock();

    private final Canvas canvas;

    private AFood food = new Apple(((int)(Math.random()*10))*SIZE_BLOCK,((int)(Math.random()*10))*SIZE_BLOCK);
    public GameField(final Canvas canvas){
        this.canvas = canvas;
    }

    public void createGameField(GraphicsContext graphicsContext){
        for (int i = 0; i < getRow(); i++) {
            for (int j = 0; j < getRow(); j++) {
                if((i+j) % 2 == 0){
                    graphicsContext.setFill(Color.GREEN);
                }else{
                    graphicsContext.setFill(Color.DARKGREEN);
                }
                graphicsContext.fillRect(i*getSizeBlock(), j*getSizeBlock(), getSizeBlock(),getSizeBlock());
            }
        }
    }

    public int getSizeBlock() {
        return SIZE_BLOCK;
    }

    public int getRow() {
        return row;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public AFood getFood() {
        return food;
    }

    public void setFood(AFood food) {
        this.food = food;
    }

    public void createdRandomFood(){
        setFood(new Apple(((int)(Math.random()*10))*SIZE_BLOCK,((int)(Math.random()*10))*SIZE_BLOCK));
    }

}

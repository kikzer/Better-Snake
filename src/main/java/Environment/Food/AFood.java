package Environment.Food;

import Environment.IObject;
import Environment.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AFood implements IObject {
    private Image appearance;
    private Position position;

    public AFood(Position position){
        this.position = position;
    }

    public Image getAppearance() {
        return appearance;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setAppearance(Image appearance) {
        this.appearance = appearance;
    }

    @Override
    public void show(GraphicsContext graphicsContext){
        graphicsContext.drawImage(appearance, position.getX(),position.getY());
    }
}

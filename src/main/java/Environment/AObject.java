package Environment;

import Environment.IObject;
import Environment.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AObject implements IObject {
    private Image appearance;
    private Position position;

    private boolean blocked = false;

    public AObject(Position position){
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
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public void show(GraphicsContext graphicsContext){
        graphicsContext.drawImage(appearance, position.getX(),position.getY());
    }
}

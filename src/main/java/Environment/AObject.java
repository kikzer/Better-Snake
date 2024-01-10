package Environment;

import Environment.Images.ImageFactory;
import Management.MetaDataHelper;
import Management.ObjectManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AObject implements IObject {
    private Position position;

    private String appearance;

    private boolean blocked = false;

    public AObject(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getAppearance(){
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }
}

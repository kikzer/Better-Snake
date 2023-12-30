package Environment;

import javafx.scene.canvas.GraphicsContext;

public interface IObject {

    /**
     * shows the current object on the game scene
     * @param graphicsContext makes the object visible
     */
    void show(GraphicsContext graphicsContext);

    /**
     * gives back the current position object of the IObject object
     * @return position object
     */
    Position getPosition();

    /**
     * updates the current position object of the IObject object
     * @param position new position object
     */
    void setPosition(Position position);

    /**
     * is used for IObjects to check if it is a wall and the player could die from it
     * @return boolean if it is a wall or not
     */
    boolean isBlocked();

    /**
     * is used to update the IObject to a wall or not
     * @param blocked boolean
     */
    void setBlocked(boolean blocked);

}


package GameField;

import Management.SnakeManagement.Snake;

public interface ObjectManager {
    default boolean checkCollision() {
        return false;
    }

}


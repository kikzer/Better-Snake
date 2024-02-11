package Environment;

public abstract class AObject implements IObject {
    private Position position;

    private String appearance;

    private boolean blocked = false;

    public AObject(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return new Position(position.getX(),position.getY());
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

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }
}

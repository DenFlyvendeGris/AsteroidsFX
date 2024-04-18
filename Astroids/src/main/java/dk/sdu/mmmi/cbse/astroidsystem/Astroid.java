package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Astroid extends Entity {

    private final AstroidSize size;
    private double speed;

    public Astroid(AstroidSize size) {
        this.size = size;
    }

    public AstroidSize getSize() {
        return size;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void onCollision(Entity other) {
        if (other instanceof Astroid) {
            double x1 = getX();
            double y1 = getY();

            double x2 = other.getX();
            double y2 = other.getY();

            double deltaX = x1 - x2;
            double deltaY = y1 - y2;

            double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            deltaX = deltaX / magnitude;
            deltaY = deltaY / magnitude;


            this.setRotation(Math.toDegrees(Math.atan2(deltaY,deltaX)));

            return;
        }
        new AstroidSplitterLogic().splitter(this);
    }
}

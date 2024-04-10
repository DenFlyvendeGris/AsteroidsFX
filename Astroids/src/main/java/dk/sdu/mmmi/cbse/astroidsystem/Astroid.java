package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Astroid extends Entity {

    private AstroidSize size;
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
}

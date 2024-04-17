package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

import java.util.Random;

public class AstroidFactory {

    private Random random = new Random();


    public Astroid createAsteroids(GameData gameData, AstroidSize size) {
        Astroid astroid = new Astroid(size);
        astroid.setSpeed(Math.random());

        if (size == AstroidSize.LARGE)
            astroid.setRadius(random.nextInt(20,31));
        if (size == AstroidSize.MEDIUM)
            astroid.setRadius(random.nextInt(15,21));
        if (size == AstroidSize.SMALL)
            astroid.setRadius(random.nextInt(5,11));

        setAsteroidShape(astroid);
        astroid.setX(gameData.getDisplayWidth() - 50);
        astroid.setY(gameData.getDisplayHeight()*Math.random());
        astroid.setRotation(180);

        return astroid;
    }

    private void setAsteroidShape(Entity asteroid) {
        asteroid.setPolygonCoordinates(
                0, 1 * asteroid.getRadius(),
                0.71 * asteroid.getRadius(), 0.71 * asteroid.getRadius(),
                1 * asteroid.getRadius(), 0,
                0.71 * asteroid.getRadius(), -0.71 * asteroid.getRadius(),
                0, -1 * asteroid.getRadius(),
                -0.71 * asteroid.getRadius(), -0.71 * asteroid.getRadius(),
                -1 * asteroid.getRadius(), 0,
                -0.71 * asteroid.getRadius(), 0.71 * asteroid.getRadius());
    }
}

package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;


public class AsteroidControls implements IEntityProcessingService {

    private Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {

        if (world.getEntities(Astroid.class).size() <= 3) {
            world.addEntity(createAsteroids(gameData));
        }

        for (Entity entity : world.getEntities(Astroid.class)) {
            double changeX = Math.cos(Math.toRadians(entity.getRotation()));
            double changeY = Math.sin(Math.toRadians(entity.getRotation()));
            entity.setX(entity.getX() + changeX * ((Astroid) entity).getSpeed());
            entity.setY(entity.getY() + changeY * ((Astroid) entity).getSpeed());

            if (entity.getX() <= 0 || entity.getX() >= gameData.getDisplayWidth() || entity.getY() <=0 || entity.getY() >= gameData.getDisplayHeight()) {
                entity.setDestroyed(true);
            }
        }


    }

    public Entity createAsteroids(GameData gameData) {
        Astroid astroid = new Astroid(AstroidSize.LARGE);
        astroid.setSpeed(Math.random());
        astroid.setRadius(random.nextInt(25 - 15 + 1) + 15);
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

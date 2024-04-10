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

    private void setSpawnLocation(Entity asteroid,GameData gameData) {

        int asteroidSpawnX = random.nextInt(gameData.getDisplayWidth());
        int asteroidSpawnY = random.nextInt(gameData.getDisplayHeight());

        if (random.nextInt(0,3) == 1) {

            // Top
            if (asteroidSpawnX > asteroidSpawnY) {
                asteroid.setX(asteroidSpawnX);
                asteroid.setY(-30);
                asteroid.setRotation(random.nextInt(0,180));
                System.out.println("kagemand top");
            }

            // Left
            if (asteroidSpawnY >= asteroidSpawnX) {
                asteroid.setX(-30);
                asteroid.setY(asteroidSpawnY);
                asteroid.setRotation(90 - random.nextInt(0,180));
                System.out.println("gulderød venstre");
            }
        } else {

            // Bottom
            if (asteroidSpawnX > asteroidSpawnY) {
                asteroid.setX(asteroidSpawnX);
                asteroid.setY(gameData.getDisplayHeight() + 30);
                asteroid.setRotation(180 + random.nextInt(0,180));
                System.out.println("Ostehaps bund");
            }

            // Right
            if (asteroidSpawnY >= asteroidSpawnX) {
                asteroid.setX(gameData.getDisplayWidth() + 30);
                asteroid.setY(asteroidSpawnY);
                asteroid.setRotation(270 - random.nextInt(0,180));
                System.out.println("utilfredshed til højre");
            }
        }
    }
    private void setAsteroidShape(Entity asteroid) {

        // Calculate points of asteroid
        double[][] points = new double[5][2];
        for (int i = 0; i < 5; i++) {
            double angle = Math.toRadians(72 * i - 90);
            points[i][0] = asteroid.getRadius() * Math.cos(angle) + random.nextInt(10,25);
            points[i][1] = asteroid.getRadius() * Math.sin(angle) + random.nextInt(10,25);
        }

        // Set coordinates of asteroid polygon from the semi randomly generated values
        asteroid.setPolygonCoordinates(
                points[0][0], points[0][1],
                points[1][0], points[1][1],
                points[2][0], points[2][1],
                points[3][0], points[3][1],
                points[4][0], points[4][1]);
    }

}

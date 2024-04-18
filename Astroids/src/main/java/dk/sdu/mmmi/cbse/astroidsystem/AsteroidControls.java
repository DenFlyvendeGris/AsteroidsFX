package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;



public class AsteroidControls implements IEntityProcessingService {



    @Override
    public void process(GameData gameData, World world) {

        if (world.getEntities(Astroid.class).size() <= 10) {
            world.addEntity(new AstroidFactory().createAsteroids(gameData, AstroidSize.LARGE));
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





}

package dk.sdu.mmmi.cbse.collider;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class Collider implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity: world.getEntities()) {
            for (Entity againstEntity: world.getEntities()) {
                if (entity.getID().equals(againstEntity.getID())) {
                    continue;
                }

                if (entity instanceof Bullet && ((Bullet) entity).getShooter().getID().equals(againstEntity.getID()))
                    continue;
                if (againstEntity instanceof Bullet && ((Bullet) againstEntity).getShooter().getID().equals(entity.getID()))
                    continue;

                double distance = Math.sqrt(Math.pow(entity.getX()-againstEntity.getX(), 2) + Math.pow(entity.getY()-againstEntity.getY(), 2));
                System.out.println(entity.getRadius() + " : " + againstEntity.getRadius());
                if (distance <= entity.getRadius() + againstEntity.getRadius()) {
                    entity.setDestroyed(true);
                }
            }
        }
    }

        /*TODO:
        - Create collision so it works in all cases, player meets bullet, player meets astroid... and so on
        - Be careful with the enum representation for the astroids since the logic is not created for splitting an astroid yet
        - create an or statement when player meets astroid.LARGE/astroid.Medium/astorid.SMALL to destroy player
         */
}

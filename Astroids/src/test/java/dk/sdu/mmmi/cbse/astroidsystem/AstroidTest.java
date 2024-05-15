package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AstroidTest {

    private GameData gameData;
    private World world;

    @BeforeEach
    public void setupWold() {
        world = new World();
        gameData = new GameData();
    }

    @Test
    public void testOnCollisionWithAsteroid() {
        Entity entity1 = new Entity();
        Entity entity2 = new Astroid(AstroidSize.LARGE);

        entity1.setRotation(0);

        entity1.onCollision(entity2);

        double expectedRotation = Math.toDegrees(Math.atan2(50, 50));
        assertEquals(expectedRotation, entity1.getRotation(), 0.01);
    }

    @Test
    public void testOnCollisionWithNonAsteroid() {

        Entity entity1 = new Entity();
        Entity entity2 = new Entity();

        entity1.setRotation(0);
        entity1.onCollision(entity2);

        assertEquals(0, entity1.getRotation(), 0);
    }
}
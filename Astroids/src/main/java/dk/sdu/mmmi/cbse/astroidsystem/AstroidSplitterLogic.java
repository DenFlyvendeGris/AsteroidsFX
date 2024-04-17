package dk.sdu.mmmi.cbse.astroidsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

public class AstroidSplitterLogic {

    private static World world;
    private static GameData gameData;
    private Random random = new Random();

    public void splitter(Astroid astroid) {
        astroid.setDestroyed(true);
        AstroidSize newFragmentSize;
        if (astroid.getSize() == AstroidSize.LARGE) {
            newFragmentSize = AstroidSize.MEDIUM;
        } else if (astroid.getSize() == AstroidSize.MEDIUM) {
            newFragmentSize = AstroidSize.SMALL;
        } else {
            return;
        }
        Astroid fragmentOne = new AstroidFactory().createAsteroids(gameData, newFragmentSize);
        fragmentOne.setX(astroid.getX() + random.nextInt(-1, 2));
        fragmentOne.setY(astroid.getY() + random.nextInt(-1, 2));

        Astroid fragmentTwo = new AstroidFactory().createAsteroids(gameData, newFragmentSize);
        fragmentTwo.setX(astroid.getX() + random.nextInt(-1, 2));
        fragmentTwo.setY(astroid.getY() + random.nextInt(-1, 2));

        world.addEntity(fragmentOne);
        world.addEntity(fragmentTwo);
    }

    public static void setWorld(World w) {
        world = w;
    }

    public static void setGameData(GameData gd) {
        gameData = gd;
    }



}

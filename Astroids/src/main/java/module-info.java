import dk.sdu.mmmi.cbse.astroidsystem.AsteroidControls;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Astroids {
    requires Common;
    requires javafx.graphics;
    exports dk.sdu.mmmi.cbse.astroidsystem;
    provides IEntityProcessingService with AsteroidControls;
}
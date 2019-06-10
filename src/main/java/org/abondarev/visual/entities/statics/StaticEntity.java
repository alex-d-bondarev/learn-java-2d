package org.abondarev.visual.entities.statics;

import org.abondarev.visual.Handler;
import org.abondarev.visual.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

}

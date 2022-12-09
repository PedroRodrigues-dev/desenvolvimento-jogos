package main.entity;

import main.graphics.Spritesheet;

public class RedOctorok extends Enemy{
    public RedOctorok(int x, int y) {
        super(x, y, Spritesheet.redOctorokBack, Spritesheet.redOctorokFront, Spritesheet.redOctorokLeft, Spritesheet.redOctorokRight);
    }
}

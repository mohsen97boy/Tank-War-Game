/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author mohsen
 */
public class Explosion extends Sprite {
    public Explosion(double x, double y, int width, int height, boolean visible) {
        super(x, y, width, height, visible);
        this.setImage("collide.gif");
    }
}
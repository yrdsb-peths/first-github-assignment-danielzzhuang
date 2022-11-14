import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class heal_I here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class heal_I extends Actor
{
    /**
     * Act - do whatever the heal_I wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int locationY=1000;
    public void setLocationY(int Y){
        locationY=Y;
    }
    public void show(){
        setLocation(450, locationY);
    }
    public void hide(){
        setLocation(450, 1000);
    }
    public void act(){
        GreenfootImage image = getImage();
        image.scale(300,300);
        setImage(image);
    }
}

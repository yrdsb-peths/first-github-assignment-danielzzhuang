import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class atk_I here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class atkEffect_I extends Actor
{
    public int damage;
    public int bossHp;
    /**
     * Act - do whatever the atk_I wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public double cAtk(int point, int hp){
        return -(point*10);
    }
    public void show(){
        setLocation(770, 450);
    }
    public void hide(){
        setLocation(770, 900);
    }
    public void act()
    {
        GreenfootImage image = getImage();
        image.scale(300,300);
        setImage(image);
    }
}

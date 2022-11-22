import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class boss extends Actor
{
    public int bossMaxHp=500;
    public int bossMinHp=0;
    public int bossHp=bossMaxHp;
    /**
     * Act - do whatever the boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    /**
     * Act - do whatever the Ele wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void boss(){
        
    }
    public int getBossHp(){
        return bossHp;
    }
    public int getBossMaxHp(){
        return bossMaxHp;
    }
    public void setBossHp(int change){
        if(bossHp+change > bossMaxHp){
            bossHp=bossMaxHp;
        }
        else if(bossHp+change < bossMinHp){
            bossHp=bossMinHp;
        }
        else{
            bossHp=bossHp+change;
        }
    }
    
    public boolean bossAlive(){
        if(bossHp==0){
            return false;
        }
        return true;
    }
    
    public void show(){
        setLocation(770, 450);
    }
    
    public void hide(){
        setLocation(900, 450);
    }
    
    public void act(){
        GreenfootImage image = getImage();
        image.scale(150,150);
        setImage(image);
    }
}

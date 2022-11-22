import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class atk_c1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class atk extends Actor
{
    public int skillNum;
    public int identificationNum;
    /**
     * Act - do whatever the atk_c1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public atk(int identificationNum){
        this.identificationNum=identificationNum;
    }
    public int atkSkill(int point){
        if(identificationNum<4){
            return -point*13;
        }
        return -1;
    }
    public void setSkillNum(int skillNum){
        this.skillNum=skillNum;
    }
    public void show(){
        setLocation(450, 50+skillNum*100);
        //y:150, 250, 350, 450 
    }
    public void hide(){
        setLocation(450, 1000);
    }
    public void act(){
        GreenfootImage image = getImage();
        image.scale(300, 300);
        setImage(image);
    }
}

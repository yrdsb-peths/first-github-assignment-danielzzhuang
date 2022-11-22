import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class gameDice_IV here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gameDice extends Actor
{
    /**
     * Act - do whatever the gameDice_IV wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] gameDice_idle = new GreenfootImage[6];
    boolean pickUp=false;
    public int diceNum;
    public int dicePoint;
    /**
     * Act - do whatever the gameDice wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public gameDice(int diceNum, int dicePoint){
        this.diceNum=diceNum;
        this.dicePoint=dicePoint;
    }
    public void setDicePoint(int point){
        dicePoint=point+1;
    }
    public int getDicePoint(){
        return dicePoint;
    }
    public void show()
    {
        setLocation(diceNum*60, 560);
    }
    public void hide()
    {
        setLocation(-50, 560);
    }
    public void act()
    {
        if(Greenfoot.mousePressed(this)) pickUp = true;
        if(Greenfoot.mouseClicked(null)) pickUp = false;
        if(pickUp){
            MouseInfo info = Greenfoot.getMouseInfo();
            setLocation(info.getX(), info.getY());
        }
        
        gameDice_idle[dicePoint] = new GreenfootImage("images/gameDice_idle/GameUsingDice"+dicePoint+".png");
        setImage(gameDice_idle[dicePoint]);
        gameDice_idle[dicePoint].scale(100, 100);
    }
}

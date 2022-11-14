import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaveWorld extends World
{
    public int dicePlayerNeed=3;
    public int bossAtkCoolDown=2;
    public int dicePlayerHave=dicePlayerNeed;
    public int round=1;
    
    public playerCharacter player;
    public boss boss;
    //public Label atkC1_text;
    public hpIcon[] iconArr=new hpIcon[2];
    public hpBar[] barArr=new hpBar[2];
    public gameDice[] diceArr=new gameDice[dicePlayerNeed];
    public atk[] atkArr= new atk[3];
    
    public atkEffect_I atkEffect1;
    
    public heal_I heal1;
    public heal_I heal2;
    public heal_I heal3;
    public caveBackGround cBG;
    
    
    public boolean gameStart=true;
    public boolean nextRound=false;
    public boolean fightBoss=false;
    public boolean[] fixedD=new boolean[]{false, false, false};
    /*
    public boolean fixedD1=false;
    public boolean fixedD2=false;
    public boolean fixedD3=false;
    */
    MouseInfo mouse = Greenfoot.getMouseInfo();
    SimpleTimer fixTimer = new SimpleTimer();
    SimpleTimer cBGTimer = new SimpleTimer();
    SimpleTimer nextRoundTimer = new SimpleTimer();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public CaveWorld()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, false);
        cBG = new caveBackGround();
        addObject(cBG, 900, getHeight()/2);
        
        atkArr[0] = new atk(0);
        addObject(atkArr[0], getWidth()/2, 1000);
        
        heal1= new heal_I();
        addObject(heal1, getWidth()/2, 1000);
        
        /*
        atkC1_text = new Label("atk1: cause point*30 damage", 30);
        addObject (atkC1_text, getWidth()/2, 1000);
        */
        for(int i=0; i<dicePlayerNeed; i++){
            diceArr[i]= new gameDice(i+1, 1);
            addObject(diceArr[i], 100, 900);
        }
        
        
        boss = new boss();
        addObject(boss, 770, 900);
        
        player = new playerCharacter();        
        addObject(player, 170, 900);
        
        for(int i=0; i<2; i++){
            barArr[i]=new hpBar(i);
            addObject(barArr[i], 1000, 27);
        }
        for(int i=0; i<2; i++){
            iconArr[i]=new hpIcon(i);
            addObject(iconArr[i], 1000, 27);
        }
        
        
        iconArr[1]=new hpIcon(1);
        addObject(iconArr[1], 1000, 27);
        
        atkEffect1 = new atkEffect_I();
        addObject(atkEffect1, 100, 900);
    }
    public void hideAllDice(){
        for(int i=0; i<dicePlayerNeed; i++){
            diceArr[i].hide();
        }
    }
    //get random point for all dice
    public void setUp_diceAndPoint(int diceNeed){
        for(int i=0; i<dicePlayerNeed; i++){
            diceArr[i].setDicePoint(Greenfoot.getRandomNumber(5));
            diceArr[i].show();
        }
    }
    public void setUp_playerAndEnemy(boolean fightWithBoss){
        player.show();
        if(fightWithBoss){
            boss.show();
        }
    }
    public void setUp_HpAndHpBar(boolean fightWithBoss){
        //155,27
        for(int i=0; i<2; i++){
            iconArr[i].show();
            barArr[i].show();
        }
        
        /*
        if(fightWithBoss){
            enemyHpBar.setHpBarLenght(boss.getBossMaxHp()/2);
        }
        else if(!fightWithBoss){
            enemyHpBar.setHpBarLenght(200);
        }
        */
        
    }
    public void setUp_skillOrder(){
        atkArr[0].setSkillNum(1);
        atkArr[0].show();
    }
    public void dynamicHpAndHpBar(boolean fightWithBoss){
        barArr[0].setLocation(player.getPlayerHp()-45, 27);
        showText("HP: "+String.valueOf(player.getPlayerHp()),40, 70);
        
        if(fightWithBoss){
            barArr[1].setLocation(945-(int)(boss.getBossHp()/3.2258), 27);
            showText("HP: "+String.valueOf(boss.getBossHp()),860, 70);
        }
        
    }
    public void heal(int point){
        player.setPlayerHp((int)(player.getPlayerMaxHp()*0.02*point));
    }
    public void bossAtk(){
        if(boss.getBossHp()<(int)(500/4*2) && bossAtkCoolDown<=0)
        {
            bossAtkCoolDown=3;
            boss.setBossHp(+100);
            player.setPlayerHp(-(int)((boss.getBossMaxHp()/14)*(player.getPlayerMaxHp()/100)*1.5));
        }
        else if(boss.getBossHp()>(int)(500/4*2)||bossAtkCoolDown>0){
            player.setPlayerHp(-(player.getPlayerMaxHp()/17+(int)(Math.random()*10)));
            bossAtkCoolDown--;
        }
        showText(String.valueOf(bossAtkCoolDown), 450, 300);
    }
    public void fixedD(){
        for(int i=0; i<dicePlayerNeed; i++){
            if( ((int)Math.sqrt(Math.pow(diceArr[i].getX()-545, 2)+Math.pow(diceArr[i].getY()-145, 2)))<15 ){
                //dice_1.setLocation(545, 145);//545, 145
                diceArr[i].hide();
                fixedD[i]=true;
            }
        }
    }
    public void act(){
        fightBoss=true;
        if(cBG.getX()>900-round*20){
            cBG.moveForward();
        }
        if(nextRoundTimer.millisElapsed() > 1500 && nextRound){
            bossAtk();
            setUp_diceAndPoint(dicePlayerNeed);
            dicePlayerHave=dicePlayerNeed;
            round+=1;
            nextRound=false;
            
        }
        else if(!nextRound){
            dynamicHpAndHpBar(fightBoss);
            fixedD();
            atkArr[0].show();
            heal1.show();
            if(dicePlayerHave<=0){
                nextRound=true;
            }
            if(!player.playerAlive() || !boss.bossAlive()){
                showText("Game Over", 450, 300);
                Greenfoot.stop();
            }
            showText("Round: "+String.valueOf(round), 450, 50);
            if(gameStart){
                setUp_diceAndPoint(dicePlayerNeed);
                setUp_playerAndEnemy(fightBoss);
                setUp_HpAndHpBar(fightBoss);
                setUp_skillOrder();
                gameStart=false;
            }
        }
        if(fixTimer.millisElapsed() > 5000){
            for(int i=0; i<dicePlayerNeed; i++){
                if(fixedD[i]){
                    boss.setBossHp(atkArr[0].atkSkill(diceArr[i].getDicePoint()));
                    fixTimer.mark();
                    fixedD[i]=false;
                    dicePlayerHave--;
                }
            }
        }
        
        
        
        
        
        
        
        
    }
}

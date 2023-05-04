import java.util.HashMap;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;


public class RPG{   

    public static void checkDecision(char decision, Scanner scnr){
        if (!(decision == 'R' || decision == 'A')){                    
            System.out.println("This is an incorrect choice try again");
            decision = scnr.nextLine().charAt(0);
            checkDecision(decision,scnr);
        }
    }

    public static void checkYesOrNo(char decision, Scanner scnr){
        if (!(decision == 'Y' || decision == 'N')){                    
            System.out.println("This is an incorrect choice try again");
            decision = scnr.nextLine().charAt(0);
            checkYesOrNo(decision,scnr);
        }
    }

    public static void isAlive(int lifePoints, String deathPrint, String fileName){
        if (lifePoints == 0){
            try{
                FileOutputStream fos = new FileOutputStream(fileName);
                PrintWriter pw = new PrintWriter(fos);
                System.out.println("You have 0 health points left. Therefore, your story ends here.");
                pw.println("Death Log:\n\n\n" + deathPrint);

                pw.close();
                System.exit(0);
            }
            catch (IOException e){
                System.out.println("This shit is not the right file");
            }

        }
    }

     public static void endGame(String deathPrint, String fileName){
        
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            PrintWriter pw = new PrintWriter(fos);
            pw.println("Journey Log:\n\n\n" + deathPrint);

            pw.close();
            System.exit(0);
         }
        catch (IOException e){
              
        }

    }
    

    public static void main(String[] args){
        ArrayList<String> kills = new ArrayList<String>();
        boolean isDead = false;
        int lifePoints = 100;
        String playerName;
        String weapon = "Nothing";
        String supportItem = "Nothing";
        String potion = "Nothing";
        String deathPrint = "";
        String fileName = "";
        char decision = 'R';
        char yesOrNo = 'N';
        //This makes a scanner variable and makes a string variable
        Scanner scnr = new Scanner(System.in);
        Inventory inventory = new Inventory(weapon,supportItem,potion);
        

        System.out.println("Welcome to Fran's Fanstistical Fantasy. What is your name?\n");

        playerName = scnr.nextLine();
        fileName = playerName.replace(' ','_') + "'s journey.txt";

        //FileOutputStream fos = new FileOutputStream(fileName);
        //PrintWriter pw = new PrintWriter(fos);

        System.out.println("\nThe sounds of bells echo throughout the valley. You open your eyes only to find more darkness. You feel as though you're suffocating and can hardly breathe.\n");
        System.out.println("In a panic, you try to get up, but slam your head into hard surface, leaving you slightly disorientated. You take a second to reevaluate, and realize that it is the top of something\n");
        System.out.println("You push up and toss the heavy top away. Immediately, your nostrils fill with the smell of rotting bodies and decaying mud.\n");
        System.out.println("You get up and realize you're in a dilapitated stone coffin. Suddenly, you realize you do not remember a single thing. The more you try to remember, the more a pain you're in\n");
        System.out.println("You step out of your coffin and begin to explore some of the area.\n");
        System.out.println("You find a dull blade and a torch located next to your 'coffin'...\n");
        System.out.println("You've acquired a dull blade and torch!\n");

        inventory.setWeapon("Dull Blade");
        inventory.setSupportItem("Torch");
        Map map = new Map();

        Area area = new Area("Graveyard","This place reeks of sulfur and piss. You want to get out of here. To your left there is an sacrifical altar. To your right there is a pale wood forest with hardly any live plants.");
        area.addNewArea("Altar");
        area.addNewArea("Forest");
        map.addArea(area);

        area = new Area("Altar","On the front of the alter with the name of a god, however, it is extremely dirty so you can only make out the name Mohg.");
        area.addNewArea("Forest");
        map.addArea(area);

        area = new Area("Forest","As you walk through the swamp you hear low growls and the sounds of animals rustling. To your left, there is a large looming castle. To your right there is an abondoned shack.\n");
        area.addNewArea("Castle");
        area.addNewArea("Shack");
        map.addArea(area);

        area = new Area("Shack", "It smells like coal and freshly baked bread. You greet the dwarf and you guys sit down and share some stories. +25 Life Points");
        area.addNewArea("Castle");
        map.addArea(area);

        area = new Area("Castle","A giant gate seperates the gate from the outside. Theres a corridor that you can take to get into the castle.\n");
        area.addNewArea("Throne");   
        map.addArea(area);

        area = new Area("Throne","You look out into your new kingdom.");
        area.addNewArea("Quit");
        map.addArea(area);
        
        String input1 = "Graveyard";

        map.getArea(input1);
        while(!(input1.equalsIgnoreCase("Quit"))){
            if (map.getArea(input1) != null){
                if (input1.equalsIgnoreCase("Altar")){
                    System.out.println("You approach the altar to get a better look at. Unfortunately, you failed to check your surrondings and get stabbed in the back by a ghoul. You are bleeding profusely: -50 life points\nDo you choose to run away and or attack? (R/A)");
                        lifePoints -= 50;
                        decision = scnr.nextLine().charAt(0);
                        deathPrint += "You got back stabbed by a ghoul.\n\n";
                            
                        while(!(decision == 'R' || decision == 'A')){                    
                            System.out.println("This is an incorrect choice try again");
                            decision = scnr.nextLine().charAt(0);
                            checkDecision(decision,scnr);
                        }

                            if (decision == 'A'){                                
                                System.out.println("You spin around and attack with your dull blade seperating the ghouls head from its body. Upon futher inspection you find a potion with a light green tint in it. Do you drink it? (Y/N)");
                                kills.add("Ghoul");
                                deathPrint += "You kill the ghoul and patch yourself up by cauterizing the wound.\n\n";
                                yesOrNo = scnr.nextLine().charAt(0);
                                checkYesOrNo(yesOrNo,scnr);
                            if (yesOrNo == 'Y'){
                                System.out.println("You take the top off and chug that shit. Unfortunately, this is not a game. This was a poison potion. You lose -50 life points");
                                deathPrint += "You drank the poison potion\n\n";
                                lifePoints -= 50;
                                if (lifePoints == 0){
                                    deathPrint += "Cause of death: Poison Potion\n\n";
                                }
                                isAlive(lifePoints, deathPrint, fileName);
                                
                            }
                            else{
                                    System.out.println("You keep the potion!");
                                    inventory.setPotion("Miscellaneous Potion");
                                    System.out.println(inventory.toString());
                            }                            
                        }
                        else{
                            System.out.println("...Coward");
                        }
                    }
                    if (input1.equalsIgnoreCase("Shack")){
                        System.out.println("You walk into the crumbling building and are immediately met by a sword at your chin. You can't see the assailant, but then look down and see a scared dwarf. You show that you are not a threat and drop your " + inventory.getWeapon() + " to ensure that they understand you are not after them. He offers you a his shortsword and shows deep regret. He also offers you a rage potion. Do you take it? (Y/N)");                            
                        yesOrNo = scnr.nextLine().charAt(0);
                        checkYesOrNo(yesOrNo,scnr);
                        if (yesOrNo == 'Y'){
                            lifePoints += 25;
                            System.out.println("You've acquired the Dwarf's Shortsword!");
                            inventory.setWeapon("Dwarf's Shortsword");
                            inventory.setPotion("Rage Potion");
                            System.out.println(inventory.toString());
                        }
                        deathPrint += "You met the dwarf in the shack.\n\n";
                    }
                    if (input1.equalsIgnoreCase("Castle")){
                        
                        System.out.println("You go to the entrance to see a dragon emblem on the door. The guard yells at you in a language you don't understand. You try to speak, but you can't seem to make any noise. They start unsheathing their weapons. There are two of them, one with a halberd and one with a shortsword.\n");
                        
                        if (inventory.getWeapon().equalsIgnoreCase("Dull Blade")){
                            System.out.println("You attempt to stab the one with the shortsword, but your "+ inventory.getWeapon()+" snaps in half. You suddenly feel a blazing hot liquid streaming down your abdomen. The guard flashes his golden, rotting teeth with pleasure as he wipes the blood from his shortsword. Life points -25! ");
                            inventory.setWeapon("Nothing");
                            System.out.println("What do you do now? Run away or attack? (R/A)");
                            deathPrint += "You weapon broke on the guard.\n\n";
                            decision = scnr.nextLine().charAt(0);
                            checkDecision(decision,scnr);
                            if (decision == 'A'){
                                System.out.println("You swing at the guard with the shortsword, however, you find that your vision is upside down and that you are now significantly shorter...oh.");
                                lifePoints -= lifePoints;
                                if (lifePoints == 0){

                                    deathPrint += "Cause of death: Beheaded by Guard\n\n";
                                }
                                isAlive(lifePoints, deathPrint, fileName);
                            }
                            else{
                                System.out.println("Sike you thought. You get a shortsword straight to the heart. Be more prepared next time.");
                                lifePoints-=lifePoints;
                                deathPrint += "You tried to run away from the guard.\n\n";
                                deathPrint += "Cause of death: Stabbed through the heart by Guard\n\n";
                                isAlive(lifePoints, deathPrint, fileName);
                            }
                        }
                        else{
                            System.out.println("You succeed in piercing your " + inventory.getWeapon() + " in between the slits of his armor. He coughs up blood and his body goes limp." );
                            kills.add("Guard");
                            System.out.println("The other guard drops his weapon in fear and runs away. Do you pick up his halberd? (Y/N)");
                            yesOrNo = scnr.nextLine().charAt(0);
                            checkYesOrNo(yesOrNo,scnr);
                            if (yesOrNo == 'Y'){
                                deathPrint += "You killed the first guard and took the other one's weapon.\n\n";
                                System.out.println("You've acquired the Guard's Halberd");
                                inventory.setWeapon("Guard's Halberd");
                                System.out.println(inventory.toString());
                            }
                        }
                    }
                    if (input1.equalsIgnoreCase("Throne")){
                        System.out.println("You walk into the throne room and see a tall ivory skinned man with fiery hair. His scowl is almost piercing. He flings open his cloak to reveal a comically large spoon. He throws it in front of you. He grabs a comically large fork and beckons you to pick up the spoon. Do you pick it up? (Y/N)");
                        yesOrNo = scnr.nextLine().charAt(0);
                        checkYesOrNo(yesOrNo,scnr);
                        if (yesOrNo == 'Y'){
                            inventory.setWeapon("King Spoon");
                            System.out.println(inventory.toString());
                            System.out.println("You both lock eyes and dash towards each other at inhuman speeds. You clash and trade blows for a period of time.");
                            if (inventory.getPotion().equalsIgnoreCase("Rage Potion") && inventory.getWeapon().equalsIgnoreCase("King Spoon") && lifePoints >= 100){
                                System.out.println("Thankfully, because of the rage potion you received from the dwarf, the King Spoon, and because you were careful at all corners, you scoop his innards and take his crown.");
                                kills.add("The King");
                                deathPrint += "You killed the King and this is now your kingdom. Well done.\n\n";
                            }
                            else{
                                System.out.println("Due to your injuries and to lack of preperation, you lose the fight of attrition and are skewered by the King Fork. You spit in his face and succumb to your wounds.");
                                lifePoints -= lifePoints;
                                deathPrint += "Cause of death: Skewered by the King\n\n";
                                isAlive(lifePoints,deathPrint,fileName);
                            }

                        }
                        else{
                            System.out.println("Due to your injuries and to lack of preperation, you lose the fight of attrition and are skewered by the King Fork. You spit in his face and succumb to your wounds.");
                            lifePoints -= lifePoints;
                            deathPrint += "Cause of death: Skewered by the \n\n";
                            isAlive(lifePoints,deathPrint,fileName);
                        }
                    }
                    System.out.println(map.getArea(input1));

                    System.out.println("Please choose a new area");
                    input1 = scnr.nextLine();
            }
            else{
                System.out.println("Invalid area.");
                System.out.println("Please choose a new area");
                input1 = scnr.nextLine();

            }
        }
        deathPrint += "Kills: ";
        for (int i =0; i < kills.size()-1;i++){
            deathPrint += kills.get(i);
        }

        endGame (deathPrint,fileName);

    }
}


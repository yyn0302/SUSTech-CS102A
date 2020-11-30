package backend.util;

public class ValidMove {
    int dice1,dice2;
    String moveStatement;

    ValidMove(){
        dice1=RandomDice.nextInt(1,6);
        dice2=RandomDice.nextInt(1,6);
    }

    @Override
    public String toString(){
        return "s";
    }

//    public static ValidMove[] getValidMove(){
//
//    }
}

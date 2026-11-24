package Logic.Card;

public class ImageFinder {

    public static String findImageAddress(CardNames cardName,boolean isFace){
        if (isFace){
            if (cardName == CardNames.Contessa1 || cardName == CardNames.Contessa2 || cardName == CardNames.Contessa3) {
                return "./src/main/resources/contessa.jpg";
            }else if(cardName == CardNames.Duke1 || cardName == CardNames.Duke2 || cardName == CardNames.Duke3){
                return "./src/main/resources/duke.jpg";
            }else if(cardName == CardNames.Ambassador1 || cardName == CardNames.Ambassador2 || cardName == CardNames.Ambassador3){
                return "./src/main/resources/ambassador.jpg";
            }else if(cardName == CardNames.Captain1 || cardName == CardNames.Captain2 || cardName == CardNames.Captain3){
                return "./src/main/resources/captain.jpg";
            }else if(cardName == CardNames.Assassin1 || cardName == CardNames.Assassin2 || cardName == CardNames.Assassin3){
                return "./src/main/resources/assassin.png";
            }
        }
        return "./src/main/resources/coup.jpg";
    }
}

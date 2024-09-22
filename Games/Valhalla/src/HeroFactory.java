package src;

public class HeroFactory {
    public static Hero createHero(String heroName) {
        switch (heroName) {
            case "Thor":
                return new Thor();
            case "Loki":
                return new Loki();
            case "Heimdall":
                return new Heimdall();
            case "Feiticeira Negra":
                return new FeiticeiraNegra();
            case "Odin":
                return new Odin();
            default:
                return null;
        }
    }
}

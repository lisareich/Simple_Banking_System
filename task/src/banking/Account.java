package banking;

public class Account {
    private Card card;

    public Account(Card card) {
        this.card = card;
    }
    public Account() {
        this.card = createCard();
        PrintUtil.createdCard();
        this.card.printCardInfo();
    }

    private Card createCard() {
        return new Card();
    }
    public Card getCard() {
        return card;
    }
}

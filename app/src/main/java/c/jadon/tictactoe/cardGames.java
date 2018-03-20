package c.jadon.tictactoe;


import java.util.Random;

// This class is used to set up a deck of cards and make references to some functions common
// in many card games.
public class cardGames {

    // Empty constructor (until I think of something necessary to put here)
    public cardGames() {

    }

    private static class Card {
        int value;
        String suit;

        public Card(int value, String suit) {
            this.value = value;
            this.suit = suit;
        }
    }

    Card card;

    // The deck is implemented as a linked list so that we can easily remove a card from the middle
    // of the deck, for example. The deck is also treated as a stack, because that's what a deck of
    // cards is in real life.
    private static class Deck {
        Card currentCard;
        Deck nextCard;

        private Deck(Card currentCard, Deck nextCard) {
            this.currentCard = currentCard;
            this.nextCard = nextCard;
        }
    }

    public Deck createDeck() {
        Deck deck = null;
        String[] suits = {"Diamond", "Heart", "Club", "Spades"};
        for (int cardValue = 1; cardValue <= 13; cardValue++) {
            for (int i = 0; i < 4; i++) {
                deck = new Deck(new Card(cardValue, suits[i]), deck);
            }
        }

        return deck;
    }

    private Deck shuffle(Deck deck, final int cardCount) {
        Card[] deckArray = new Card[52];
        int i = 0;
        Deck shuffledDeck = null;

        while (deck.nextCard != null) {
            deckArray[i] = deck.currentCard;
            ++i;
            deck = deck.nextCard;
        }
        deckArray[i] = deck.currentCard;

        // This is a implementation of the Fisher-Yates shuffling algorithm, which frequently
        // involves indexing through a 'list' of objects. Hence, an array would be more convenient
        // and efficient than a linked list (O(n) vs O(n^2) time complexity respectively) here.
        for (int shuffleCards = cardCount; shuffleCards > 0; shuffleCards--) {
            int randOrder = new Random().nextInt(deckArray.length);
            shuffledDeck = new Deck(deckArray[randOrder - 1], shuffledDeck);
            deckArray[randOrder - 1] = deckArray[cardCount - 1];
        }
        shuffledDeck = new Deck(deckArray[0], shuffledDeck);

        return shuffledDeck;
    }

    private Deck dealCard(Deck deck, int cardCount) {
        card = deck.currentCard;
        deck = deck.nextCard;

        --cardCount;
        return deck;
    }
}


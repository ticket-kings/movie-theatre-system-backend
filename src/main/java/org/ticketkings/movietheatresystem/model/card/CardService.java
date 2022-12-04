package org.ticketkings.movietheatresystem.model.card;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Executes card business logic and uses repository class to retrieve data from the database
 */
@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

}

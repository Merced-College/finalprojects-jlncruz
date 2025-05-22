import java.util.HashMap;

public class DeckManager {
	public HashMap<String, HashMap<String, String>> allDecks = new HashMap<>();

	public void newDeck(String deckName) {
		HashMap<String, String> newDeck = new HashMap<String, String>();
		allDecks.put(deckName, newDeck);
		System.out.println("New deck created successfully!");
	}

	public void printAllDecks() {
		System.out.println("\nAll decks: ");
		for (String keyValue : allDecks.keySet()) {
			System.out.println(keyValue);
		}
		System.out.println();
	}

	public void deleteDeck(String deckName) {
		if (allDecks.containsKey(deckName)) {
			allDecks.remove(deckName);
			System.out.println("Deck deleted successfully!");
		}
		else {
			System.out.println("Deck not found. Try again.");
		}
	}

	public void deleteCard(String deckName, String frontOfCardToDelete) {
		if (allDecks.containsKey(deckName)) {
			HashMap<String, String> deckToCheck = allDecks.get(deckName);
			if (deckToCheck.containsKey(frontOfCardToDelete)) {
				deckToCheck.remove(frontOfCardToDelete);
				System.out.println("Card deleted successfully!");
			}

			else {
				System.out.println("Card not found. Try again.");
			}
		}
		else {
			System.out.println("Deck not found. Try again.");
		}

	}

}

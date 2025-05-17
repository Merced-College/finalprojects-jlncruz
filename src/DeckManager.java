import java.util.HashMap;

public class DeckManager {
	HashMap<String, HashMap> allDecks = new HashMap<>();

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

	// public void chooseDeck(String deckName) {
	// 	if (allDecks.containsKey(deckName)) {
	//
	// 	}
	// }
}

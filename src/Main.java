// Ikna: a simple flashcard game utilizing hash maps and a class that manages the entire flashcard data storage system
// Jose Cruz
// CPSC39
// SP2025

import java.util.Scanner;
import java.util.HashMap;

public class Main {

	// commands when in the main menu
	public static void commandMenu() {
		System.out.println();
		System.out.println("nd\tCreate a new deck.");
		System.out.println("h\tDisplay command menu.");
		System.out.println("q\tQuit program.");
		System.out.println("cd\tChoose a deck to start studying.");
		System.out.println("dd\tDelete a deck.");
		System.out.println();
	}

	// commands when reviewing a deck
	public static void deckCommands() {
		System.out.println();
		System.out.println("j\tCycle between front and back of card.");
		System.out.println("n\tNext card.");
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to Ikna.");
		System.out.println("You are currently in the main menu, where you can manage decks, see the help commands, or start studying a deck");
		System.out.println("Enter \"nd\" to create a new deck, 'h' to show commands menu, or 'q' to quit.");
		var deckManager = new DeckManager();

		boolean userQuit = false;
		String deckName;
		String userAnswer = scnr.next();
		while (!userQuit) {
			if (userAnswer.equals("nd")) {
				System.out.print("Name of new deck (no spaces allowed): ");
				deckName = scnr.next();
				deckManager.newDeck(deckName);
				System.out.println("Enter \"nd\" to create another deck, or enter \"pd\" to see all your decks.");
			}

			else if (userAnswer.equals("h")) {
				commandMenu();
			}

			else if (userAnswer.equals("pd")) {
				deckManager.printAllDecks();
			}

			else if (userAnswer.equals("q")) {
				System.out.println("Exiting...");
				break;
			}

			// main part of program (reviewing decks, adding cards, removing cards, etc.)
			else if (userAnswer.equals("cd")) {


				boolean userInMainPartOfProgram = true;
				while (userInMainPartOfProgram) {
					if (deckManager.allDecks.isEmpty()) {
						System.out.print("Looks like you don't have any decks. Would you like to create a deck? [y/n] ");
						userAnswer = scnr.next();
						boolean userCreatingNewDeck;
						if (userAnswer.equals("y")) {
							userCreatingNewDeck = true;
							while (userCreatingNewDeck) {
								System.out.print("Name of new deck: ");
								deckName = scnr.next();
								deckManager.newDeck(deckName);
								System.out.println("\nEnter \"nd\" to create another deck.");
								System.out.println("Enter any key to continue.\n");
								userAnswer = scnr.next();
								if (!userAnswer.equals("nd")) {
									userCreatingNewDeck = false;
								}
							}
						}

						else {
							System.out.println("Exiting program.");
							break;
						}

					}

					boolean userReviewing = true;
					System.out.print("\nEnter name of deck to review (ensure no spaces in deck name + case sensitive): ");
					String deckToReview = scnr.next();

					if (deckManager.allDecks.containsKey(deckToReview)) {

						// removes warning for HashMap unchecked conversion
						@SuppressWarnings("unchecked")

						HashMap<String, String> deckBeingReviewed = deckManager.allDecks.get(deckToReview);
						if (deckBeingReviewed.isEmpty()) {
							System.out.print("\nLooks like you don't have any cards in this deck. Would you like to add cards? (Enter 'y' to add cards and any other key to exit.) ");
							userAnswer = scnr.next();
							boolean userAddingCardsToDeck;
							if (userAnswer.equals("y")) {
								userAddingCardsToDeck = true;
								while (userAddingCardsToDeck) {
									System.out.print("Front of card: ");
									String frontOfCard = scnr.next();
									System.out.print("Back of card: ");
									String backOfCard = scnr.next();
									deckBeingReviewed.put(frontOfCard, backOfCard);
									System.out.println("\nEnter \"nc\" to create another card.");
									System.out.println("Enter any other key(s) to start reviewing deck.\n");
									userAnswer = scnr.next();
									if (!userAnswer.equals("nc")) {
										userAddingCardsToDeck = false;
									}
								}
							}

							else {
								System.out.println("Exiting program.");
								userQuit = true;
								break;
							}
						}
						System.out.println("Current deck being reviewed: " + deckToReview);
						System.out.println("Use 'j' to see back of card and 'l' to move to next card.");
						System.out.println("Use 'H' to see all deck commands.");
						boolean userReviewingCurrentCard = true;
							for (String keyValue : deckBeingReviewed.keySet()) {
								while (userReviewingCurrentCard) {
									System.out.println("\nFront of current card: " + keyValue);
									String nextAction = scnr.next();
									if (nextAction.equals("H")) {
										deckCommands();
									}
									else if (nextAction.equals("j"))  {
										System.out.println("\nBack of current card: " + deckBeingReviewed.get(keyValue));
									}
									System.out.println("Use \"l\" to move to next card");
									nextAction = scnr.next();
									if (nextAction.equals("l")) {
										userReviewingCurrentCard = false;
									}
								}
							}

					}

					else {
						System.out.println("Deck not found. Try again.");
					}

				}


			}

			else if (userAnswer.equals("dd")) {
				System.out.print("Name of deck to delete (no spaces allowed + case sensitive): ");
				String deckToDelete = scnr.next();
				deckManager.deleteDeck(deckToDelete);
			}

			else {
				System.out.println("Invalid command. See 'h' for list of commands.");
			}
			userAnswer = scnr.next();
		}
		// deckManager.newDeck("Japanese");
		// deckManager.newDeck("Human Anatomy");
		// deckManager.newDeck("Geography");
		//
		// deckManager.printAllDecks();

		scnr.close();
	}
}

// Ikna: a simple flashcard game utilizing hash maps and a class that manages the entire flashcard data storage system
// Jose Cruz
// CPSC39
// SP2025

import java.util.Scanner;
import java.util.HashMap;

public class Ikna {

	// commands when in the main menu
	public static void listCommands() {
		System.out.println();
		System.out.println("mm\tReturn to main menu.");
		System.out.println("nd\tCreate a new deck.");
		System.out.println("h\tDisplay command menu.");
		System.out.println("q\tQuit program.");
		System.out.println("cd\tChoose a deck to start studying.");
		System.out.println("dd\tDelete a deck.");
		System.out.println("dc\tDelete a card from a deck.");
		System.out.println();
	}

	// commands when reviewing a deck
	public static void deckCommands() {
		System.out.println();
		System.out.println("j\tSee back side of card.");
		System.out.println("k\tSee front side of card.");
		System.out.println("l\tNext card.");
	}

	public static void mainMenuGreet() {
		System.out.println("\nWelcome to Ikna.");
		System.out.println("You are currently in the main menu, where you can manage decks, see the help commands, or start studying a deck");
		System.out.println("Enter \"nd\" to create a new deck, 'h' to show commands menu, or 'q' to quit.");
	}

	public static void deckDeletionPrompt() {
		System.out.print("\nName of deck to delete (no spaces allowed + case sensitive) ");
		System.out.print("(Enter \"mm\" to get back to main menu): ");
	}

	public static void cardDeletionPrompt() {
		System.out.print("\nName of card to delete (no spaces allowed + case sensitive) ");
		System.out.print("(Enter \"mm\" to get back to main menu): ");
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		mainMenuGreet();
		var deckManager = new DeckManager();

		boolean userWantsToGoToMainMenu = false;
		boolean userQuit = false;
		boolean userAnswerInvalid = true;
		boolean userInMainPartOfProgram = true;
		String deckName;
		String userAnswer = scnr.next();
		while (!userQuit) {
		// Card creation algorithm
			if (userAnswer.equals("nd")) {
				System.out.print("\nName of new deck (ensure no spaces in deck name): ");
				deckName = scnr.next();
				deckManager.newDeck(deckName);
				System.out.println("Enter \"nd\" to create another deck, or enter \"pd\" to see all your decks.");
			}

			else if (userAnswer.equals("h")) {
				listCommands();
			}

			else if (userAnswer.equals("pd")) {
				deckManager.printAllDecks();
			}

			else if (userAnswer.equals("q")) {
				System.out.println("Exiting...");
				break;
			}

			// start of else-if block that contains the main logic
			// main part of program (reviewing decks, adding cards, removing cards, etc.)
			else if (userAnswer.equals("cd")) {


				while (userInMainPartOfProgram) {
					// checks if HashMap that contains all decks is empty. If it's empty it prompts you to add decks to it.
					if (deckManager.allDecks.isEmpty()) {
						System.out.print("Looks like you don't have any decks. Would you like to create a deck? [y/n] ");
						userAnswer = scnr.next();
						while (userAnswerInvalid) {

							boolean userCreatingNewDeck;
							if (userAnswer.equals("y")) {
								userCreatingNewDeck = true;
								while (userCreatingNewDeck) {
									System.out.print("Name of new deck: ");
									deckName = scnr.next();
									deckManager.newDeck(deckName);
									System.out.println("\nEnter \"nd\" to create another deck.");
									userCreatingNewDeck = false;
									userAnswer = scnr.next();
								}
							}

							else if (userAnswer.equals("n") || userAnswer.equals("mm")) {
								userAnswerInvalid = false;
								userInMainPartOfProgram = false;
								userWantsToGoToMainMenu = true;
							}

							else {
								System.out.println("Invalid input. Please answer question with 'y' to create a new deck or 'n' to exit to main menu.");
								userAnswer = scnr.next();
							}

						}
					}

					if (!userQuit && !userWantsToGoToMainMenu) {
						System.out.println("\nEnter name of deck to review (ensure no spaces in deck name + case sensitive)");
						System.out.println("(Use \"pd\" to see all your decks!)");
						String deckToReview = scnr.next();

						boolean userMeantCommand = false;
						if (deckToReview.equals("pd")) {
							if (deckManager.allDecks.containsKey("pd")) {
								System.out.println("There is a deck named \"pd\", which interferes with global command, \"pd\"");
								System.out.println("Did you mean the deck, or the command? [deck/command]");
								userAnswer = scnr.next();

								if (userAnswer.equals("command")) {
									deckManager.printAllDecks();
									userMeantCommand = true;
								}
							}

							else {
								deckManager.printAllDecks();
							}
						}

						if (deckManager.allDecks.containsKey(deckToReview) && !userMeantCommand) {

							HashMap<String, String> deckBeingReviewed = deckManager.allDecks.get(deckToReview);
							boolean userAddingCardsToDeck;
							if (deckBeingReviewed.isEmpty()) {
								System.out.print("\nLooks like you don't have any cards in this deck. Would you like to add cards? [y/n]: ");
								userAnswer = scnr.next();
								while (userAnswerInvalid) {

									if (userAnswer.equals("y")) {
										userAddingCardsToDeck = true;

										// hub for adding cards to deck
										while (userAddingCardsToDeck) {
												userAnswerInvalid = true;
												System.out.print("Front of card: ");
												String frontOfCard = scnr.next();
												System.out.print("Back of card: ");
												String backOfCard = scnr.next();
												deckBeingReviewed.put(frontOfCard, backOfCard);
												System.out.println("\nEnter \"nc\" to create another card.");
												System.out.println("Press \"mm\" to exit to main menu.\n");
												System.out.println("Press 'r' to start reviewing deck.");
												userAnswer = scnr.next();

											while (userAnswerInvalid) {

												if (userAnswer.equals("mm")) {
													/* Because of the amount of nested while loops, we have to create a chain
													 * of boolean "switches" to leave each nested loop*/

													// leave 3rd while loop (2nd nested, current one)
													userAddingCardsToDeck = false;
													// leave 2nd while loop (1st nested)
													userAnswerInvalid = false;
													// leave 1st while loop (outer-most one in this case)
													userInMainPartOfProgram = false;
													// make the program doesn't go into the next section
													userWantsToGoToMainMenu = true;
												}

												else if (userAnswer.equals("nc")) {
													userAnswerInvalid = false;
												}

												else if (userAnswer.equals("r")) {
													userAnswerInvalid = false;
													System.out.println("Initializing deck reviewing environment...");
													userAddingCardsToDeck = false;
												}

												else {
													System.out.println("Invalid input.");
													userAnswer = scnr.next();
												}

											}

										}
									}

									// sends user back to main menu if they say no to adding cards to an empty deck, or if they say "mm"
									else if (userAnswer.equals("n") || userAnswer.equals("mm")) {
										userAnswerInvalid = false;
										userInMainPartOfProgram = false;
										userWantsToGoToMainMenu = true;
									}

									else {
										System.out.print("Please enter 'y' or 'n' (Press 'r' to start reviewing deck): ");
										userAnswer = scnr.next();
									}

								}

							}

							if (!userWantsToGoToMainMenu) {

								boolean uniqueCardSession = false;
								System.out.println("Would you like activate unique card session? (Press any key other than 'y' and 'n' to skip this message) [y/n]");
								userAnswer = scnr.next();
								if (userAnswer.equals("y")) {
									uniqueCardSession = true;
									System.out.println("\nUnique card session status: ON");
									System.out.println("Only unique cards will be shown! After all cards have been reviewed once, Ikna will send you to the main menu.");
								}

								else if (userAnswer.equals("n")) {
									System.out.println("Unique card session status: OFF");
								}


								System.out.println("\nCurrent deck being reviewed: " + deckToReview);
								System.out.println("Use 'j' to see back of card, 'k' to see front of card, and 'n' to move to next card.");
								System.out.println("Use 'H' to see all deck commands.");

								boolean userReviewing = true;

								// main card reviewing logic/algorithm
								String nextAction;
								if (!uniqueCardSession) {
									while (userReviewing) {
										for (String keyValue : deckBeingReviewed.keySet()) {
											boolean nextCard = false;
											System.out.println("\nFront of current card: " + keyValue);
											while (!nextCard) {
												nextAction = scnr.next();
												if (nextAction.equals("H")) {
													deckCommands();
												}

												else if (nextAction.equals("k"))  {
													System.out.println("\nFront of current card: " + keyValue);
												}

												else if (nextAction.equals("j"))  {
													System.out.println("\nBack of current card: " + deckBeingReviewed.get(keyValue));
												}

												else if (nextAction.equals("l")) {
													nextCard = true;
												}

												// if user wants to stop reviewing and go back to the main menu
												else if (nextAction.equals("qr") || nextAction.equals("mm")) {
													userReviewing = false;
													break;
												}

												else {
													System.out.println("Invalid command.");
												}
											}
										}
									}

								}

								else if (uniqueCardSession) {
									for (String keyValue : deckBeingReviewed.keySet()) {
										boolean nextCard = false;
										System.out.println("\nFront of current card: " + keyValue);
										while (!nextCard) {
											nextAction = scnr.next();
											if (nextAction.equals("H")) {
												deckCommands();
											}

											else if (nextAction.equals("k"))  {
												System.out.println("\nFront of current card: " + keyValue);
											}

											else if (nextAction.equals("j"))  {
												System.out.println("\nBack of current card: " + deckBeingReviewed.get(keyValue));
											}

											else if (nextAction.equals("l")) {
												nextCard = true;
											}

											// if user wants to stop reviewing and go back to the main menu
											else if (nextAction.equals("qr") || nextAction.equals("mm")) {
												userReviewing = false;
												break;
											}

											else {
												System.out.println("Invalid command.");
											}
										}
									}
									System.out.println("All cards have been reviewed! Exiting to main menu");
									userInMainPartOfProgram = false;
									userWantsToGoToMainMenu = true;
								}

							}

						}

						else if (deckToReview.equals("h")) {
							listCommands();
						}

						else if (deckToReview.equals("mm")) {
							userInMainPartOfProgram = false;
							userWantsToGoToMainMenu = true;
						}

						// catch this possibility so that it doesn't hit the final else
						else if (deckToReview.equals("pd")) {
						}

						else {
							System.out.println("Deck not found. Try again.");
						}

					}

				}


			}
			// end of initial else if block

			else if (userAnswer.equals("dd")) {
				deckDeletionPrompt();
				String deckToDelete = scnr.next();
				while (!deckToDelete.equals("mm")) {
					deckManager.deleteDeck(deckToDelete);
					deckDeletionPrompt();
					deckToDelete = scnr.next();
					if (deckToDelete.equals("mm")) {
						userWantsToGoToMainMenu = true;
					}
				}
			}

			else if (userAnswer.equals("mm")) {
				System.out.println("Already in main menu!");
			}

			else if (userAnswer.equals("dc")) {
				String deckToDelete;
				String cardToDelete;
				while (!userWantsToGoToMainMenu) {
					System.out.print("\nSelect deck from which you would like to delete a card from: ");
					deckToDelete = scnr.next();
					if (deckToDelete.equals("mm")) {
						userWantsToGoToMainMenu = true;
						break;
					}
					System.out.print("Name of card that you would like to delete a card from: ");
					cardToDelete = scnr.next();
					if (cardToDelete.equals("mm")) {
						userWantsToGoToMainMenu = true;
						break;
					}
					deckManager.deleteCard(deckToDelete, cardToDelete);
				}
			}

			else {
				System.out.println("Invalid command. See 'h' for list of commands.");
			}
			// greet user again at the end of while loop
			if (userWantsToGoToMainMenu) {
				mainMenuGreet();
				// reset all boolean switches when going to main menu
				userAnswerInvalid = true;
				userWantsToGoToMainMenu = false;
				userInMainPartOfProgram = true;
			}
			userAnswer = scnr.next();
		}
		scnr.close();
	}
}

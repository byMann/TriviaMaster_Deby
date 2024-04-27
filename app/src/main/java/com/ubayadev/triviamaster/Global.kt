package com.ubayadev.triviamaster

object Global {

    val player = arrayListOf(Player("Deby", 3000, "History", "Hard", "rabbit"),
                            Player("Fio01", 15000, "Arts and Culture", "Hard", "koala"),
                            Player("Edward99", 12000, "Science", "Hard", "tiger"),
                            Player("Nath4n", 1000, "History", "Easy", "rabbit"),
                            Player("Fan33", 4000, "Arts and Culture", "Medium", "koala"),
                            Player("Ant0n", 9000, "Science", "Hard", "rabbit"),
                            Player("Anton28", 3000, "Science", "Hard", "tiger"),
                            Player("Kusti06", 12000, "Science", "Hard", "koala"),
                            Player("Putr4", 6000, "History", "Medium", "tiger"),
                            Player("michaels", 1000, "History", "Medium", "rabbit"),
                            Player("michelle", 5000, "Arts and Culture", "Easy", "koala"))
    //bank soal
    val questionsBank = listOf(
        Category(
            "History",
            DifficultyLevel(
                Easy = listOf(
                    Question(
                        "Who was the first President of the United States?",
                        listOf("George Washington", "Thomas Jefferson", "Abraham Lincoln", "Benjamin Franklin"),
                        listOf(80, 10, 5, 5)
                    ),
                    Question(
                        "Which of these countries did the Soviet Union NEVER invade?",
                        listOf("Afghanistan", "Finland", "Poland", "Sweden"),
                        listOf(10, 10, 3, 77)
                    ),
                    Question(
                        "What was the capital of the Byzantine Empire?",
                        listOf("Constantinople", "Athens", "Rome", "Jerusalem"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "Who was the first person to orbit the Earth?",
                        listOf("Neil Amstrong", "Yuri Gagarin", "Joseph Stalin", "Leon Trotsky"),
                        listOf(14, 79, 1, 6)
                    ),
                    Question(
                        "Which war saw the Battle of Gettysburg?",
                        listOf("American Civil War", "World War I", "World War II", "Vietnam War"),
                        listOf(85, 5, 5, 5)
                    )
                ),
                Medium = listOf(
                    Question(
                        "What is the name of the first device that successfully landed on the moon?",
                        listOf("Apollo 11", "Apollo 13", "Apollo 17", "Voyager 1"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "What was the primary cause of the French Revolution?",
                        listOf("Social inequality and economic hardship", "Foreign invasion", "Religious conflicts", "Monarchical corruption"),
                        listOf(85, 5, 5, 5)
                    ),
                    Question(
                        "Who was the longest-reigning monarch in British history?",
                        listOf("Queen Victoria", "King Henry VIII", "Queen Elizabeth I", "King George III"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "Which ancient civilization built the Great Pyramid of Giza?",
                        listOf("Ancient Egyptians", "Ancient Greeks", "Ancient Romans", "Mesopotamians"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "Who led the Russian Revolution in 1917 and established the Soviet Union?",
                        listOf("Vladimir Lenin", "Joseph Stalin", "Leon Trotsky", "Nikita Khrushchev"),
                        listOf(85, 5, 5, 5)
                    )
                ),
                Hard = listOf(
                    Question(
                        "What was the primary cause of the collapse of the Western Roman Empire?",
                        listOf("Various factors, including economic decline, military defeats, and internal strife", "Invasion by the Huns", "Plague", "Christianity"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "Who was the leader of the Bolshevik Revolution in Russia in 1917?",
                        listOf("Vladimir Lenin", "Joseph Stalin", "Leon Trotsky", "Nikita Khrushchev"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "Which treaty officially ended World War I?",
                        listOf("Treaty of Versailles", "Treaty of Paris", "Treaty of Trianon", "Treaty of Potsdam"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "Who was the first female prime minister of a European country?",
                        listOf("Margaret Thatcher", "Golda Meir", "Indira Gandhi", "Angela Merkel"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "Which ancient city was destroyed by the eruption of Mount Vesuvius in 79 AD?",
                        listOf("Pompeii", "Athens", "Rome", "Alexandria"),
                        listOf(70, 24, 1, 5)
                    )
                )
            )
        ),
        Category(
            "Science",
            DifficultyLevel(
                Easy = listOf(
                    Question(
                        "What is the term for Newton's law of gravitation?",
                        listOf("Universal gravitation", "Kepler's law", "Coulomb's law", "Archimedes' law"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "What is the process by which plants make their own food?",
                        listOf("Photosynthesis", "Respiration", "Fermentation", "Transpiration"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "What is the unit of electrical resistance?",
                        listOf("Ohm", "Volt", "Ampere", "Watt"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "What is the chemical symbol for oxygen?",
                        listOf("O", "C", "H", "N"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "What is the smallest unit of matter?",
                        listOf("Atom", "Molecule", "Cell", "Element"),
                        listOf(90, 5, 2, 3)
                    )
                ),
                Medium = listOf(
                    Question(
                        "What is the term for the study of living organisms?",
                        listOf("Biology", "Physics", "Chemistry", "Geology"),
                        listOf(85, 5, 5, 5)
                    ),
                    Question(
                        "What is the name for the process by which plants convert sunlight into energy?",
                        listOf("Photosynthesis", "Respiration", "Fermentation", "Transpiration"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "What is the chemical symbol for gold?",
                        listOf("Au", "Ag", "Fe", "Cu"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "Which planet is known as the 'Red Planet'?",
                        listOf("Mars", "Venus", "Jupiter", "Saturn"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "What is the SI unit of force?",
                        listOf("Newton", "Watt", "Joule", "Pascal"),
                        listOf(85, 5, 5, 5)
                    )
                    // Add other Medium questions here
                ),
                Hard = listOf(
                    Question(
                        "What is the speed of sound in air at room temperature?",
                        listOf("Approximately 343 meters per second", "Approximately 299,792 kilometers per second", "Approximately 1,000,000 kilometers per second", "Approximately 9.8 meters per second squared"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "What is the chemical formula for sulfuric acid?",
                        listOf("H2SO4", "HCl", "H2O", "CO2"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "What is the process by which radioactive elements decay into other elements called?",
                        listOf("Radioactive decay", "Nuclear fission", "Nuclear fusion", "Isotope splitting"),
                        listOf(90, 5, 2, 3)
                    ),
                    Question(
                        "What is the largest organ in the human body?",
                        listOf("Skin", "Liver", "Heart", "Brain"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "What is the chemical formula for ammonia?",
                        listOf("NH3", "H2O", "CO2", "NaCl"),
                        listOf(90, 5, 2, 3)
                    )
                )
            )
        ),
        Category(
            "Arts and Culture",
            DifficultyLevel(
                Easy = listOf(
                    Question(
                        "Who painted the 'Mona Lisa'?",
                        listOf("Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "Who wrote 'Romeo and Juliet'?",
                        listOf("William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "Who is known for sculpting 'David'?",
                        listOf("Michelangelo", "Leonardo da Vinci", "Donatello", "Raphael"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "Who is the author of 'To Kill a Mockingbird'?",
                        listOf("Harper Lee", "F. Scott Fitzgerald", "Ernest Hemingway", "Mark Twain"),
                        listOf(95, 2, 1, 2)
                    ),
                    Question(
                        "Who composed 'Für Elise'?",
                        listOf("Ludwig van Beethoven", "Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "Franz Schubert"),
                        listOf(95, 2, 1, 2)
                    )
                ),
                Medium = listOf(
                    Question(
                        "Who painted the famous artwork 'The Starry Night'?",
                        listOf("Vincent van Gogh", "Pablo Picasso", "Claude Monet", "Leonardo da Vinci"),
                        listOf(80, 10, 5, 5)
                    ),
                    Question(
                        "Who wrote the novel '1984'?",
                        listOf("George Orwell", "F. Scott Fitzgerald", "Ernest Hemingway", "J.K. Rowling"),
                        listOf(80, 10, 5, 5)
                    ),
                    Question(
                        "Which artist is known for creating the sculpture 'The Thinker'?",
                        listOf("Auguste Rodin", "Michelangelo", "Donatello", "Leonardo da Vinci"),
                        listOf(80, 10, 5, 5)
                    ),
                    Question(
                        "Who is the author of 'The Catcher in the Rye'?",
                        listOf("J.D. Salinger", "Harper Lee", "F. Scott Fitzgerald", "Ernest Hemingway"),
                        listOf(80, 10, 5, 5)
                    ),
                    Question(
                        "Who composed the 'Four Seasons'?",
                        listOf("Antonio Vivaldi", "Johann Sebastian Bach", "Ludwig van Beethoven", "Wolfgang Amadeus Mozart"),
                        listOf(80, 10, 5, 5)
                    )

                ),
                Hard = listOf(
                    Question(
                        "Who is the architect behind the design of the Sydney Opera House?",
                        listOf("Jørn Utzon", "Frank Lloyd Wright", "Le Corbusier", "Zaha Hadid"),
                        listOf(85, 5, 5, 5)
                    ),
                    Question(
                        "Which artist painted the ceiling of the Sistine Chapel?",
                        listOf("Michelangelo", "Leonardo da Vinci", "Raphael", "Titian"),
                        listOf(85, 5, 5, 5)
                    ),
                    Question(
                        "Who wrote the epic poem 'The Divine Comedy'?",
                        listOf("Dante Alighieri", "Homer", "Virgil", "Geoffrey Chaucer"),
                        listOf(85, 5, 5, 5)
                    ),
                    Question(
                        "Which ballet composer composed 'Swan Lake'?",
                        listOf("Pyotr Ilyich Tchaikovsky", "Igor Stravinsky", "Sergei Prokofiev", "Ludwig Minkus"),
                        listOf(85, 5, 5, 5)
                    ),
                    Question(
                        "Who created the sculpture 'The Thinker'?",
                        listOf("Auguste Rodin", "Michelangelo", "Donatello", "Leonardo da Vinci"),
                        listOf(85, 5, 5, 5)
                    )
                )
            )
        )
    )
}
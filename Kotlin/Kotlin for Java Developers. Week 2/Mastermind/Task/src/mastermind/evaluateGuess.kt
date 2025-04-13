package mastermind


data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

        var rightPosition = 0
        var wrongPosition = 0

        val secretUsed = BooleanArray(CODE_LENGTH) // Eşleşen secret harfleri işaretle
        val guessUsed = BooleanArray(CODE_LENGTH)  // Eşleşen guess harfleri işaretle

        // 1. Aşama: Doğru pozisyonlar
        for (i in 0 until  CODE_LENGTH) {
            if (secret[i] == guess[i]) {
                rightPosition++
                secretUsed[i] = true
                guessUsed[i] = true
            }
        }

        // 2. Aşama: Yanlış pozisyondaki doğru harfler
        for (i in 0 until CODE_LENGTH) {
            if (!secretUsed[i]) { //only false values
                for (j in 0 until CODE_LENGTH) {
                    if (!guessUsed[j] && secret[i] == guess[j]) {
                        wrongPosition++
                        secretUsed[i] = true
                        guessUsed[j] = true
                        break
                    }
                }
            }
        }

        return Evaluation(rightPosition, wrongPosition)

}

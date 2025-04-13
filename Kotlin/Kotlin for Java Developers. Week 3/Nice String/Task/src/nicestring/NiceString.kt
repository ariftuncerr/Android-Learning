package nicestring

fun String.isNice(): Boolean {{}
    val hasDoubleLetter = this.zipWithNext().any { it.first == it.second }
    val hasThreeVowels = this.count { it in "aeiou" } >= 3
    val hasNoBadSubstring = listOf("bu", "ba", "be").none { this.contains(it) }


    return listOf(hasDoubleLetter, hasThreeVowels, hasNoBadSubstring).count { it } >= 2


}

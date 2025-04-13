class TestWhenCondition {
    companion object{
        fun respondToInput(input : String) =
            when ( input ) {
                "yes","y","evet" -> "You are happy"
                "no","n","hayır" -> "You are sad"
                else -> "I don't know"
            }

    }


}
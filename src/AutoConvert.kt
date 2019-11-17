private fun loop() {
    val number = 100
    for (i in 0..number - 1) {
        print(i)
    }
}

private fun largerNumber() {
    val number = Math.max(1, 100)
}

private fun stringConcatenation() {
    val familyName = "長島"
    val givenName = "雅人"
    val fullName = "フルネーム:" + familyName + givenName
}

private fun redundantAssignment() {
    val someString: String
    someString = "Hi"
}

private fun elvisOperator(nullableString: String?) {
    val nonNullString =
        if (nullableString == null) {
            "non-null"
        } else {
            nullableString
        }
}

private fun uppercaseOnlyNonNullString(nullableString: String?) {
    if (nullableString != null) {
        nullableString.toUpperCase()
    }
}

private fun keyValuePair() {
    val favoriteLanguage = hashMapOf<String, String>()
    favoriteLanguage.put("長島", "Kotlin")
}
infix fun Int.isDivisibleBy(denominator: Int) = this % denominator == 0

fun main() {
    print("うるう年の判定をします。")

    // メッセージを表示し、入力を促す。入力を受け取ったら、それを数字に変換する
    val receiveInputAsInt: (String) -> Int = { msg -> convertStringToInteger(promptInput(msg)) }

    // 入力を受け取る
    val startYear = receiveInputAsInt("判定を開始する年を入力してください")
    val endYear = receiveInputAsInt("判定を終了する年を入力してください")

    // 結果を表示する
    print(startYear, endYear, receiveInputAsInt)
}

private fun isLeapYear(year: Int): Boolean {
    // ある数で引数として受け取った年が割り切れるかを判定する
    val yearIsDivisibleBy: (Int) -> Boolean = { denominator -> year isDivisibleBy denominator }

    // 400で割れるか、100で割れるか、4で割れるか、それ以外かを判定する
    return when {
        yearIsDivisibleBy(400) -> true
        yearIsDivisibleBy(100) -> false
        yearIsDivisibleBy(4) -> true
        else -> false
    }
}

private tailrec fun promptInput(msg: String): String {
    println(msg)
    // 入力を受け取れなければもう一度入力を促す
    return readLine() ?: promptInput("すみません、入力が受け取れませんでした。\n$msg")
}

private tailrec fun convertStringToInteger(input: String): Int {
    val regexNumber = Regex("[1-9][0-9]*")
    return if (input.matches(regexNumber) || input == "0") {
        input.toInt()
    } else {
        convertStringToInteger(promptInput("0以上の半角数字を入力してください。"))
    }
}

private tailrec fun print(startYear: Int, endYear: Int, receiveInputAsInt: (String) -> Int) {
    if (startYear < endYear) {
        (startYear..endYear)
            .filter { year -> isLeapYear(year) }
            .let { leapYear -> print(leapYear) }
    } else {
        print(startYear, receiveInputAsInt("開始年より後の年を入力してください"), receiveInputAsInt)
    }
}
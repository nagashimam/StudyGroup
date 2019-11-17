import kotlin.random.Random

fun main() {
    Janken().play()
}

class Janken() {

    private val hands = arrayOf(Rock(), Scissors(), Paper())

    fun play() {
        println("じゃんけんしよう!")
        Thread.sleep(1000)
        println("最初はグー、じゃんけん...")

        // 乱数でCPUの出す手を決めて、判定結果を表示
        hands[Random.nextInt(0, PlayerHand.values().size)]
            .shot(receivePlayerHand())
            .let { println(it) }

        if (promptInput("もう一度する? \n 0: うん! それ以外: もう疲れた") == "0") {
            Janken().play()
        }
    }

    private tailrec fun promptInput(msg: String): String {
        println(msg)
        // 入力を受け取れなけれ1ばもう一度入力を促す
        return readLine() ?: promptInput("ごめん、聞こえなかった。\n$msg")
    }

    private tailrec fun receivePlayerHand(): PlayerHand {
        val input = promptInput("0:グー 1:チョキ 2:パー")
        val handOption = Regex("[0-${PlayerHand.values().size - 1}]")
        return if (input.matches(handOption)) {
            PlayerHand.values()[input.toInt()]
        } else {
            receivePlayerHand()
        }
    }
}

sealed class CpuHand {
    val cpuWin = "私の勝ち!"
    val even = "あいこ!"
    val playerWin = "あなたの勝ち!"

    // 自分の手を表示
    abstract fun myHand()

    // 結果判定
    abstract fun result(playerHand: PlayerHand): String

    fun shot(playerHand: PlayerHand): String {
        myHand()
        return result(playerHand)
    }
}

class Rock : CpuHand() {
    override fun myHand() = println("グー")

    override fun result(playerHand: PlayerHand) =
        when (playerHand) {
            PlayerHand.ROCK -> even
            PlayerHand.SCISSORS -> cpuWin
            PlayerHand.PAPER -> playerWin
        }
}

class Paper : CpuHand() {
    override fun myHand() = println("パー")

    override fun result(playerHand: PlayerHand) =
        when (playerHand) {
            PlayerHand.ROCK -> cpuWin
            PlayerHand.SCISSORS -> playerWin
            PlayerHand.PAPER -> even
        }
}

class Scissors : CpuHand() {
    override fun myHand() = println("チョキ")

    override fun result(playerHand: PlayerHand) =
        when (playerHand) {
            PlayerHand.ROCK -> playerWin
            PlayerHand.SCISSORS -> even
            PlayerHand.PAPER -> cpuWin
        }
}

// プレイヤーは結果判定をしないので、ロジックを持たせない
enum class PlayerHand {
    ROCK, SCISSORS, PAPER
}

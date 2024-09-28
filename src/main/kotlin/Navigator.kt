class Navigator {
    fun showMenu(name: String, optionList: List<Pair<String, () -> Unit>>) {
        while (true) {
            println("\n$name")
            optionList.forEachIndexed { index, option ->
                println("${index + 1} - ${option.first}")
            }
            println("0 - Назад")

            val choice = getNumInput(optionList)
            if (choice == 0) return
            optionList[choice - 1].second()
        }
    }
}
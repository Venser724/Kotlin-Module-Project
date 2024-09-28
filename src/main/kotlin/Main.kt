import java.util.InputMismatchException
import java.util.Scanner

fun main() {
    val navigator = Navigator()
    val archiveList = mutableListOf<Archive>()


    println("Добро пожаловать в Заметки!")
    navigator.showMenu("Меню архивов", listOf(
        "Создать архив" to {
            println("Введите название архива:")
            val name = getTextInput()
            archiveList.add(Archive(name))
            println("Архив '$name' создан.")
        },
        "Просмотреть архивы" to {
            if (archiveList.isEmpty()) {
                println("Нет доступных архивов.")
            } else {
                navigator.showMenu(
                    "Выбор архива",
                    archiveList.map { it.name to { it.archiveMenu(navigator) } })
            }
        }
    ))
}


fun getTextInput(): String {
    val scanner = Scanner(System.`in`)
    var input = scanner.nextLine()
    while (input.trim().isEmpty()) {
        println("Поле не может быть пустым. Повторите ввод:")
        input = scanner.nextLine()
    }
    return input
}

fun getNumInput(optionList: List<Pair<String, () -> Unit>>): Int {
    println("Выберите пункт меню, введя соответствующую цифру:")
    val scanner = Scanner(System.`in`)

    while (true) {
        try {
            val choice = scanner.nextInt()
            if (choice !in 0..optionList.size) {
                println("Цифра должна быть в диапазоне от 0 до ${optionList.size}.")
            } else {
                scanner.nextLine()
                return choice
            }
        } catch (e: InputMismatchException) {
            println("Ввод некорректен.Введите цифру в диапазоне от 0 до ${optionList.size}.")
            scanner.next()
        }
    }
}
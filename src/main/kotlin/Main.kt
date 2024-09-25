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
    println("Выберите пункт меню, введя соответствующую цифру: ")
    val scanner = Scanner(System.`in`)
    while (true) {
        try {
            scanner.nextInt()
        } catch (e: Exception) {
            println("Ошибка ввода. Пожалуйста, введите число.")
            scanner.next()
        }
        try {
            if (scanner.toString().toInt() < 0 || scanner.toString().toInt() > optionList.size) {
                continue
            }
        } catch (e: Exception) {
            println("Введите число от 0 до ${optionList.size}.")
        }
    }
}
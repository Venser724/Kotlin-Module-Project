class Archive(val name: String) {
    val noteList = mutableListOf<Note>()

    fun archiveMenu(navigator: Navigator) {
        navigator.showMenu("Вы находитесь в архиве '$name'", listOf(
            "Добавить заметку" to { addNote() },
            "Просмотреть заметки" to { showNotes(navigator) }
        ))
    }

    fun addNote() {
        println("Введите название заметки:")
        val noteName = getTextInput()
        println("Введите текст заметки:")
        val noteContent = getTextInput()
        noteList.add(Note(noteName, noteContent))
        println("Заметка '$name' добавлена.")
    }

    fun showNotes(navigator: Navigator) {
        if (noteList.isEmpty()) {
            println("Нет заметок в архиве.")
            return
        }

        navigator.showMenu("Выбор заметки", noteList.map { it.noteName to { it.getContent() } })
    }

}
package org.example.menu;

public enum MenuCommand {
    EXIT("0", "Выход"),
    FILL_RANDOM("1", "Заполнить случайно"),
    FILL_MANUAL("2", "Заполнить вручную"),
    FILL_FILE("3", "Заполнить из файла"),
    DISPLAY("4", "Показать все автомобили"),
    SORT_POWER("5", "Сортировать по мощности"),
    SORT_MODEL("6", "Сортировать по модели"),
    SORT_YEAR("7", "Сортировать по году"),
    SEARCH("8", "Поиск автомобиля"),
    SAVE_FILE("9", "Сохранить в файл");

    private final String code;
    private final String description;

    MenuCommand(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }

    public static MenuCommand fromCode(String code) {
        for (MenuCommand cmd : values()) {
            if (cmd.code.equals(code)) {
                return cmd;
            }
        }
        return null;
    }
}

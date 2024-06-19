package ScriptLanguage_2;

import java.util.HashMap;
import java.util.Map;

public class VariableStore {
    private final Map<String, Integer> variables = new HashMap<>();

    // Установка значения переменной
    public void setVariable(String name, int value) {
        variables.put(name, value);
    }

    // Получение значения переменной
    public int getVariable(String name) {
        return variables.getOrDefault(name, 0);
    }

    // Проверка наличия переменной
    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }
}

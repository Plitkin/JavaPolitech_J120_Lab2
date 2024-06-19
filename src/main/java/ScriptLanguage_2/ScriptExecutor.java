package ScriptLanguage_2;

public class ScriptExecutor {
    private final VariableStore variableStore = new VariableStore();
    private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(variableStore);

    // Выполнение одной строки скрипта
    public void executeLine(String line) {
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) {
            // Игнорирование пустых строк и комментариев
            return;
        }

        if (line.startsWith("set")) {
            // Обработка оператора set
            handleSet(line);
        } else if (line.startsWith("print")) {
            // Обработка оператора print
            handlePrint(line);
        } else {
            System.err.println("Неизвестный оператор: " + line);
            System.exit(1);
        }
    }

    // Обработка оператора set
    private void handleSet(String line) {
        try {
            String[] parts = line.split("=", 2);
            if (parts.length != 2 || !parts[0].trim().startsWith("set $")) {
                throw new IllegalArgumentException("Неверный формат оператора set");
            }

            String varName = parts[0].trim().substring(5).trim();
            String expression = parts[1].trim();
            int value = expressionEvaluator.evaluate(expression);

            variableStore.setVariable(varName, value);
        } catch (Exception e) {
            System.err.println("Ошибка в операторе set: " + e.getMessage());
            System.exit(1);
        }
    }

    // Обработка оператора print
    private void handlePrint(String line) {
        try {
            String content = line.substring(5).trim();
            if (content.startsWith("\"") && content.endsWith("\"")) {
                // Печать строки
                System.out.println(content.substring(1, content.length() - 1));
            } else {
                // Обработка строк и переменных, разделенных запятыми
                String[] parts = content.split(",");
                for (String part : parts) {
                    part = part.trim();
                    if (part.startsWith("\"") && part.endsWith("\"")) {
                        // Печать строки
                        System.out.print(part.substring(1, part.length() - 1));
                    } else if (part.startsWith("$")) {
                        // Печать значения переменной
                        String varName = part.substring(1);
                        if (variableStore.hasVariable(varName)) {
                            System.out.print(variableStore.getVariable(varName));
                        } else {
                            throw new IllegalArgumentException("Неизвестная переменная: " + varName);
                        }
                    } else {
                        throw new IllegalArgumentException("Неверный формат оператора print");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Ошибка в операторе print: " + e.getMessage());
            System.exit(1);
        }
    }
}

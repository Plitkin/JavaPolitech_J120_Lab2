package ScriptLanguage_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {
    private final VariableStore variableStore;

    public ExpressionEvaluator(VariableStore variableStore) {
        this.variableStore = variableStore;
    }

    // Оценка математического выражения
    public int evaluate(String expression) {
        try {
            expression = expression.replaceAll("\\s+", "");

            // Разбор выражения и замена переменных на их значения
            Pattern pattern = Pattern.compile("\\$[a-zA-Z_][a-zA-Z_0-9]*");
            Matcher matcher = pattern.matcher(expression);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String varName = matcher.group().substring(1);
                if (variableStore.hasVariable(varName)) {
                    matcher.appendReplacement(sb, String.valueOf(variableStore.getVariable(varName)));
                } else {
                    throw new IllegalArgumentException("Неизвестная переменная: " + varName);
                }
            }
            matcher.appendTail(sb);
            expression = sb.toString();

            // Теперь expression не содержит переменных, только числа и операторы
            return evaluateSimpleExpression(expression);
        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка в выражении: " + expression, e);
        }
    }

    // Оценка простого математического выражения без переменных
    private int evaluateSimpleExpression(String expression) {
        int result = 0;
        int currentNumber = 0;
        char currentOperation = '+';

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }

            if (!Character.isDigit(ch) || i == expression.length() - 1) {
                switch (currentOperation) {
                    case '+':
                        result += currentNumber;
                        break;
                    case '-':
                        result -= currentNumber;
                        break;
                }

                currentOperation = ch;
                currentNumber = 0;
            }
        }

        return result;
    }
}


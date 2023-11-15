import java.util.Stack;

public class Calculator {

    public static final String OP_ADD = "+";
    public static final String OP_SUB = "-";
    public static final String OP_MUL = "*";
    public static final String OP_DIV = "/";

    private final Stack<Float> calculator = new Stack<>();

    public float evaluate(String line){
        String[] terms = line.split(" ");
        float answer = 0f;

        for (String term : terms){
            switch (term){
                case OP_ADD:
                case OP_SUB:
                case OP_MUL:
                case OP_DIV:

                float rhs = calculator.pop();
                float lhs = calculator.pop();

                if (term.equals(OP_ADD)){
                    answer = lhs + rhs;
                } else if (term.equals(OP_SUB)){
                    answer = lhs - rhs;
                } else if (term.equals(OP_MUL)){
                    answer = lhs * rhs;
                } else {
                    answer = lhs / rhs;
                }

                break;
                
                default:
                calculator.push(Float.parseFloat(term));

            } 
        }

        return answer;

        }
    }
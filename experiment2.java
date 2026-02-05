interface ModerationRule {
    int apply(int marks);
}

class AttendanceModeration implements ModerationRule {
    public int apply(int marks) {
        return marks + 5;
    }
}

interface Program {
    int calculateFinalMarks(int theory, int lab);
    String generateGrade(int finalMarks);
}

class BTechProgram implements Program {
    public int calculateFinalMarks(int theory, int lab) {
        return (int) (theory * 0.6 + lab * 0.4);
    }

    public String generateGrade(int marks) {
        if (marks >= 80) return "A";
        return "B";
    }
}

abstract class EvaluationEngine {
    protected Program program;
    protected ModerationRule rule;

    public EvaluationEngine(Program program, ModerationRule rule) {
        this.program = program;
        this.rule = rule;
    }

    public final void evaluate() {
        int t = getTheory();
        int l = getLab();
        int score = rule.apply(program.calculateFinalMarks(t, l));
        System.out.println("Grade: " + program.generateGrade(score));
    }

    protected abstract int getTheory();
    protected abstract int getLab();
}

class CollegeSystem extends EvaluationEngine {
    public CollegeSystem(Program p, ModerationRule r) {
        super(p, r);
    }

    protected int getTheory() { return 70; }
    protected int getLab() { return 80; }
}

public class experiment2 {
    public static void main(String[] args) {
        EvaluationEngine engine = new CollegeSystem(new BTechProgram(), new AttendanceModeration());
        engine.evaluate();
    }
}
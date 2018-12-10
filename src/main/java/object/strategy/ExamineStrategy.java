package object.strategy;

import process.Response;

public interface ExamineStrategy {
    Response examine(String label);
}

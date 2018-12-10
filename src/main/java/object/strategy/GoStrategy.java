package object.strategy;

import process.Response;

public interface GoStrategy {
    Response go(String label);
}
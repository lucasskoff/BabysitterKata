package test;

import code.FamilyInterface;

public class FamilyCodeFake implements FamilyInterface {
    @Override
    public int computePay(int hour) {
        return 1;
    }
}

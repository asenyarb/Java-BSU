package StrangeClasses;

public class SimpleClass extends AbstractClass implements SimpleInterface{

    @Override
    public void print() {
        System.out.println(this.value);
    }

    @Override
    public void multiplyByNumber() {
        this.value *= 5;
    }
}

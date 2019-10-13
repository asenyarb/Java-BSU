import java.util.regex.*;
class Regex {
    private String pattern;
    Regex() {
        this.pattern = "-?[0-9]*[,.]?[0-9]*((E|e)-?[0-9]*)?";
    }
    Regex(String pattern){
        this.pattern = pattern;
    }
    void change_pattern(String pattern){
        this.pattern = pattern;
    }
    boolean match(String line){
        return Pattern.compile(pattern).matcher(line).matches();
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class WordsCollection {

    private final Map<String, Integer> words;

    public WordsCollection() {
        words = new HashMap<>();
    }

    public void add(String word, int count) {
        words.put(word, count);
    }

    /*
        Для сортировки мапы по значению используется стрим, в котором записи сортируются по значению
        и заносятся в новую, отсортированную мапу
        После чего записи мапы преобразуются в список строк с указанием слова и количества его повторений
        После, чтобы слова были отсортированы в порядке убывания, список реверсится
     */

    public List<String> getList() {
        List<String> wordsList = new ArrayList<>();
        LinkedHashMap<String, Integer> sortedWordMap = words.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<String, Integer> pair : sortedWordMap.entrySet()) {
            wordsList.add(pair.getKey() + " (" + pair.getValue() + ")");
        }

        Collections.reverse(wordsList);

        return wordsList;
    }

    public boolean contains(String key) {
        return words.containsKey(key);
    }

    public void incrementValue(String key) {
        words.put(key, words.get(key) + 1);
    }
}

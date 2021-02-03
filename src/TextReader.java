import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class TextReader {

    private final WordsCollection wordsCollection;
    private URL url;

    public TextReader(String url) {
        this.wordsCollection = new WordsCollection();

        //инициализация URL и обработка ошибок
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /*
        В цикле с файла считается каждая строка, после чего строка заспилится на отдельные слова
        Которые будут помещены в коллекцию для слов (Map, где они отсортируются)
        Чтобы слова учитывались без учета региста, каждое слово полностью транслируется в нижний регистр
        После в консоль выводится список слов
    */


    public void readText() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner != null && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("[^a-zA-Z0-9_А-Яа-я]+");

            for (String word : words) {
                if (word.isEmpty()) {
                    continue;
                }

                word = word.toLowerCase();

                if (wordsCollection.contains(word)) {
                    wordsCollection.incrementValue(word);
                } else {
                    wordsCollection.add(word, 1);
                }
            }
        }
        prettyListPrint(wordsCollection.getList());
    }

    private void prettyListPrint(List<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
    }
}

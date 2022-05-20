import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JaccardSimilarity {
    public static float union(String[] query, String document)
    {
        float answer = 0.0F;
        float similarity=0;
        float sum=0;
        String[] docWords = document.split("\\s+");
        for (int i=0;i< query.length;i++)
        {
            for (int j=0; j<docWords.length; j++)
            {
                if (query[i].equals(docWords[j]))
                {
                    similarity++;
                }
            }

        }
        String[] total = new String[query.length+ docWords.length];
        int start = query.length;
        int end = docWords.length;

        System.arraycopy(query, 0, total, 0, start);
        System.arraycopy(docWords, 0, total, start, end);
        Set<String> set = new LinkedHashSet<>(Arrays.stream(total).toList());
        sum = set.size();
        answer=similarity /sum;
        return answer;
    }

    public static String readFile (String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner fileReader = new Scanner(file);
        String fileData = "";
        while (fileReader.hasNextLine())
        {
            fileData += fileReader.nextLine();
            fileData  += "\n";
        }
        return fileData.toLowerCase();
    }
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enter your Query:");
        Scanner input = new Scanner(System.in);
        String queryInput = input.nextLine();
        String query = queryInput.toLowerCase();
        //words splitting
        /**WORDs**/
        String[] words = query.split("\\s+");

        System.out.println("Num of words : "+ words.length);
        System.out.println("Words in the query: ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("\\s+","");
            System.out.println(words[i]);

        }
        List<String> files = new ArrayList<String>();
        files.add(readFile("src/1.txt"));
        files.add(readFile("src/2.txt"));
        List<Float> results = new ArrayList<Float>();
        List<Float> results2 = new ArrayList<Float>();
        for (int i=0; i<files.size();i++)
        {
            float result = union(words,files.get(i));
            results.add(result);
            results2.add(result);
        }
        Collections.sort(results,Collections.reverseOrder());
        for (int i=0; i<results.size();i++)
        {
            for (int j=0; j<results2.size();j++)
            {
                if (results.get(i).equals(results2.get(j)))
                {
                    System.out.println("DOC"+(j+1)+"  "+results.get(i));
                    break;
                }
            }
        }
    }
}

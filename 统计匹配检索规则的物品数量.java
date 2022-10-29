import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1773. 统计匹配检索规则的物品数量
 * 
 * 简单
 */
public class 统计匹配检索规则的物品数量 {

    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;

        // type color name

        int index = Map.of("type", 0, "color", 1, "name", 2).get(ruleKey);

        for (List<String> item : items) {
            if (item.get(index).equals(ruleValue)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // java8
        List<String> item1 = Stream.of("phone", "blue", "pixel").collect(Collectors.toList());
        List<String> item2 = Stream.of("computer", "silver", "lenovo").collect(Collectors.toList());
        List<String> item3 = Stream.of("phone", "gold", "iphone").collect(Collectors.toList());

        List<List<String>> items = new ArrayList<List<String>>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        int a = countMatches(items, "color", "silver");
        System.out.println("匹配数量数：" + a);
    }
}

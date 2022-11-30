package demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("all")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Integer id;

    private String name;

    private Integer count;

    public void getCurrentCount(Integer stockCount, Integer id) {
        if (id != getId()) {
            throw new RuntimeException("当前信息不存在");
        }
        if (this.count < stockCount) {
            throw new RuntimeException("库存信息不足");
        }
        count = this.count - stockCount;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setId(1);
        person.setName("wang");
        person.setCount(12);

        person.getCurrentCount(13, 1);

        System.out.println(person);
    }
}

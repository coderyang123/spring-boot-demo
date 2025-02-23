# 定时任务案例

# 1.开启定时任务功能

```java

@EnableScheduling
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
}
```

# 2.为定时执行的的任务设置执行周期，描述方式cron表达式-注解方式实现

[`MyJob.java`](./src/main/java/com/demo/task/job/MyJob.java)

# 3.为定时执行的的任务设置执行周期，描述方式cron表达式-实现接口方式实现

[`MyJob2.java`](./src/main/java/com/demo/task/job/MyJob2.java)

# 4.配置相关参数

[`application.yml`](./src/main/resources/application.yml)

# 5.cron表达式

## 5.1 格式

`Seconds Minutes Hours DayofMonth Month DayofWeek`

- `Seconds`（秒数）：允许值范围为 0~59 不允许为空值，若值不合法，调度器将抛出异常
- `Minutes`（分钟）：允许值范围为 0~59 不允许为空值，若值不合法，调度器将抛出异常
- `Hours`（小时）：允许值范围为 0~23 不允许为空值，若值不合法，调度器将抛出异常,占位符和秒数一样
- `DayofMonth`（日期）：允许值范围为 1~31 不允许为空值，若值不合法，调度器将抛出异常
- `Month`（月份）：允许值范围为 1-12 (JAN-DEC) 不允许为空值，若值不合法，调度器将抛出异常
- `DayofWeek`（星期）：允许值范围为 1~7 (SUN-SAT)，不允许为空值，若值不合法，调度器将抛出异常

## 5.2 占位符

- `*`：代表指定单位任意值触发，比如："* * * * *"代表每月每周每日每时每分每秒都触发。
- `,`：代表在指定单位固定时刻触发，比如"5,15 * * * *"代表每月每周每日每时每分的触发0秒、15秒时触发任务
- `-`：代表在指定的单位范围内触发，比如”25-45 * * * *”代表每月每周每日每时每分的25秒到45秒之间每隔1秒触发1次
- `/`：代表触发步进，`/`前面的值代表触发初始值，后面的值代表每隔固定单位时间触发一次，比如"5/10 * * * *"
  代表每月每周每日每时每分的第5秒触发一次，然后每10秒触发一次。
- `?`：只能用在`DayofMonth`和`DayofWeek`两个域，代表指定单位任意值触发，但实际不会。因为`DayofMonth`和`DayofWeek`
  会相互影响。例如想在每月的20日触发调度，不管20日到底是星期几，则只能使用如下写法： * * * 20 * ?,
  其中DayofWeek域只能用?，而不能使用*，如果使用*表示不管周几都触发。
- `L`：表示最后，只能出现在`DayofWeek`和`DayofMonth`域，如果在`DayofWeek`域使用5L，意味着在最后的一个星期四触发。
- `W`：表示有效工作日(周一到周五)，只能出现在`DayofMonth`域，系统将在离指定日期的最近的有效工作日触发事件。例如：在
  `DayofMonth`使用5W，如果5日是星期六，则将在最近的工作日：星期五，即4日触发。如果5日是星期天，则在6日(周一)
  触发；如果5日在星期一到星期五中的一天，则就在5日触发。另外一点，W的最近寻找不会跨过月份。
- `LW`：这两个字符可以连用，表示在某个月最后一个工作日，即某个月的最后一个星期五。 
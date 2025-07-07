package dimasnovik;

public class StatsMaker {

    private final boolean isFullStatsMode;
    private int intCount = 0;
    private int floatCount = 0;
    private int stringCount = 0;
    private long minInt = Long.MAX_VALUE;
    private long maxInt = Long.MIN_VALUE;
    private double minFloat = Double.MAX_VALUE;
    private double maxFloat = Double.MIN_VALUE;
    private int maxStr = 0;
    private int minStr = Integer.MAX_VALUE;
    private long intSum = 0L;
    private double floatSum = 0.0;

    public StatsMaker(String statsMode) {
        this.isFullStatsMode = statsMode.equals("f");
    }

    public void addInt(long value) {
        intCount++;
        intSum += value;
        minInt = Long.min(minInt, value);
        maxInt = Long.max(maxInt, value);
    }

    public void addFloat(double value) {
        floatCount++;
        floatSum += value;
        maxFloat = Double.max(value, maxFloat);
        minFloat = Double.min(value, minFloat);
    }

    public void addString(String value) {
        stringCount++;
        maxStr = Integer.max(value.length(), maxStr);
        minStr = Integer.min(value.length(), minStr);
    }


    public String getStats() {
        StringBuilder intStats = new StringBuilder();
        intStats.append("Integers:\nCount:").append(intCount).append("\n");
        if (isFullStatsMode && intCount != 0) {

            intStats.append("Max: ").append(maxInt).append("\n").append("Min: ").append(minInt).append("\n")
                    .append("Sum: ").append(intSum).append("\n")
                    .append("Average: ").append(intSum / intCount).append("\n");
        }
        intStats.append("\n");

        StringBuilder floatStats = new StringBuilder();
        floatStats.append("Floats:\nCount:").append(floatCount).append("\n");
        if (isFullStatsMode && floatCount != 0) {

            floatStats.append("Max: ").append(maxFloat).append("\n").append("Min: ").append(minFloat).append("\n")
                    .append("Sum: ").append(floatSum).append("\n")
                    .append("Average: ").append(floatSum / floatCount).append("\n");
        }
        floatStats.append("\n");

        StringBuilder strStats = new StringBuilder();
        strStats.append("Strings:\nCount:").append(stringCount).append("\n");
        if (isFullStatsMode && stringCount != 0) {
            strStats.append("Max length: ").append(maxStr).append("\n")
                    .append("Min length: ").append(minStr).append("\n");
        }

        return "Statistics:\n" + intStats + floatStats + strStats;
    }
}

package com.self.learn.state;

/**
 *
 */
public class Modification {


    protected int lineNumer;
    protected String content;
    public Modification(int lineNumber, String content) {
        this.lineNumer = lineNumber;
        this.content = content;
    }

    private static boolean isDate(String content) {
        return content.contains("/") && Character.isDigit(content.charAt(0));
    }

    private static Modification getModification(String original, String modified, int lineNumber) {
        if (modified.contains("+") && modified.contains("-")) {
            return new Update(lineNumber, original);
        } else if (modified.contains("+")) {
            return new Create(lineNumber, original);
        } else if (modified.contains("-")) {
            return new Delete(lineNumber, original);
        }
        return new Unchanged(lineNumber, original);
    }

    private static String purgeContent(String content) {
        return content.replace("-", "");
    }

    private static String genDiff(StringBuilder sb, int[][] countTable, String s1, String s2, int s1Len, int s2Len) {
        if (s1Len > 0 && s2Len > 0 && s1.charAt(s1Len - 1) == s2.charAt(s2Len - 1)) {
            sb.append(" " + s1.charAt(s1Len - 1));
            return genDiff(sb, countTable, s1, s2, s1Len - 1, s2Len - 1);
        } else if (s2Len > 0 && (s1Len == 0 || countTable[s1Len - 1][s2Len] >= countTable[s1Len][s2Len - 1])) {
            sb.append(" +" + s2.charAt(s2Len - 1));
            return genDiff(sb, countTable, s1, s2, s1Len, s2Len - 1);
        } else if (s1Len > 0 && (s2Len == 0 || countTable[s1Len][s2Len - 1] > countTable[s1Len - 1][s2Len])) {
            sb.append(" -" + s1.charAt(s1Len - 1));
            return genDiff(sb, countTable, s1, s2, s1Len - 1, s2Len);
        }
        return sb.toString();
    }

    private static int[][] longestSubSequenceTable(String s1, String s2) {
        int[][] counts = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    counts[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    counts[i][j] = 1 + counts[i - 1][j - 1];
                } else {
                    counts[i][j] = Math.max(counts[i - 1][j], counts[i][j - 1]);
                }
            }
        }
        return counts;
    }

    public Modification processContent() {
        this.content = this.content
                .replace("-", "")
                .trim();
        return this;
    }

    /**
     * @param fromCol
     * @return
     */
    public String getRange(char fromCol) {
        String[] contentArray = getContent();
        int length = contentArray.length;
        int charToInteger = fromCol;
        int nextPos = charToInteger + length - 1;
        char nextCol = (char) (nextPos);
        return String.format("%s%d:%s%d", fromCol, this.lineNumer, nextCol, this.lineNumer + length);
    }

    /**
     * NOTE: This method is not completed since it still need to process its content
     *
     * @return
     */
    public String[] getContent() {
        String[] contentArr = new String[4];
        if (isDate(this.content)) {
            contentArr[0] = this.content;
            for (int i = 1; i < 4; i++) {
                contentArr[i] = "";
            }
        } else {
            int lastNotNumer = 0;
            while (!Character.isDigit(this.content.charAt(lastNotNumer++))) ;
            contentArr[0] = "";
            contentArr[1] = content.substring(0, lastNotNumer - 1);
            contentArr[2] = "";
            contentArr[3] = content.substring(lastNotNumer - 1);
        }
        return contentArr;
    }

    public Modification diff(Modification that) {
        int[][] countTable = longestSubSequenceTable(this.content, that.content);
        StringBuilder sb = new StringBuilder();
        String diff = genDiff(sb, countTable, this.content, that.content, this.content.length(), that.content.length());
        return formNewContent(diff, that.lineNumer);
    }

    protected Modification formNewContent(String diff, int lineNumber) {
        StringBuilder sb = new StringBuilder();
        for (String elem : diff.split(" ")) {
            sb.append(elem);
        }
        String newContent = sb.reverse().toString();
        if (newContent.contains("+") && newContent.contains("-")) {
            return  new Update(lineNumber,newContent);
        } else if (newContent.contains("-")) {
            return new Delete(lineNumber,newContent);
        } else {
            return new Create(lineNumber, newContent);
        }
    }

}